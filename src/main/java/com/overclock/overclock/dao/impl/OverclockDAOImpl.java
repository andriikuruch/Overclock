package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class OverclockDAOImpl implements OverclockDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Overclock getOverclockById(BigInteger id) {
        return null;
    }

    @Override
    public boolean save(Overclock overclock) {
        return false;
    }

    @Override
    public boolean update(BigInteger id, Overclock newOverclock) {
        return false;
    }
}
