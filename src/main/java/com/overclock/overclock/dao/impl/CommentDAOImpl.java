package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.dao.impl.mapper.CommentRowMapper;
import com.overclock.overclock.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(CommentDAOImpl.class.getName());

    private static boolean checkNotNull(Comment comment) {
        if (comment == null) {
            return false;
        } else if (comment.getDateOfComment() == null || comment.getCommentMessage() == null || comment.getAuthor() == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean save(Comment comment, BigInteger assemblyId, BigInteger authorId) {
        if (!checkNotNull(comment)) {
            LOGGER.log(Level.WARNING, "Invalid comment");
            return false;
        }
        try {
            String commentName = "comment" + UUID.randomUUID();
            String commentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(comment.getDateOfComment());

            jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) "
                    + "VALUES (OBJECT_ID_SEQ.NEXTVAL, " + assemblyId + ", 8, '" + commentName + "', NULL)");
            String sql = "INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, DATE_VALUE) "
                    + "VALUES (45, OBJECT_ID_SEQ.CURRVAL, TO_DATE('" + commentDate + "', 'dd/mm/yyyy hh24:mi'))";
            jdbcTemplate.update(sql);
            jdbcTemplate.update("INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) "
                    + "VALUES (43, OBJECT_ID_SEQ.CURRVAL, '" + comment.getCommentMessage() + "')");
            jdbcTemplate.update("INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) " +
                    "VALUES (44, " + authorId + ", OBJECT_ID_SEQ.CURRVAL)");
            return true;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(BigInteger id) {
        try {
            int objectTypeId = jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE object_id = " + id, Integer.class);
            if (objectTypeId == 8) {
                jdbcTemplate.update("DELETE FROM ATTRIBUTES WHERE OBJECT_ID = "+ id);
                jdbcTemplate.update("DELETE FROM OBJREFERENCE WHERE OBJECT_ID = "+ id +" OR REFERENCE = "+ id);
                jdbcTemplate.update("DELETE FROM OBJECTS WHERE OBJECT_ID = "+ id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Identifier belongs not to a comment");
                return false;
            }
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    public List<Comment> getLimitedListOfCommentsByAssemblyId(BigInteger assemblyId, BigInteger limit) {
        try {
            String sql = "SELECT * FROM " +
                    "(SELECT COMMENTS.OBJECT_ID ID, MESSAGES.VALUE MESSAGE , DATES.DATE_VALUE DATE_OF_COMMENT, USERS.NAME AUTHOR " +
                    "FROM OBJECTS COMMENTS, OBJECTS ASSEMBLIES, OBJECTS USERS, OBJREFERENCE REF, " +
                    "    ATTRIBUTES MESSAGES, ATTRIBUTES DATES " +
                    "        WHERE COMMENTS.OBJECT_TYPE_ID = 8 " +
                    "        AND ASSEMBLIES.OBJECT_TYPE_ID = 1 " +
                    "        AND USERS.OBJECT_TYPE_ID = 7 " +
                    "        AND ASSEMBLIES.OBJECT_ID = " + assemblyId +
                    "        AND REF.ATTR_ID = 44 " +
                    "        AND REF.OBJECT_ID = USERS.OBJECT_ID " +
                    "        AND REF.REFERENCE = COMMENTS.OBJECT_ID " +
                    "        AND MESSAGES.ATTR_ID = 43 " +
                    "        AND DATES.ATTR_ID = 45 " +
                    "        AND MESSAGES.OBJECT_ID = COMMENTS.OBJECT_ID " +
                    "        AND DATES.OBJECT_ID = COMMENTS.OBJECT_ID " +
                    "        AND COMMENTS.PARENT_ID = ASSEMBLIES.OBJECT_ID " +
                    "    ORDER BY DATE_OF_COMMENT)" +
                    "WHERE rownum <= " + limit;
            return jdbcTemplate.query(sql, new CommentRowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<Comment> getAllCommentsByAssemblyId(BigInteger assemblyId) {
        try {
            String sql = "SELECT COMMENTS.OBJECT_ID ID, MESSAGES.VALUE MESSAGE , DATES.DATE_VALUE DATE_OF_COMMENT, USERS.NAME AUTHOR " +
                    "FROM OBJECTS COMMENTS, OBJECTS ASSEMBLIES, OBJECTS USERS, OBJREFERENCE REF, " +
                    "    ATTRIBUTES MESSAGES, ATTRIBUTES DATES " +
                    "        WHERE COMMENTS.OBJECT_TYPE_ID = 8 " +
                    "        AND ASSEMBLIES.OBJECT_TYPE_ID = 1 " +
                    "        AND USERS.OBJECT_TYPE_ID = 7 " +
                    "        AND ASSEMBLIES.OBJECT_ID = " + assemblyId +
                    "        AND REF.ATTR_ID = 44 " +
                    "        AND REF.OBJECT_ID = USERS.OBJECT_ID " +
                    "        AND REF.REFERENCE = COMMENTS.OBJECT_ID " +
                    "        AND MESSAGES.ATTR_ID = 43 " +
                    "        AND DATES.ATTR_ID = 45 " +
                    "        AND MESSAGES.OBJECT_ID = COMMENTS.OBJECT_ID " +
                    "        AND DATES.OBJECT_ID = COMMENTS.OBJECT_ID " +
                    "        AND COMMENTS.PARENT_ID = ASSEMBLIES.OBJECT_ID ";
            return jdbcTemplate.query(sql, new CommentRowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }
}
