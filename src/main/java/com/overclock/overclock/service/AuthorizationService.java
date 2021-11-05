package com.overclock.overclock.service;

import com.overclock.overclock.model.User;
import com.overclock.overclock.util.RequestUser;

public interface AuthorizationService {
    User getAuthorization(String username);
    boolean authenticate(RequestUser user);
    boolean resetPassword(String newPassword);
    boolean sendForgotPasswordMail(String email);
}
