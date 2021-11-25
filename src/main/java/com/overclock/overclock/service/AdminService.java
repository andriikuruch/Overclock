package com.overclock.overclock.service;

import com.overclock.overclock.model.User;

import java.math.BigInteger;

public interface AdminService {
    boolean blockUser(BigInteger id);
    boolean unblockUser(BigInteger id);
}
