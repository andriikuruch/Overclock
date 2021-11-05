package com.overclock.overclock.dao;

import com.overclock.overclock.model.Admin;

import java.math.BigInteger;

public interface AdminDAO {
    Admin getById(BigInteger id);
}
