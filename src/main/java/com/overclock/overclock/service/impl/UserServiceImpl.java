package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.model.User;
import com.overclock.overclock.service.UserService;
import com.overclock.overclock.util.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
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
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean save(RequestUser user) {
        return userDAO.save(user.getName(), user.getPassword(), user.getEmail(), true);
    }

    @Override
    public boolean updateUsername(BigInteger id, String username) {
        return userDAO.updateUsername(id, username);
    }

    @Override
    public boolean updatePassword(BigInteger id, String password) {
        return userDAO.updatePassword(id, password);
    }

    @Override
    public boolean updateEmail(BigInteger id, String email) {
        return userDAO.updateEmail(id, email);
    }

    @Override
    public boolean updateUserActiveStatus(BigInteger id, boolean isActive) {
        return userDAO.updateUserActiveStatus(id, isActive);
    }
}
