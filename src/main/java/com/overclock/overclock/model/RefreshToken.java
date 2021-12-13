package com.overclock.overclock.model;

import java.math.BigInteger;

public class RefreshToken {
    private final BigInteger id;
    private final String refreshToken;

    public RefreshToken(BigInteger id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public BigInteger getId() {
        return id;
    }
}
