package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.AdminDAO;
import com.overclock.overclock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class AdminDAOImpl implements AdminDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(BigInteger id) {
        return null;
    }
}
