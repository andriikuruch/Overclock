package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AdminServiceImpl implements AdminService {
    private UserDAO userDAO;

    @Autowired
    public void setAdminDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void blockUser(BigInteger id) throws IllegalStateException {
        boolean status = userDAO.updateUserActiveStatus(id, false);

        if (!status) {
            throw new IllegalStateException("Can not block user with id: " + id);
        }
    }

    @Override
    public void unblockUser(BigInteger id) throws IllegalStateException {
        boolean status = userDAO.updateUserActiveStatus(id, true);

        if (!status) {
            throw new IllegalStateException("Can not unblock user with id: " + id);
        }
    }
}
