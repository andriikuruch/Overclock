package com.overclock.overclock.service;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.model.RAM;
import com.overclock.overclock.model.enums.Program;

import java.math.BigDecimal;

public interface TestService {
    BigDecimal testAssembly(Assembly assembly);
    BigDecimal testAssemblyOnProgram(Assembly assembly, Program program);
    BigDecimal testCPU(CPU cpu, Overclock overclock);
    BigDecimal testGPU(GPU gpu, Overclock overclock);
    BigDecimal testRAM(RAM ram, Overclock overclock);
}
