package com.overclock.overclock.dao;

import com.overclock.overclock.model.User;

import java.math.BigInteger;

public interface AdminDAO {
    User getById(BigInteger id);
}
