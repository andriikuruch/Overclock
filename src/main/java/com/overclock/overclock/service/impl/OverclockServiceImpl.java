package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.service.OverclockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class OverclockServiceImpl implements OverclockService {
    private OverclockDAO overclockDAO;

    @Autowired
    public void setOverclockDAO(OverclockDAO overclockDAO) {
        this.overclockDAO = overclockDAO;
    }

    @Override
    public Overclock getOverclockById(int id) {
        return null;
    }

    @Override
    public boolean save(Overclock overclock) {
        return false;
    }

    @Override
    public boolean update(int id, Overclock newOverclock) {
        return false;
    }

    @Override
    public Overclock getDefaultValues(int overclockId, int assemblyId) {
        return null;
    }

    @Override
    public boolean validate(Overclock overclock) {
        return false;
    }
}
