package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.User;
import com.overclock.overclock.service.AdminService;
import com.overclock.overclock.service.AuthorizationService;
import com.overclock.overclock.service.JWTService;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AuthorizationServiceImpl implements AuthorizationService {
    private UserService userService;
    private JWTService jwtService;
    private AdminService adminService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJWTService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public User getAuthorization(String username) {
        return null;
    }

    @Override
    public boolean authenticate(RequestUser user) {
        return false;
    }

    @Override
    public boolean resetPassword(String newPassword) {
        return false;
    }

    @Override
    public boolean sendForgotPasswordMail(String email) {
        return false;
    }
}
