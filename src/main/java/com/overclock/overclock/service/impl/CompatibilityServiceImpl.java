package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.service.CompatibilityService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class CompatibilityServiceImpl implements CompatibilityService {

    @Override
    public boolean isCompatibleMotherboardAndCPU(Motherboard motherboard, CPU cpu) {
        return false;
    }

    @Override
    public boolean checkCompatibility(Assembly assembly) {
        return false;
    }
}
