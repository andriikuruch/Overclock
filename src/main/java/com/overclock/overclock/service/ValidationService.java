package com.overclock.overclock.service;

import com.overclock.overclock.model.*;

public interface ValidationService {
    boolean isCompatibleMotherboardAndCPU(Motherboard motherboard, CPU cpu);
    boolean checkCompatibility(Assembly assembly);
    boolean isValidMotherboard(Motherboard motherboard);
    boolean isValidCPU(CPU cpu);
    boolean isValidGPU(GPU gpu);
}
