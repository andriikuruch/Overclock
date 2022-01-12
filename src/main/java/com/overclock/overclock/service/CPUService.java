package com.overclock.overclock.service;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.enums.CPUFamily;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;

import java.math.BigInteger;
import java.util.List;

public interface CPUService {
    CPU getById(BigInteger id);
    List<CPU> getAll();
    boolean save(CPU cpu);
    boolean updateById(BigInteger id, CPU newCpu);
    boolean delete(BigInteger id);

    List<CPUSocket> getCPUSocketsByManufacturer(CPUManufacturer manufacturer);
    List<CPUGeneration> getCPUGenerationsBySocket(CPUSocket socket);
    List<CPUFamily> getCPUFamiliesByGeneration(CPUGeneration generation);

    List<CPU> getCPUsByManufacturer(CPUManufacturer manufacturer);
    List<CPU> getCPUsBySocket(CPUSocket socket);
    List<CPU> getCPUsByGeneration(CPUGeneration generation);
    List<CPU> getCPUsByFamily(CPUFamily family);
    List<CPU> getCPUsByName(String name);
}
