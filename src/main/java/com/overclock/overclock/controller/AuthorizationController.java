package com.overclock.overclock.controller;

import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.AuthorizationService;
import com.overclock.overclock.util.JwtTokenUtil;
import com.overclock.overclock.util.RequestUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/api/authorization")
public class AuthorizationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @PostMapping("/sign_in")
    public ResponseEntity<?> signIn(@Valid @RequestBody RequestUser requestUser) {
        try {
            authorizationService.validateRequestUser(requestUser);
            UserDetailsImpl user = authorizationService.authenticate(requestUser);
            String accessToken = authorizationService.getAccessToken(user);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .body(user);
        } catch (BadCredentialsException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalStateException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
