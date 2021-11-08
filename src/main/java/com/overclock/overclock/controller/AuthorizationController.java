package com.overclock.overclock.controller;

import com.overclock.overclock.service.AuthorizationService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/authorization")
public class AuthorizationController {
    private AuthorizationService authorizationService;

    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/sign_in")
    public String signIn(@Valid @RequestBody RequestUser requestUser) {
        return null;
    }

    @PostMapping("/forgot_password")
    public String sendForgotPasswordMail(@RequestBody String email) {
        return null;
    }

    @PostMapping("/reset_password")
    public String resetPassword(@RequestBody String newPwd) {
        return null;
    }

    @GetMapping("/sign_out")
    public String signOut(Authentication authentication) {
        return null;
    }
}
