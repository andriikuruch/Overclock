package com.overclock.overclock.service.impl;

import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.AdminService;
import com.overclock.overclock.service.AuthorizationService;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.JwtTokenUtil;
import com.overclock.overclock.util.RequestUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AdminService adminService;
    private final AuthenticationManager authenticationManager;

    public AuthorizationServiceImpl(UserService userService, JwtTokenUtil jwtTokenUtil,
                                    AdminService adminService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.adminService = adminService;
        this.authenticationManager = authenticationManager;
    }

    public UserDetailsImpl authenticate(RequestUser user) throws BadCredentialsException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    @Override
    public String getAccessToken(UserDetailsImpl user) {
        return jwtTokenUtil.generateAccessToken(user);
    }

    public void validateRequestUser(RequestUser user) throws IllegalStateException {
        if (StringUtils.isBlank(user.getName())) {
            throw new IllegalStateException("Username is empty.");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            throw new IllegalStateException("Password is empty");
        }
    }
}
