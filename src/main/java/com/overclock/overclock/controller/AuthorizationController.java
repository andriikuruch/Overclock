package com.overclock.overclock.controller;

import com.overclock.overclock.service.AuthorizationService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/autorization/")
public class AuthorizationController {
    private AuthorizationService authorizationService;
    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService){this.authorizationService = authorizationService;}

    @PostMapping
    public String signIn(RequestUser requestUser){return null;}
    @PostMapping
    public String sendForgotPasswordMail(String email){return null;}
    @PostMapping
    public String resetPassword(String newPwd){return null;}
    @GetMapping
    public String signOut(Authentication authentication){return null;}
}
