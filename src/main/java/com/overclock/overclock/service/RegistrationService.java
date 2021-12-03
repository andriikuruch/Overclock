package com.overclock.overclock.service;

import com.overclock.overclock.util.RequestUser;

public interface RegistrationService {
    boolean register(RequestUser user);
    boolean isValid(RequestUser user);
}
