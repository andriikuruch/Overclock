package com.overclock.overclock.service;

import com.overclock.overclock.controller.request.ForgotPasswordRequest;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.controller.request.RequestUser;
import org.springframework.security.authentication.BadCredentialsException;

public interface AuthorizationService {
    UserDetailsImpl authenticate(RequestUser user) throws BadCredentialsException;
    String getAccessToken(UserDetailsImpl user);
    String getAccessTokenByRefreshToken(String refreshToken);
    String getRefreshToken(UserDetailsImpl user);
    void validateRequestUser(RequestUser user) throws IllegalStateException;
    void validateRefreshToken(String token) throws IllegalStateException;
    void validateResetPasswordToken(String token) throws IllegalStateException;
    void resetPassword(String token, String newPassword);
    void forgotPassword(ForgotPasswordRequest email);
}
