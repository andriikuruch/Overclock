package com.overclock.overclock.service;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.Overclock;

public interface ValidationService {
    void isCompatibleMotherboardAndCPU(Motherboard motherboard, CPU cpu);
    void checkCompatibility(Assembly assembly);
    void isValidMotherboard(Motherboard motherboard);
    void isValidCPU(CPU cpu);
    void isValidGPU(GPU gpu);
    void checkOverclockValidity(Overclock overclock);
}
