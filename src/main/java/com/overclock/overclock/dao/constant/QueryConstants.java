package com.overclock.overclock.dao.constant;

public interface QueryConstants {
    final static String SQL_INSERT_INTO_OBJECTS =
            "INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) " +
            "VALUES (OBJECT_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";

    final static String SQL_INSERT_INTO_ATTRIBUTES_VALUE =
            "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
            "VALUES(?, OBJECT_ID_SEQ.CURRVAL, ?)";

    final static String SQL_INSERT_INTO_ATTRIBUTES_DATE_VALUE =
            "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE) " +
            "VALUES(?, OBJECT_ID_SEQ.CURRVAL, TO_DATE(?, 'dd/mm/yyyy hh24:mi'))";

    final static String SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID =
            "INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
            "VALUES(?, OBJECT_ID_SEQ.CURRVAL, ?)";

    final static String SQL_INSERT_INTO_OBJREFERENCE_OBJECT_ID =
            "INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) " +
            "VALUES (?, ?, OBJECT_ID_SEQ.CURRVAL)";

    final static String SQL_INSERT_INTO_OBJREFERENCE_REFERENCE =
            "INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) " +
            "VALUES (?, OBJECT_ID_SEQ.CURRVAL, ?)";

    final static String SQL_UPDATE_OBJECTS_NAME =
            "UPDATE OBJECTS SET NAME = ? " +
            "WHERE OBJECT_ID = ?";

    final static String SQL_UPDATE_ATTRIBUTES_VALUE =
            "UPDATE ATTRIBUTES SET VALUE = ? " +
            "WHERE ATTR_ID = ? AND OBJECT_ID = ?";

    final static String SQL_UPDATE_ATTRIBUTES_DATE_VALUE =
            "UPDATE ATTRIBUTES SET DATE_VALUE = TO_DATE(?, 'dd/mm/yyyy hh24:mi') " +
                    "WHERE ATTR_ID = ? AND OBJECT_ID = ?";

    final static String SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID =
            "UPDATE ATTRIBUTES SET LIST_VALUE_ID = ? " +
                    "WHERE ATTR_ID = ? AND OBJECT_ID = ?";

    final static String SQL_DELETE_FROM_OBJECTS = "DELETE FROM OBJECTS WHERE OBJECT_ID = ?";
    final static String SQL_DELETE_FROM_ATTRIBUTES = "DELETE FROM ATTRIBUTES WHERE OBJECT_ID = ?";
    final static String SQL_DELETE_FROM_OBJREFERENCE = "DELETE FROM OBJREFERENCE WHERE OBJECT_ID = ? OR REFERENCE = ?";

    final static String SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID = "SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE OBJECT_ID = ?";
}