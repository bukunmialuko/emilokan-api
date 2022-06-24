package com.plunex.emilokan.modules.auth;


import com.plunex.emilokan.modules.auth.dto.*;
import com.plunex.emilokan.modules.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
//@Api(tags = "Auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "register")
//    @ApiOperation(value = "Sign up, Note: Phone number format 070********")
    public UserRegistrationResponse signUp(@RequestBody RegistrationRequest request) {
        return authService.register(request);
    }


    @GetMapping(value = "confirm-account")
//    @ApiOperation(value = "Confirm/Verify account")
    public MessageResponse confirmAccount(@RequestParam("token") String confirmationToken) {
        return MessageResponse.builder().message(authService.confirmAccount(confirmationToken)).build();
    }

    @PostMapping(value = "login")
//    @ApiOperation(value = "Log in")
    public JwtResponse logIn(@RequestBody LoginRequest request) {
        return JwtResponse.builder().token(authService.login(request)).build();
    }


    /**
     * This link here should load the front end forgot password page and pass the token as param
     */
    @GetMapping(value = "forgot-password")
//    @ApiOperation(value = "This link here should load the front end forgot password page and pass the token as param")
    public MessageResponse forgotPassword(String email) {
        return MessageResponse.builder().message(authService.forgotPassword(email)).build();
    }


    @PostMapping(value = "reset-password")
//    @ApiOperation(value = "Reset password")
    public MessageResponse resetPassword(@RequestBody PasswordResetRequest request) {
        return MessageResponse.builder().message(authService.resetPassword(request)).build();
    }

    /**
     * This link here should load the front end forgot password page and pass the token as param
     */
    @GetMapping(value = "me")
    @PreAuthorize("hasRole('ROLE_USER')")
//    @ApiOperation(value = "Returns current user's data", authorizations = {@Authorization(value = "apiKey")})
    public UserResponse whoAmI(HttpServletRequest req) {
        return authService.whoAmI(req);
    }

    @GetMapping(value = "refresh")
//    @ApiOperation(value = "Refresh JWT")
    @PreAuthorize("hasRole('ROLE_USER')")
    public JwtResponse refresh(HttpServletRequest req) {
        return JwtResponse.builder().token(authService.refreshJwt(req.getRemoteUser())).build();
    }

}
