package com.overclock.overclock.service.impl;

import com.overclock.overclock.controller.request.RequestUser;
import com.overclock.overclock.model.User;
import com.overclock.overclock.service.EmailSender;
import com.overclock.overclock.service.RegistrationService;
import com.overclock.overclock.service.TokenService;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final EmailSender emailSender;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenService tokenService;

    public RegistrationServiceImpl(UserService userService, EmailSender emailSender,
                                   JwtTokenUtil jwtTokenUtil, TokenService tokenService) {
        this.userService = userService;
        this.emailSender = emailSender;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public void register(RequestUser user) {
        if (!userService.save(user)) {
            throw new IllegalStateException("User with same username or email already exist");
        }

        String activateAccountToken = jwtTokenUtil.generateActivateAccountToken(user.getEmail());
        boolean result = tokenService.saveActivateAccountToken(user.getEmail(), activateAccountToken);

        if (!result) {
            throw new IllegalStateException("Can not generate activate account token");
        }
        String link = user.getFrontUrl() + "/api/registration/activate-account?token=" + activateAccountToken;

        emailSender.sendActivateAccountMail(user.getEmail(), link);
    }

    @Override
    @Transactional
    public void finishRegistration(String token) {
        if (!tokenService.existActivateAccountToken(token)) {
            throw new IllegalStateException("Activate account token does not exist");
        }

        String email = jwtTokenUtil.getEmailByToken(token);

        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new IllegalStateException("Can not find user by email: " + email);
        }

        boolean result = userService.updateUserActiveStatus(user.getId(), true);

        if (!result) {
            throw new IllegalStateException("Can not activate account");
        }

        tokenService.deleteActivateAccountToken(user.getId());
    }

    @Override
    public void isValidRequestUser(RequestUser user) {
        if (StringUtils.isBlank(user.getName())) {
            throw new IllegalStateException("Empty username");
        }

        if (StringUtils.isBlank(user.getEmail())) {
            throw new IllegalStateException("Empty email");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            throw new IllegalStateException("Empty password");
        }
    }
}
