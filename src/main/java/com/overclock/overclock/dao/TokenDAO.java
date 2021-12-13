package com.overclock.overclock.dao;

import com.overclock.overclock.model.ActivateAccountToken;
import com.overclock.overclock.model.RefreshToken;
import com.overclock.overclock.model.ResetPasswordToken;

import java.math.BigInteger;

public interface TokenDAO {
    String SELECT_TOKEN = "SELECT TOKEN.OBJECT_ID TOKEN_ID, TOKEN_ATTR.VALUE TOKEN_VALUE " +
            "FROM OBJECTS TOKEN, ATTRIBUTES TOKEN_ATTR " +
            "WHERE TOKEN.OBJECT_TYPE_ID = ? " +
            "   AND TOKEN_ATTR.ATTR_ID = ? " +
            "   AND TOKEN_ATTR.OBJECT_ID = TOKEN.OBJECT_ID " +
            "   AND TOKEN_ATTR.VALUE = ?";

    String SELECT_TOKEN_BY_USER_ID = "SELECT TOKEN.OBJECT_ID TOKEN_ID, TOKEN_ATTR.VALUE TOKEN_VALUE " +
            "FROM OBJECTS TOKEN, ATTRIBUTES TOKEN_ATTR " +
            "WHERE TOKEN.OBJECT_TYPE_ID = ? " +
            "   AND TOKEN.PARENT_ID = ? " +
            "   AND TOKEN_ATTR.ATTR_ID = ? " +
            "   AND TOKEN_ATTR.OBJECT_ID = TOKEN.OBJECT_ID";

    String INSERT_TOKEN = "INSERT ALL " +
            "INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) /*CREATE RESET PASSWORD TOKEN*/ " +
            "   VALUES (OBJECT_ID_SEQ.NEXTVAL, ?, ?, 'token' || OBJECT_ID_SEQ.CURRVAL, NULL) " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) " +
            "   VALUES (?, OBJECT_ID_SEQ.CURRVAL, ?) /*TOKEN ATTRIBUTE*/ " +
            "SELECT * FROM DUAL";

    String INSERT_TOKEN_BY_USER_EMAIL = "INSERT ALL " +
            "INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) /*CREATE RESET PASSWORD TOKEN*/ " +
            "   VALUES (OBJECT_ID_SEQ.NEXTVAL, USER_ID, ?, 'token' || OBJECT_ID_SEQ.CURRVAL, NULL) " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) " +
            "   VALUES (?, OBJECT_ID_SEQ.CURRVAL, ?) /*TOKEN ATTRIBUTE*/ " +
            "SELECT USERS.OBJECT_ID USER_ID FROM OBJECTS USERS, ATTRIBUTES EMAIL WHERE USERS.OBJECT_TYPE_ID = 7 /*USER*/" +
            "   AND EMAIL.OBJECT_ID = USERS.OBJECT_ID " +
            "   AND EMAIL.ATTR_ID = 40 /*EMAIL*/" +
            "   AND EMAIL.VALUE = ?";

    String DELETE_TOKEN_ATTR = "DELETE FROM ATTRIBUTES WHERE ATTR_ID = ? /*RESET PASSWORD TOKEN ATTRIBUTE*/ " +
            "AND OBJECT_ID = (SELECT OBJECT_ID FROM OBJECTS WHERE PARENT_ID = ? AND OBJECT_TYPE_ID = ?) /*TOKEN BY USER ID*/";

    String DELETE_TOKEN = "DELETE FROM OBJECTS WHERE OBJECT_TYPE_ID = ? AND PARENT_ID = ?";

    boolean saveRefreshToken(BigInteger userId, String token);
    boolean saveResetPasswordToken(BigInteger userId, String token);
    boolean deleteRefreshToken(BigInteger userId);
    boolean deleteResetPasswordToken(BigInteger userId);
    boolean saveActivateAccountToken(String email, String token);
    boolean deleteActivateAccountToken(BigInteger userId);
    ActivateAccountToken getActivateAccountToken(String token);
    RefreshToken getRefreshToken(String token);
    ResetPasswordToken getResetPasswordToken(String token);
    RefreshToken getRefreshTokenByUserId(BigInteger userId);
    ResetPasswordToken getResetPasswordTokenByUserId(BigInteger userId);
}
