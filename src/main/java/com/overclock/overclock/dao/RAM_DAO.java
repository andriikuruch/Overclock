package com.overclock.overclock.dao;

import com.overclock.overclock.model.RAM;

import java.math.BigInteger;
import java.util.List;

public interface RAM_DAO {
    RAM getById(BigInteger id);
    List<RAM> getAll();
    boolean save(RAM ram);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, RAM newRam);
}
