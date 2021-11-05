package com.overclock.overclock.service;

import com.overclock.overclock.model.User;
import com.overclock.overclock.util.RequestUser;

public interface RegistrationService {
    void register(RequestUser user);
    boolean validate(RequestUser user);
}
