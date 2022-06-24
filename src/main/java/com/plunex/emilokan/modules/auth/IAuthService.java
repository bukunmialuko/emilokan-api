package com.plunex.emilokan.modules.auth;


import com.plunex.emilokan.modules.auth.dto.LoginRequest;
import com.plunex.emilokan.modules.auth.dto.PasswordResetRequest;
import com.plunex.emilokan.modules.auth.dto.RegistrationRequest;
import com.plunex.emilokan.modules.auth.dto.UserRegistrationResponse;
import com.plunex.emilokan.modules.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;



public interface IAuthService {

    UserRegistrationResponse register(RegistrationRequest request);

    String confirmAccount(String confirmationToken);

    String login(LoginRequest request);

    String forgotPassword(String email);

    String resetPassword(PasswordResetRequest request);

    UserResponse whoAmI(HttpServletRequest req);

    String refreshJwt(String email);
}