package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.GPU_DAO;
import com.overclock.overclock.model.GPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class GPU_DAOImpl implements GPU_DAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GPU getById(BigInteger id) {
        return null;
    }

    @Override
    public List<GPU> getAll() {
        return null;
    }

    @Override
    public boolean save(GPU gpu) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public boolean update(BigInteger id, GPU newGpu) {
        return false;
    }
}
