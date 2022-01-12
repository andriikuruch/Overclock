package com.overclock.overclock.service;

import com.overclock.overclock.model.RAM;

import java.math.BigInteger;
import java.util.List;

public interface RAMService {
    RAM getById(BigInteger id);
    List<RAM> getAll();
    boolean save(RAM ram);
    boolean updateById(BigInteger id, RAM newRam);
    boolean delete(BigInteger id);
    List<RAM> getRAMsByName(String name);
}
