package com.plunex.emilokan.modules.auth;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.plunex.emilokan.exception.CustomException;
import com.plunex.emilokan.modules.auth.dto.LoginRequest;
import com.plunex.emilokan.modules.auth.dto.PasswordResetRequest;
import com.plunex.emilokan.modules.auth.dto.RegistrationRequest;
import com.plunex.emilokan.modules.auth.dto.UserRegistrationResponse;
import com.plunex.emilokan.modules.confirmation_token.ConfirmationToken;
import com.plunex.emilokan.modules.confirmation_token.ConfirmationTokenRepository;
import com.plunex.emilokan.modules.email.EmailSenderService;
import com.plunex.emilokan.modules.role.RoleRepository;
import com.plunex.emilokan.modules.role.dto.UserRoleResponse;
import com.plunex.emilokan.modules.security.JwtTokenProvider;
import com.plunex.emilokan.modules.user.User;
import com.plunex.emilokan.modules.user.UserRepository;
import com.plunex.emilokan.modules.user.dto.UserResponse;
import com.plunex.emilokan.utill.EmailValidator;
import com.plunex.emilokan.utill.PhoneNumberValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    // Services
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;

    // Utilities
    private final PasswordEncoder encoder;
    private final EmailValidator emailValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtUtils;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    // Constants
    @Value("${spring.mail.from.email}")
    private String mailFrom;
    @Value("${legituu-api.app.verifyAccountBaseUrl}")
    private String verifyAccountBaseUrl;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService, PasswordEncoder encoder, EmailValidator emailValidator, PhoneNumberValidator phoneNumberValidator, ModelMapper modelMapper, JwtTokenProvider jwtUtils, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
        this.encoder = encoder;
        this.emailValidator = emailValidator;
        this.phoneNumberValidator = phoneNumberValidator;
        this.modelMapper = modelMapper;
        this.jwtUtils = jwtUtils;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }


    @Transactional
    @Override
    public UserRegistrationResponse register(RegistrationRequest request) {
        if (!emailValidator.test(request.getEmail())) {
            throw new CustomException(request.getEmail() + " is not valid", HttpStatus.BAD_REQUEST);
        }

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(request.getPhoneNumber(), "NG");
            if (!phoneUtil.isValidNumber(numberProto)) {
                throw new CustomException(request.getPhoneNumber() + " is not valid, use format 070********", HttpStatus.BAD_REQUEST);
            }
        } catch (NumberParseException e) {
            throw new CustomException("Invalid Phone Number", HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userRepository.findByUserNameIgnoreCase(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new CustomException(request.getEmail() + " is already taken!", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User user = modelMapper.map(request, User.class);
        user.setUserName(request.email);
        user.setPassword(encoder.encode(request.getPassword()));
//        user.setStatus(EStatus.CREATED);

//        List<Role> roles = roleRepository.findAll();
//        List<Role> userPrivilege = new ArrayList<>();
//        userPrivilege.add(roles.stream().filter(role -> role.getName().equalsIgnoreCase(ERole.ROLE_USER.name())).findAny().orElse(null));
//
//        if (request.email.equalsIgnoreCase("oluwabukunmi.aluko@gmail.com")) {
//            userPrivilege.add(roles.stream().filter(role -> role.getName().equalsIgnoreCase(ERole.ROLE_SELLER.name())).findAny().orElse(null));
//            userPrivilege.add(roles.stream().filter(role -> role.getName().equalsIgnoreCase(ERole.ROLE_MODERATOR.name())).findAny().orElse(null));
//            userPrivilege.add(roles.stream().filter(role -> role.getName().equalsIgnoreCase(ERole.ROLE_ADMIN.name())).findAny().orElse(null));
//            userPrivilege.add(roles.stream().filter(role -> role.getName().equalsIgnoreCase(ERole.ROLE_SUPER_ADMIN.name())).findAny().orElse(null));
//        }

//        user.setRoles(userPrivilege);
        userRepository.save(user);

//        if (request.email.equalsIgnoreCase("oluwabukunmi.aluko@gmail.com")) {
//            Optional<User> superAdmin = userRepository.findByUserNameIgnoreCase(request.getEmail());
//
//            List<ContactDetail> contactDetails = new ArrayList<>();
//            contactDetails.add(ContactDetail.builder()
//                    .type(EContactDetailType.EMAIL)
//                    .value("oluwabukunmi.aluko@gmail.com")
//                    .status(EStatus.CREATED)
//                    .build());
//
//            if (superAdmin.isPresent()) {
//                Store superAdminStore = new Store();
//                superAdminStore.setUserId(superAdmin.get().getId());
//                superAdminStore.setName("OBKM Store");
//                superAdminStore.setContactDetails(contactDetails);
//                superAdminStore.setCountryCode("NGN");
//                superAdminStore.setBannerUrl("");
//                superAdminStore.setSubscribed(true);
//                superAdminStore.setStatus(EStatus.CREATED);
//                storeService.saveStore(superAdminStore);
//            }
//        }

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        // Java mailer
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getEmail());
        mailMessage.setSubject("Verify Account");
        mailMessage.setFrom(mailFrom);
        mailMessage.setText("To confirm your account, click the link : " + verifyAccountBaseUrl +
                "/api/v1/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);

        return modelMapper.map(user, UserRegistrationResponse.class);

    }

    @Transactional
    @Override
    public String confirmAccount(String confirmationToken) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Optional<User> userOptional = userRepository.findByUserNameIgnoreCase(token.getUser().getEmail());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
//                user.setStatus(EStatus.ACTIVATED);
                userRepository.save(user);
                return "Account Verified";
            } else {
                throw new CustomException("User  not found!", HttpStatus.NOT_FOUND);
            }

        } else {
            throw new CustomException("The link is invalid or broken!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email, request.password));
            Optional<User> userOptional = userRepository.findByEmailIgnoreCase(request.email);
            if (userOptional.isPresent()) {
                return jwtTokenProvider.createToken(userOptional.get());
            }
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
            // Check if account is verified here.
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String forgotPassword(String email) {

        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(email);
        if (existingUser.isPresent()) {

            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser.get());
            confirmationTokenRepository.save(confirmationToken);

            // Java Mailer
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.get().getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("legitukused@gmail.com");

            //Note url should route to front end forgot password. This email should be protected

            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://" + verifyAccountBaseUrl + "/forgotPasswordPage/" + confirmationToken.getConfirmationToken() + "" +
                    "   " +
                    "Note: This link will expire after two days");

            emailSenderService.sendEmail(mailMessage);

            return "Request to reset password received. Check your email for the reset link.";

        } else {
            throw new CustomException("Email not registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Transactional
    @Override
    public String resetPassword(PasswordResetRequest request) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(request.confirmationToken);

        if (token == null) {
            throw new CustomException("Invalid token", HttpStatus.NOT_FOUND);
        }


        Date currentDate = new Date();
        Date expiryDate = DateUtils.addDays(currentDate, 2);
        boolean expired = currentDate.after(expiryDate);

        if (expired) {
            throw new CustomException("Token expired", HttpStatus.NOT_FOUND);
        }

        if (request.email != null) {
            // use email to find user
            Optional<User> tokenUser = userRepository.findByEmailIgnoreCase(request.getEmail());

            if (tokenUser.isPresent()) {
                User user = tokenUser.get();
//                user.setStatus(EStatus.ACTIVATED);
                user.setPassword(encoder.encode(request.getPassword()));
                userRepository.save(user);
                return "Password successfully reset. You can now log in with the new credentials.";

            } else {
                throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
            }

        } else {
            throw new CustomException("The link is invalid or broken!", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public UserResponse whoAmI(HttpServletRequest req) {
        String email = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));

        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        if (user.isPresent()) {
            UserResponse response = new UserResponse();
            response.setId(user.get().getId());
            response.setFirstName(user.get().getFirstName());
            response.setLastName(user.get().getLastName());
            response.setEmail(user.get().getEmail());

            List<UserRoleResponse> userRoleResponses = user.get().getRoles().stream().map(role -> modelMapper.map(role, UserRoleResponse.class)).collect(Collectors.toList());
            response.setRoles(userRoleResponses);

            return response;
        }
        throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
    }

    @Override
    public String refreshJwt(String email) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        if (user.isPresent()) {
            return jwtTokenProvider.createToken(user.get());
        }
        throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
    }
}