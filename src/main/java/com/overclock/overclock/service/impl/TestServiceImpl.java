package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.*;
import com.overclock.overclock.model.enums.Program;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TestServiceImpl implements TestService {
    private AssemblyService assemblyService;

    @Autowired
    public void setAssemblyService(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @Override
    public int testOverclock(Overclock overclock) {
        return 0;
    }

    @Override
    public int testAssembly(Assembly assembly) {
        return 0;
    }

    @Override
    public int testAssemblyOnProgram(Program program) {
        return 0;
    }

    @Override
    public int testOverclockOnProgram(Program program) {
        return 0;
    }

    @Override
    public int testCPU(CPU cpu) {
        return 0;
    }

    @Override
    public int testGPU(GPU gpu) {
        return 0;
    }

    @Override
    public int tstRAM(RAM ram) {
        return 0;
    }
}
