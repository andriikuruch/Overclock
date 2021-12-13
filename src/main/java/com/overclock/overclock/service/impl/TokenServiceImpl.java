package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.TokenDAO;
import com.overclock.overclock.model.ActivateAccountToken;
import com.overclock.overclock.model.RefreshToken;
import com.overclock.overclock.model.ResetPasswordToken;
import com.overclock.overclock.service.TokenService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenDAO tokenDAO;

    public TokenServiceImpl(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public boolean saveRefreshToken(BigInteger userId, String token) {
        return tokenDAO.saveRefreshToken(userId, token);
    }

    @Override
    public boolean saveResetPasswordToken(BigInteger userId, String token) {
        return tokenDAO.saveResetPasswordToken(userId, token);
    }

    @Override
    public boolean deleteRefreshToken(BigInteger userId) {
        return tokenDAO.deleteRefreshToken(userId);
    }

    @Override
    public boolean deleteResetPasswordToken(BigInteger userId) {
        return tokenDAO.deleteResetPasswordToken(userId);
    }

    @Override
    public boolean deleteActivateAccountToken(BigInteger userId) {
        return tokenDAO.deleteActivateAccountToken(userId);
    }

    @Override
    public boolean saveActivateAccountToken(String email, String activateAccountToken) {
        return tokenDAO.saveActivateAccountToken(email, activateAccountToken);
    }

    @Override
    public boolean existActivateAccountToken(String token) {
        ActivateAccountToken activateAccountToken = tokenDAO.getActivateAccountToken(token);
        return activateAccountToken != null;
    }

    @Override
    public boolean existRefreshToken(String token) {
        RefreshToken refreshToken = tokenDAO.getRefreshToken(token);
        return refreshToken != null;
    }

    @Override
    public boolean existResetPasswordToken(String token) {
        ResetPasswordToken resetPasswordToken = tokenDAO.getResetPasswordToken(token);
        return resetPasswordToken != null;
    }

    @Override
    public boolean existRefreshToken(BigInteger userId) {
        RefreshToken refreshToken = tokenDAO.getRefreshTokenByUserId(userId);
        return refreshToken != null;
    }

    @Override
    public boolean existResetPasswordToken(BigInteger userId) {
        ResetPasswordToken resetPasswordToken = tokenDAO.getResetPasswordTokenByUserId(userId);
        return resetPasswordToken != null;
    }
}
