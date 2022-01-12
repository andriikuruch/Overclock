package com.overclock.overclock.service;

import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;

import java.math.BigInteger;
import java.util.List;

public interface GPUService {
    GPU getById(BigInteger id);
    List<GPU> getAll();
    boolean save(GPU gpu);
    boolean updateById(BigInteger id, GPU newGpu);
    boolean delete(BigInteger id);
    List<GPUChip> getGPUChipsByManufacturer(GPUChipManufacturer manufacturer);
    List<GPU> getGPUsByChip(GPUChip chip);
    List<GPU> getGPUsByManufacturer(GPUChipManufacturer manufacturer);
    List<GPU> getGPUsByName(String name);
}