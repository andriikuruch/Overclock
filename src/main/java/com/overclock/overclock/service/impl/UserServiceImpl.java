package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.model.User;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.controller.request.RequestUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserDAO userDAO) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Override
    public User getFullInformationById(BigInteger id) {
        return userDAO.getFullInformationById(id);
    }

    @Override
    public User getWithMainInformation(BigInteger id) {
        return userDAO.getWithMainInformation(id);
    }

    @Override
    public User getWithMainInformationByUsername(String username) {
        return userDAO.getWithMainInformationByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean save(RequestUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userDAO.save(user.getName(), encodedPassword, user.getEmail(), false);
    }

    @Override
    public boolean updateUsername(BigInteger id, String username) {
        return userDAO.updateUsername(id, username);
    }

    @Override
    public boolean updatePassword(BigInteger id, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return userDAO.updatePassword(id, encodedPassword);
    }

    @Override
    public boolean updateEmail(BigInteger id, String email) {
        return userDAO.updateEmail(id, email);
    }

    @Override
    public boolean updateUserActiveStatus(BigInteger id, boolean isActive) {
        return userDAO.updateUserActiveStatus(id, isActive);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getWithMainInformationByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found by username: " + username);
        }

        return UserDetailsImpl.fromUser(user);
    }

    @Override
    public User getCurrentUser() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getFullInformationById(user.getId());
    }
}