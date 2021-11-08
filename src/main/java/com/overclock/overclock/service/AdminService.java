package com.overclock.overclock.service;

import com.overclock.overclock.model.User;

import java.math.BigInteger;

public interface AdminService {
    User getAdminById(BigInteger id);
    boolean blockUser(BigInteger id);
    boolean unblockUser(BigInteger id);
    User getAdminByUsername(String username);
    boolean update(BigInteger id, User newAdmin);
}
