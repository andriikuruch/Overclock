package com.overclock.overclock.controller;

import com.overclock.overclock.controller.request.ForgotPasswordRequest;
import com.overclock.overclock.controller.request.RefreshTokenRequest;
import com.overclock.overclock.controller.request.RequestUser;
import com.overclock.overclock.controller.request.ResetPasswordRequest;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Validated
@RestController
@RequestMapping(value = "/api/authorization")
public class AuthorizationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<?> signIn(@Valid @RequestBody RequestUser requestUser) {
        try {
            authorizationService.validateRequestUser(requestUser);

            UserDetailsImpl userDetails = authorizationService.authenticate(requestUser);

            String accessToken = authorizationService.getAccessToken(userDetails);
            String refreshToken = authorizationService.getRefreshToken(userDetails);

            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("accessToken", accessToken);
                put("refreshToken", refreshToken);
                put("user", userDetails.toUser());
            }});
        } catch (BadCredentialsException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalStateException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            String refreshToken = refreshTokenRequest.getRefreshToken();
            authorizationService.validateRefreshToken(refreshToken);

            String accessToken = authorizationService.getAccessTokenByRefreshToken(refreshToken);
            return ResponseEntity.ok(new HashMap<String, String>(){{
                put("accessToken", accessToken);
                put("refreshToken", refreshToken);
            }});
        } catch (IllegalStateException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try {
            String email = forgotPasswordRequest.getEmail();
            authorizationService.forgotPassword(email);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            String resetPasswordToken = resetPasswordRequest.getResetPasswordToken();
            String newPassword = resetPasswordRequest.getNewPassword();

            authorizationService.validateResetPasswordToken(resetPasswordToken);

            authorizationService.resetPassword(resetPasswordToken, newPassword);

            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
