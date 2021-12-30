package com.overclock.overclock.controller;

import com.overclock.overclock.model.User;
import com.overclock.overclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable("user_id") BigInteger id) {
        return userService.getFullInformationById(id);
    }
}
