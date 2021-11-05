package com.overclock.overclock.service;

import com.overclock.overclock.model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    User getUserById(BigInteger id);
    User getUserByUsername(String username);
    List<User> getAll();
    boolean save(User user);
    boolean update(BigInteger id, User newUser);
    boolean delete(BigInteger id);
}
