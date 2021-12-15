package com.overclock.overclock.controller;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.model.enums.Program;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.OverclockService;
import com.overclock.overclock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private AssemblyService assemblyService;
    @Autowired
    private OverclockService overclockService;

    @GetMapping("/assembly/{assembly_id}")
    public BigDecimal testAssembly(@PathVariable("assembly_id") BigInteger assemblyId) {
        return testService.testAssembly(assemblyService.getAssemblyById(assemblyId));
    }

    @GetMapping(("/assembly/{assembly_id}/{program}"))
    public BigDecimal testAssemblyOnProgram(@PathVariable("assembly_id") BigInteger assemblyId,
                                                   @PathVariable("program") Program program) {
        return testService.testAssemblyOnProgram(assemblyService.getAssemblyById(assemblyId), program);
    }

    @GetMapping("/assembly/{assembly_id}/cpu")
    public BigDecimal testCPU(@PathVariable("assembly_id") BigInteger assemblyId) {
        Assembly assembly = assemblyService.getAssemblyById(assemblyId);
        Overclock overclock = overclockService.getOverclockById(assembly.getOverclock());
        return testService.testCPU(assembly.getCpu(), overclock);
    }

    @GetMapping("/assembly/{assembly_id}/gpu")
    public BigDecimal testGPU(@PathVariable("assembly_id") BigInteger assemblyId) {
        Assembly assembly = assemblyService.getAssemblyById(assemblyId);
        Overclock overclock = overclockService.getOverclockById(assembly.getOverclock());
        return  testService.testGPU(assembly.getGpu(), overclock);
    }

    @GetMapping("/assembly/{assembly_id}/ram")
    public BigDecimal testRAM(@PathVariable("assembly_id") BigInteger assemblyId) {
        Assembly assembly = assemblyService.getAssemblyById(assemblyId);
        Overclock overclock = overclockService.getOverclockById(assembly.getOverclock());
        return testService.testRAM(assembly.getRam(), overclock);
    }
}
