package com.overclock.overclock.service;

public interface JWTService {
    String getAccessToken();
    String getRefreshToken();
}
