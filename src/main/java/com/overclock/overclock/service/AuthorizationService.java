package com.overclock.overclock.service;

import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.util.RequestUser;
import org.springframework.security.authentication.BadCredentialsException;

public interface AuthorizationService {
    UserDetailsImpl authenticate(RequestUser user) throws BadCredentialsException;
    String getAccessToken(UserDetailsImpl user);
    void validateRequestUser(RequestUser user) throws IllegalStateException;
}
