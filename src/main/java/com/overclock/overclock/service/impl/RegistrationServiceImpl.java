package com.overclock.overclock.service.impl;

import com.overclock.overclock.service.RegistrationService;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.RequestUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(RequestUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userService.save(user);
    }

    @Override
    public boolean isValid(RequestUser user) {
        return !StringUtils.isAllBlank(user.getName(), user.getPassword(), user.getEmail());
    }
}
