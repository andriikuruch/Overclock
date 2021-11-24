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
    @Autowired
    private MotherboardDAO motherboardDAO;

    @Override
    public Motherboard getById(BigInteger id) {
        return motherboardDAO.getById(id);
    }

    @Override
    public List<Motherboard> getAll() {
        return motherboardDAO.getAll();
    }

    @Override
    public boolean save(Motherboard motherboard) {
        return motherboardDAO.save(motherboard);
    }

    @Override
    public boolean updateById(BigInteger id, Motherboard newMotherboard) {
        return motherboardDAO.update(id, newMotherboard);
    }

    @Override
    public boolean delete(BigInteger id) {
        return motherboardDAO.delete(id);
    }
}
