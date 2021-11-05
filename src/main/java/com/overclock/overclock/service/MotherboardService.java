package com.overclock.overclock.service;

import com.overclock.overclock.model.Motherboard;

import java.math.BigInteger;
import java.util.List;

public interface MotherboardService {
    Motherboard getById(BigInteger id);
    List<Motherboard> getAll();
    boolean save(Motherboard motherboard);
    boolean updateById(BigInteger id, Motherboard newMotherboard);
    boolean delete(BigInteger id);
}
