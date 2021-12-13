package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.TokenDAO;
import com.overclock.overclock.dao.impl.mapper.ActivateAccountTokenRowMapper;
import com.overclock.overclock.dao.impl.mapper.RefreshTokenRowMapper;
import com.overclock.overclock.dao.impl.mapper.ResetPasswordTokenRowMapper;
import com.overclock.overclock.model.ActivateAccountToken;
import com.overclock.overclock.model.RefreshToken;
import com.overclock.overclock.model.ResetPasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;

@Repository
public class TokenDAOImpl implements TokenDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenDAOImpl.class);
    private static final int RESET_PASSWORD_TOKEN_TYPE = 9;
    private static final int REFRESH_TOKEN_TYPE = 10;
    private static final int RESET_PASSWORD_TOKEN_ATTR = 46;
    private static final int REFRESH_TOKEN_ATTR = 47;
    private static final int ACTIVATE_ACCOUNT_TOKEN_TYPE = 11;
    private static final int ACTIVATE_ACCOUNT_TOKEN_ATTR = 48;
    private final JdbcTemplate jdbcTemplate;
    private final ActivateAccountTokenRowMapper activateAccountTokenRowMapper;
    private final RefreshTokenRowMapper refreshTokenRowMapper;
    private final ResetPasswordTokenRowMapper resetPasswordTokenRowMapper;

    public TokenDAOImpl(JdbcTemplate jdbcTemplate, ActivateAccountTokenRowMapper activateAccountTokenRowMapper,
                        RefreshTokenRowMapper refreshTokenRowMapper,
                        ResetPasswordTokenRowMapper resetPasswordTokenRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.activateAccountTokenRowMapper = activateAccountTokenRowMapper;
        this.refreshTokenRowMapper = refreshTokenRowMapper;
        this.resetPasswordTokenRowMapper = resetPasswordTokenRowMapper;
    }

    @Override
    public boolean saveRefreshToken(BigInteger userId, String token) {
        return save(userId, REFRESH_TOKEN_TYPE, REFRESH_TOKEN_ATTR, token);
    }

    @Override
    public boolean saveResetPasswordToken(BigInteger userId, String token) {
        return save(userId, RESET_PASSWORD_TOKEN_TYPE, RESET_PASSWORD_TOKEN_ATTR, token);
    }

    @Override
    public boolean saveActivateAccountToken(String email, String token) {
        return save(email, ACTIVATE_ACCOUNT_TOKEN_TYPE, ACTIVATE_ACCOUNT_TOKEN_ATTR, token);
    }

    @Override
    @Transactional
    public boolean deleteRefreshToken(BigInteger userId) {
        return delete(userId, REFRESH_TOKEN_TYPE, REFRESH_TOKEN_ATTR);
    }

    @Override
    @Transactional
    public boolean deleteResetPasswordToken(BigInteger userId) {
        return delete(userId, RESET_PASSWORD_TOKEN_TYPE, RESET_PASSWORD_TOKEN_ATTR);
    }

    @Override
    @Transactional
    public boolean deleteActivateAccountToken(BigInteger userId) {
        return delete(userId, ACTIVATE_ACCOUNT_TOKEN_TYPE, ACTIVATE_ACCOUNT_TOKEN_ATTR);
    }

    @Override
    public ActivateAccountToken getActivateAccountToken(String token) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TOKEN, activateAccountTokenRowMapper,
                    ACTIVATE_ACCOUNT_TOKEN_TYPE, ACTIVATE_ACCOUNT_TOKEN_ATTR, token);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public RefreshToken getRefreshToken(String token) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TOKEN, refreshTokenRowMapper,
                    REFRESH_TOKEN_TYPE, REFRESH_TOKEN_ATTR, token);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ResetPasswordToken getResetPasswordToken(String token) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TOKEN, resetPasswordTokenRowMapper,
                    RESET_PASSWORD_TOKEN_TYPE, RESET_PASSWORD_TOKEN_ATTR, token);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }


    @Override
    public RefreshToken getRefreshTokenByUserId(BigInteger userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TOKEN_BY_USER_ID, refreshTokenRowMapper,
                    REFRESH_TOKEN_TYPE, userId, REFRESH_TOKEN_ATTR);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ResetPasswordToken getResetPasswordTokenByUserId(BigInteger userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TOKEN_BY_USER_ID, resetPasswordTokenRowMapper,
                    RESET_PASSWORD_TOKEN_TYPE, userId, RESET_PASSWORD_TOKEN_ATTR);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private boolean save(BigInteger userId, int tokenType, int tokenAttribute, String token) {
        try {
            jdbcTemplate.update(INSERT_TOKEN, userId, tokenType, tokenAttribute, token);
            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private boolean save(String userEmail, int tokenType, int tokenAttribute, String token) {
        try {
            jdbcTemplate.update(INSERT_TOKEN_BY_USER_EMAIL, tokenType, tokenAttribute, token, userEmail);
            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private boolean delete(BigInteger userId, int tokenType, int tokenAttribute) {
        try {
            int result = 0;
            result += jdbcTemplate.update(DELETE_TOKEN_ATTR, tokenAttribute, userId, tokenType);
            result += jdbcTemplate.update(DELETE_TOKEN, tokenType, userId);

            if (result != 2 ) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }

            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
