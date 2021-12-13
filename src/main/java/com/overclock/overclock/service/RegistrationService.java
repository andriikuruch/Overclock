package com.overclock.overclock.service;

import com.overclock.overclock.controller.request.RequestUser;

public interface RegistrationService {
    void register(RequestUser user);
    void isValidRequestUser(RequestUser user);
    void finishRegistration(String token);
}
