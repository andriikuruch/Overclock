package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.dao.constant.QueryConstants;
import com.overclock.overclock.dao.impl.mapper.CommentRowMapper;
import com.overclock.overclock.dao.impl.mapper.CommentsIdRowMapper;
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
public class CommentDAOImpl implements CommentDAO, QueryConstants {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommentRowMapper commentRowMapper;

    @Autowired
    private CommentsIdRowMapper commentsIdRowMapper;

    private static final Logger LOGGER = Logger.getLogger(CommentDAOImpl.class.getName());

    private static boolean isCommentInvalid(Comment comment) {
        if (comment == null) {
            return true;
        }
        return comment.getDateOfComment() == null || comment.getCommentMessage() == null || comment.getAuthor() == null;
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean save(Comment comment, BigInteger assemblyId, BigInteger authorId) {
        if (isCommentInvalid(comment)) {
            LOGGER.log(Level.WARNING, "Invalid comment");
            return false;
        }
        try {
            int assemblyObjectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, assemblyId);
            int authorObjectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, authorId);
            if (assemblyObjectTypeId != 1) { /* Assembly */
                LOGGER.log(Level.WARNING, "Wrong assembly identifier");
                return false;
            }
            if (authorObjectTypeId != 7) { /* Author */
                LOGGER.log(Level.WARNING, "Wrong author identifier");
                return false;
            }
            String commentName = "comment" + UUID.randomUUID();
            String commentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(comment.getDateOfComment());

            jdbcTemplate.update(SQL_INSERT_INTO_OBJECTS,assemblyId, 8, commentName, null);          /* Comment */
            jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_DATE_VALUE, 45, commentDate);            /* Date of comment */
            jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 43, comment.getCommentMessage()); /* Message */
            jdbcTemplate.update(SQL_INSERT_INTO_OBJREFERENCE_OBJECT_ID, 44, authorId);              /* Author id */
            jdbcTemplate.update(SQL_INSERT_INTO_OBJREFERENCE_REFERENCE, 7, assemblyId);             /* Comments */
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
            int objectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (objectTypeId == 8) { /* Comment */
                jdbcTemplate.update(SQL_DELETE_FROM_ATTRIBUTES, id);
                jdbcTemplate.update(SQL_DELETE__FROM_OBJREFERENCE, id, id);
                jdbcTemplate.update(SQL_DELETE_FROM_OBJECTS, id);
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

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean deleteAllCommentsByAssemblyId(BigInteger assemblyId) {
        try {
            int objectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, assemblyId);
            if (objectTypeId == 1) { /* Assembly */
                List<BigInteger> identifiers = jdbcTemplate.query(SQL_SELECT_COMMENTS_ID_BY_ASSEMBLY_ID, commentsIdRowMapper, assemblyId);
                for (BigInteger id: identifiers) {
                    if (!delete(id)) {
                        LOGGER.log(Level.WARNING, "Error while trying to delete comment with identifier " + id);
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return false;
                    }
                }
                return true;
            } else {
                LOGGER.log(Level.WARNING,"Identifier belongs not to an assembly");
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
            return jdbcTemplate.query(SQL_SELECT_LIMITED_LIST_OF_COMMENTS_BY_ASSEMBLY_ID, commentRowMapper, assemblyId, limit);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<Comment> getAllCommentsByAssemblyId(BigInteger assemblyId) {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS_BY_ASSEMBLY_ID, commentRowMapper, assemblyId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }
}