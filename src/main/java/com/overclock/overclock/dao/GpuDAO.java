package com.overclock.overclock.dao;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;

import java.math.BigInteger;
import java.util.List;

public interface GpuDAO {
    GPU getById(BigInteger id);
    GPU getByAssemblyId(BigInteger assemblyId);
    List<GPU> getAll();
    boolean save(GPU gpu);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, GPU newGpu);
}
