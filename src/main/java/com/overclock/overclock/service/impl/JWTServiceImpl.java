package com.overclock.overclock.service.impl;

import com.overclock.overclock.service.JWTService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class JWTServiceImpl implements JWTService {

    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public String getRefreshToken() {
        return null;
    }
}
