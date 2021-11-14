package com.overclock.overclock.dao;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.Motherboard;

import java.math.BigInteger;
import java.util.List;

public interface MotherboardDAO {
    Motherboard getById(BigInteger id);
    Motherboard getByAssemblyId(BigInteger assemblyId);
    List<Motherboard> getAll();
    boolean save(Motherboard motherboard);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, Motherboard newMotherboard);
}
