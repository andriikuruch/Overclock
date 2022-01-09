package com.overclock.overclock.service;

import com.overclock.overclock.model.User;
import com.overclock.overclock.controller.request.RequestUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigInteger;

public interface UserService extends UserDetailsService {
    User getFullInformationById(BigInteger id);
    User getWithMainInformation(BigInteger id);
    User getWithMainInformationByUsername(String username);
    User getUserByUsername(String username);
    boolean save(RequestUser user);
    boolean updateUsername(BigInteger id, String username);
    boolean updatePassword(BigInteger id, String password);
    boolean updateEmail(BigInteger id, String email);
    boolean updateUserActiveStatus(BigInteger id, boolean isActive);
    User getUserByEmail(String email);
    User getCurrentUser();
}
