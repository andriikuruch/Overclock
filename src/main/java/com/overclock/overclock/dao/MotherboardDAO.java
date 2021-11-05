package com.overclock.overclock.dao;

import com.overclock.overclock.model.Motherboard;

import java.math.BigInteger;
import java.util.List;

public interface MotherboardDAO {
    Motherboard getById(BigInteger id);
    List<Motherboard> getAll();
    boolean save(Motherboard motherboard);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, Motherboard newMotherboard);
}
