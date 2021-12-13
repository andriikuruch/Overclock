package com.overclock.overclock.service;

import java.math.BigInteger;

public interface TokenService {
    boolean saveRefreshToken(BigInteger userId, String token);
    boolean saveResetPasswordToken(BigInteger userId, String token);
    boolean deleteRefreshToken(BigInteger userId);
    boolean deleteResetPasswordToken(BigInteger userId);
    boolean deleteActivateAccountToken(BigInteger userId);
    boolean saveActivateAccountToken(String email, String activateAccountToken);
    boolean existActivateAccountToken(String token);
    boolean existRefreshToken(String token);
    boolean existResetPasswordToken(String token);
    boolean existRefreshToken(BigInteger userId);
    boolean existResetPasswordToken(BigInteger userId);
}
