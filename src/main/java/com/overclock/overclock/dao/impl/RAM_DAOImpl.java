package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.RAM_DAO;
import com.overclock.overclock.model.RAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class RAM_DAOImpl implements RAM_DAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RAM getById(BigInteger id) {
        return null;
    }

    @Override
    public RAM getByAssemblyId(BigInteger assemblyId) {
        return null;
    }

    @Override
    public List<RAM> getAll() {
        return null;
    }

    @Override
    public boolean save(RAM ram) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public boolean update(BigInteger id, RAM newRam) {
        return false;
    }
}
