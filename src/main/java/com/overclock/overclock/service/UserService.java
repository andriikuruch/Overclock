package com.overclock.overclock.service;

import com.overclock.overclock.model.User;
import com.overclock.overclock.util.RequestUser;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    User getFullInformationById(BigInteger id);
    User getWithMainInformation(BigInteger id);
    User getUserByUsername(String username);
    boolean save(RequestUser user);
    boolean updateUsername(BigInteger id, String username);
    boolean updatePassword(BigInteger id, String password);
    boolean updateEmail(BigInteger id, String email);
    boolean updateUserActiveStatus(BigInteger id, boolean isActive);
}
