package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.CpuDAO;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class CPUServiceImpl implements CPUService {
    private CpuDAO cpuDAO;

    @Autowired
    public void setCpuDAO(CpuDAO cpuDAO) {
        this.cpuDAO = cpuDAO;
    }

    @Override
    public CPU getById(BigInteger id) {
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
    public boolean updateById(BigInteger id, CPU newCpu) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }
}
