package com.overclock.overclock.service;

import com.overclock.overclock.model.Admin;

import java.math.BigInteger;

public interface AdminService {
    Admin getAdminById(BigInteger id);
    boolean blockUser(BigInteger id);
    boolean unblockUser(BigInteger id);
    Admin getAdminByUsername(String username);
    boolean update(BigInteger id, Admin newAdmin);
}
