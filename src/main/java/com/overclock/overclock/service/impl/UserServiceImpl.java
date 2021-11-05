package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.model.User;
import com.overclock.overclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(BigInteger id) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean update(BigInteger id, User newUser) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }
}
