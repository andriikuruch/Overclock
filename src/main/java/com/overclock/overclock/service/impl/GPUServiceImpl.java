package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.GPU_DAO;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.service.GPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class GPUServiceImpl implements GPUService {
    private GPU_DAO gpuDAO;

    @Autowired
    public void setGpuDAO(GPU_DAO gpuDAO) {
        this.gpuDAO = gpuDAO;
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
    public boolean updateById(BigInteger id, GPU newGpu) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }
}
