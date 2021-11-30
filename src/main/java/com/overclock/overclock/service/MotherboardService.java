package com.overclock.overclock.service;

import com.overclock.overclock.model.Motherboard;

import java.math.BigInteger;
import java.util.List;

public interface MotherboardService {
    Motherboard getMotherboardById(BigInteger id);
    List<Motherboard> getAllMotherboards();
    boolean save(Motherboard motherboard);
    boolean updateById(BigInteger id, Motherboard newMotherboard);
    boolean delete(BigInteger id);
}
