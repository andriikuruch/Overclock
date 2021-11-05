package com.overclock.overclock.service.impl;

import com.overclock.overclock.service.RegistrationService;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class RegistrationServiceImpl implements RegistrationService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(RequestUser user) {

    }

    @Override
    public boolean validate(RequestUser user) {
        return false;
    }
}
