package com.overclock.overclock.service;

import com.overclock.overclock.model.CPU;

import java.math.BigInteger;
import java.util.List;

public interface CPUService {
    CPU getById(BigInteger id);
    List<CPU> getAll();
    boolean save(CPU cpu);
    boolean updateById(BigInteger id, CPU newCpu);
    boolean delete(BigInteger id);
}
