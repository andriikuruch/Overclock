package com.overclock.overclock.controller;

import com.overclock.overclock.model.*;
import com.overclock.overclock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/api/v1/test/")
public class TestController {
    private TestService testService;
    @Autowired
    public void setTestService(TestService testService){this.testService = testService;}

    @PostMapping
    public String testOverclock(Overclock overclock){return null;}
    @PostMapping
    public String testAssembly(Assembly assembly){return null;}
    @PostMapping
    public String testAssemblyOnProgram(Program program){return null;}
    @PostMapping
    public String testOverclockOnProgram(Program program){return null;}
    @PostMapping
    public BigInteger testCPU(CPU CPU){return null;}
    @PostMapping
    public BigInteger testGPU(GPU GPU){return null;}
    @PostMapping
    public BigInteger testRAM(RAM RAM){return null;}
}
