package com.overclock.overclock.service;

import com.overclock.overclock.model.Motherboard;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;

public interface MotherboardService {
    Motherboard getMotherboardById(BigInteger id);
    List<Motherboard> getAllMotherboards();
    boolean save(Motherboard motherboard);
    boolean updateById(BigInteger id, Motherboard newMotherboard);
    boolean delete(BigInteger id);
    List<String> getMotherboardChipsetManufacturers();
    List<String> getMotherboardSockets(String chipsetManufacturer);
    List<String> getMotherboardChipsets(String socket);
}
