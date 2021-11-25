package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.GpuDAO;
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

    private GpuDAO gpuDAO;

    @Autowired
    public void setGpuDAO(GpuDAO gpuDAO) {
        this.gpuDAO = gpuDAO;
    }

    @Override
    public GPU getById(BigInteger id) {
        return gpuDAO.getById(id);
    }

    @Override
    public List<GPU> getAll() {
        return gpuDAO.getAll();
    }

    @Override
    public boolean save(GPU gpu) {
        return gpuDAO.save(gpu);
    }

    @Override
    public boolean updateById(BigInteger id, GPU newGpu) {
        return gpuDAO.update(id, newGpu);
    }

    @Override
    public boolean delete(BigInteger id) {
        return gpuDAO.delete(id);
    }
}