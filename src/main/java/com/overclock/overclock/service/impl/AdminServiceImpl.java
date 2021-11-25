package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.service.AdminService;
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
    public boolean blockUser(BigInteger id) {
        return userDAO.updateUserActiveStatus(id, false);
    }

    @Override
    public boolean unblockUser(BigInteger id) {
        return userDAO.updateUserActiveStatus(id, true);
    }
}
