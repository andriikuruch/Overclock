package com.overclock.overclock.dao;

import com.overclock.overclock.model.CPU;

import java.math.BigInteger;
import java.util.List;

public interface CPU_DAO {
    CPU getById(BigInteger id);
    CPU getByAssemblyId(BigInteger assemblyId);
    List<CPU> getAll();
    boolean save(CPU cpu);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, CPU newCpu);
}
