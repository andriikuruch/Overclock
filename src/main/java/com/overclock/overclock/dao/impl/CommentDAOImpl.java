package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Comment comment, BigInteger assemblyId) {
        return false;
    }

    @Override
    public List<Comment> getLimitListOfCommentsToAssembly(BigInteger assemblyId, int limit) {
        return null;
    }

    @Override
    public List<Comment> getAllCommentsToAssembly(BigInteger assemblyId) {
        return null;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }
}
