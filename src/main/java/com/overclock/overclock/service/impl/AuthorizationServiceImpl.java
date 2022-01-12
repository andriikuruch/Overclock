package com.overclock.overclock.service.impl;

import com.overclock.overclock.controller.request.ForgotPasswordRequest;
import com.overclock.overclock.controller.request.RequestUser;
import com.overclock.overclock.model.User;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.AuthorizationService;
import com.overclock.overclock.service.EmailSender;
import com.overclock.overclock.service.TokenService;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenService tokenService;
    private final EmailSender emailSender;
    private final AuthenticationManager authenticationManager;

    public AuthorizationServiceImpl(UserService userService, JwtTokenUtil jwtTokenUtil,
                                    TokenService tokenService, EmailSender emailSender,
                                    AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenService = tokenService;
        this.emailSender = emailSender;
        this.authenticationManager = authenticationManager;
    }

    public UserDetailsImpl authenticate(RequestUser user) throws BadCredentialsException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isActive()) {
            throw new IllegalStateException("Account is not activated or blocked");
        }

        return userDetails;
    }

    @Override
    public String getAccessToken(UserDetailsImpl user) {
        return jwtTokenUtil.generateAccessToken(user);
    }

    @Override
    public String getAccessTokenByRefreshToken(String refreshToken) {
        if (!tokenService.existRefreshToken(refreshToken)) {
            throw new IllegalStateException("Refresh token does not exist");
        }

        return jwtTokenUtil.generateAccessTokenByRefreshToken(refreshToken);
    }

    @Override
    @Transactional
    public String getRefreshToken(UserDetailsImpl user) {
        if (tokenService.existRefreshToken(user.getId())) {
            tokenService.deleteRefreshToken(user.getId());
        }

        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        tokenService.saveRefreshToken(user.getId(), refreshToken);
        return refreshToken;
    }

    @Override
    public void validateRequestUser(RequestUser user) throws IllegalStateException {
        if (StringUtils.isBlank(user.getName())) {
            throw new IllegalStateException("Username is empty.");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            throw new IllegalStateException("Password is empty");
        }
    }

    @Override
    public void validateRefreshToken(String token) throws IllegalStateException {
        if (!jwtTokenUtil.validate(token)) {
            throw new IllegalStateException("Invalid refresh token");
        }
    }

    @Override
    public void validateResetPasswordToken(String token) throws IllegalStateException {
        if (!jwtTokenUtil.validate(token)) {
            throw new IllegalStateException("Invalid reset password token");
        }
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) throws IllegalStateException {
        if (!tokenService.existResetPasswordToken(token)) {
            throw new IllegalStateException("Reset password token does not exist");
        }

        String email = jwtTokenUtil.getEmailByToken(token);

        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new IllegalStateException("Can not find user by email: " + email);
        }

        boolean result = userService.updatePassword(user.getId(), newPassword);

        if (!result) {
            throw new IllegalStateException("Can not reset password");
        }

        tokenService.deleteResetPasswordToken(user.getId());
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) throws IllegalStateException, UsernameNotFoundException {
        String email = request.getEmail();
        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " does not exist");
        }

        if (tokenService.existResetPasswordToken(user.getId())) {
            tokenService.deleteResetPasswordToken(user.getId());
        }

        String resetPasswordToken = jwtTokenUtil.generateResetPasswordToken(email);
        boolean result = tokenService.saveResetPasswordToken(user.getId(), resetPasswordToken);

        if (!result) {
            throw new IllegalStateException("Can not generate reset password token");
        }

        String link = request.getFrontUrl() + "/reset-password?token=" + resetPasswordToken;
        emailSender.sendForgotPasswordMail(email, link);
    }
}
