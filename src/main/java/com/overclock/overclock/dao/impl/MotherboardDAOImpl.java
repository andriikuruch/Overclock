package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.model.Motherboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class MotherboardDAOImpl implements MotherboardDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Motherboard getById(BigInteger id) {
        return null;
    }

    @Override
    public Motherboard getByAssemblyId(BigInteger assemblyId) {
        return null;
    }

    @Override
    public List<Motherboard> getAll() {
        return null;
    }

    @Override
    public boolean save(Motherboard motherboard) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public boolean update(BigInteger id, Motherboard newMotherboard) {
        return false;
    }
}
