package com.overclock.overclock.dao;

import com.overclock.overclock.model.GPU;

import java.math.BigInteger;
import java.util.List;

public interface GPU_DAO {
    GPU getById(BigInteger id);
    List<GPU> getAll();
    boolean save(GPU gpu);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, GPU newGpu);
}
