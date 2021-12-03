package com.overclock.overclock.controller;

import com.overclock.overclock.service.RegistrationService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/api/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> signUp(@Valid @RequestBody RequestUser requestUser) {
        if (!registrationService.isValid(requestUser))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Not valid username, password or email.");

        boolean result = registrationService.register(requestUser);

        if (!result)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Sign up is failed. User with these username or email already exist.");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successful sign up.");
    }
}
