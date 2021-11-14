package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CPU_DAO;
import com.overclock.overclock.model.CPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class CPU_DAOImpl implements CPU_DAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CPU getById(BigInteger id) {
        return null;
    }

    @Override
    public CPU getByAssemblyId(BigInteger assemblyId) {
        return null;
    }

    @Override
    public List<CPU> getAll() {
        return null;
    }

    @Override
    public boolean save(CPU cpu) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public boolean update(BigInteger id, CPU newCpu) {
        return false;
    }
}
