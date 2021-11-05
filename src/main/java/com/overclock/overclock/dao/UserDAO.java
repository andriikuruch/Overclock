package com.overclock.overclock.dao;

import com.overclock.overclock.model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserDAO {
    User getById(BigInteger id);
    User getByUsername(String username);
    List<User> getAll();
    boolean save(User user);
    boolean update(BigInteger id, User newUser);
    boolean delete(BigInteger id);
}
