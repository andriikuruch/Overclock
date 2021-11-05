package com.overclock.overclock.service;

import com.overclock.overclock.model.GPU;

import java.math.BigInteger;
import java.util.List;

public interface GPUService {
    GPU getById(BigInteger id);
    List<GPU> getAll();
    boolean save(GPU gpu);
    boolean updateById(BigInteger id, GPU newGpu);
    boolean delete(BigInteger id);
}
