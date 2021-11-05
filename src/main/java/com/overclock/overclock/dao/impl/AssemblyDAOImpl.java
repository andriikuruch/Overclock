package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class AssemblyDAOImpl implements AssemblyDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Assembly getById(BigInteger id) {
        return null;
    }

    @Override
    public Assembly getByAuthor(String author) {
        return null;
    }

    @Override
    public List<Assembly> getAll() {
        return null;
    }

    @Override
    public List<Assembly> getAllByAuthor(String author) {
        return null;
    }

    @Override
    public boolean save(Assembly assembly) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }
}
