package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.service.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class MotherboardServiceImpl implements MotherboardService {
    private MotherboardDAO motherboardDAO;

    @Autowired
    public void setMotherboardDAO(MotherboardDAO motherboardDAO) {
        this.motherboardDAO = motherboardDAO;
    }

    @Override
    public Motherboard getById(BigInteger id) {
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
    public boolean updateById(BigInteger id, Motherboard newMotherboard) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }
}
