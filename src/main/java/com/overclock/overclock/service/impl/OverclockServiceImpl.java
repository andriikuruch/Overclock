package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.service.OverclockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Scope("singleton")
public class OverclockServiceImpl implements OverclockService {
    private OverclockDAO overclockDAO;

    @Autowired
    public void setOverclockDAO(OverclockDAO overclockDAO) {
        this.overclockDAO = overclockDAO;
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

    @Override
    public Overclock getDefaultValues(BigInteger overclockId, BigInteger assemblyId) {
        return null;
    }

    @Override
    public boolean validate(Overclock overclock) {
        return false;
    }
}
