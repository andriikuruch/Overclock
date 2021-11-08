package com.overclock.overclock.controller;

import com.overclock.overclock.service.RegistrationService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public String signUp(@Valid @RequestBody RequestUser requestUser) {
        return null;
    }
}
