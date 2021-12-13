package com.overclock.overclock.service;

import java.math.BigInteger;

public interface AdminService {
    void blockUser(BigInteger id) throws IllegalStateException;
    void unblockUser(BigInteger id) throws IllegalStateException;
}
