package com.overclock.overclock.service;

import com.overclock.overclock.model.*;
import com.overclock.overclock.model.enums.Program;

public interface TestService {
    int testOverclock(Overclock overclock);
    int testAssembly(Assembly assembly);
    int testAssemblyOnProgram(Program program);
    int testOverclockOnProgram(Program program);
    int testCPU(CPU cpu);
    int testGPU(GPU gpu);
    int tstRAM(RAM ram);
}
