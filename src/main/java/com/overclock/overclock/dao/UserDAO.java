package com.overclock.overclock.dao;

import com.overclock.overclock.model.User;

import java.math.BigInteger;

public interface UserDAO {
    String GET_WITH_MAIN_INFORMATION = "SELECT USERS.OBJECT_ID USER_ID, " +
            "    USERS.NAME USERNAME, EMAIL.VALUE EMAIL, " +
            "    USER_ROLE.LIST_VALUE_ID USER_ROLE," +
            "    PASSWORD.VALUE PASSWORD " +
            "FROM OBJECTS USERS, ATTRIBUTES EMAIL, " +
            "    ATTRIBUTES PASSWORD, ATTRIBUTES USER_ROLE " +
            "WHERE USERS.OBJECT_ID = ? " +
            "    AND PASSWORD.ATTR_ID = 39 /*PASSWORD*/ " +
            "    AND PASSWORD.OBJECT_ID = USERS.OBJECT_ID " +
            "    AND EMAIL.ATTR_ID = 40 /*EMAIL*/ " +
            "    AND EMAIL.OBJECT_ID =  USERS.OBJECT_ID " +
            "    AND USER_ROLE.ATTR_ID = 42 /*ROLE*/ " +
            "    AND USER_ROLE.OBJECT_ID =  USERS.OBJECT_ID";

    String GET_WITH_FULL_INFORMATION = "SELECT USERS.OBJECT_ID USER_ID, " +
            "    USERS.NAME USERNAME, EMAIL.VALUE EMAIL, " +
            "    REGISTRATION_DATE.DATE_VALUE REGISTRATION_DATE, " +
            "    BLOCKED.VALUE IS_ACTIVE, " +
            "    USER_ROLE.LIST_VALUE_ID USER_ROLE " +
            "FROM OBJECTS USERS, ATTRIBUTES EMAIL, ATTRIBUTES BLOCKED, " +
            "    ATTRIBUTES REGISTRATION_DATE, ATTRIBUTES USER_ROLE " +
            "WHERE USERS.OBJECT_ID = ? " +
            "    AND BLOCKED.ATTR_ID = 38 /*IS_ACTIVE*/ " +
            "    AND BLOCKED.OBJECT_ID = USERS.OBJECT_ID " +
            "    AND EMAIL.ATTR_ID = 40 /*EMAIL*/ " +
            "    AND EMAIL.OBJECT_ID =  USERS.OBJECT_ID " +
            "    AND REGISTRATION_DATE.ATTR_ID = 41 /*REGISTRATION_DATE*/ " +
            "    AND REGISTRATION_DATE.OBJECT_ID =  USERS.OBJECT_ID " +
            "    AND USER_ROLE.ATTR_ID = 42 /*ROLE*/ " +
            "    AND USER_ROLE.OBJECT_ID =  USERS.OBJECT_ID";

    String INSERT_USER = "INSERT ALL " +
            "INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) " +
            "VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 7, ?, NULL) /*CREATE USER WITH USERNAME*/ " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) VALUES (38, OBJECT_ID_SEQ.CURRVAL, ?) /*IS ACTIVE*/ " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) VALUES (39, OBJECT_ID_SEQ.CURRVAL, ?) /*PASSWORD*/ " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) VALUES (40, OBJECT_ID_SEQ.CURRVAL, ?) /*EMAIL*/ " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, DATE_VALUE) " +
                "VALUES (41, OBJECT_ID_SEQ.CURRVAL, TO_DATE(?, 'dd/mm/yyyy hh24:mi')) /*REGISTRATION DATE*/ " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                "VALUES (42, OBJECT_ID_SEQ.CURRVAL, ?) /*ROLE*/ " +
            "SELECT * FROM DUAL";

    String UPDATE_USERNAME = "UPDATE OBJECTS SET NAME = ? WHERE OBJECT_ID = ? /*USERNAME*/ ";
    String UPDATE_PASSWORD = "UPDATE ATTRIBUTES SET VALUE = ? WHERE ATTR_ID = 39 " +
            "AND OBJECT_ID = ? /*PASSWORD*/";
    String UPDATE_EMAIL = "UPDATE ATTRIBUTES SET VALUE = ? WHERE ATTR_ID = 40 " +
            "AND OBJECT_ID = ? /*EMAIL*/";

    String UPDATE_USER_ACTIVE_STATUS = "UPDATE ATTRIBUTES SET VALUE = ? WHERE ATTR_ID = 38 " +
            "AND OBJECT_ID = ? /*IS_ACTIVE*/";



    User getFullInformationById(BigInteger id);
    User getWithMainInformation(BigInteger id);
    boolean save(String username, String password, String email, boolean isActive);
    boolean updateUsername(BigInteger id, String username);
    boolean updatePassword(BigInteger id, String password);
    boolean updateEmail(BigInteger id, String email);
    boolean updateUserActiveStatus(BigInteger id, boolean isActive);
}
