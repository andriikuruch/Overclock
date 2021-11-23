package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.model.User;
import com.overclock.overclock.service.AdminService;
import com.overclock.overclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Scope("singleton")
public class AdminServiceImpl implements AdminService {
    private UserDAO userDAO;

    @Autowired
    public void setAdminDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getAdminById(BigInteger id) {
        return null;
    }

    @Override
    public boolean blockUser(BigInteger id) {
        return false;
    }

    @Override
    public boolean unblockUser(BigInteger id) {
        return false;
    }

    @Override
    public User getAdminByUsername(String username) {
        return null;
    }

    @Override
    public boolean update(BigInteger id, User newAdmin) {
        return false;
    }
}
