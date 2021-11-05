package com.overclock.overclock.service;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.Motherboard;

public interface CompatibilityService {
    boolean isCompatibleMotherboardAndCPU(Motherboard motherboard, CPU cpu);
    boolean checkCompatibility(Assembly assembly);
}
