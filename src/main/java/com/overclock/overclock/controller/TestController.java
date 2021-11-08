package com.overclock.overclock.controller;

import com.overclock.overclock.model.*;
import com.overclock.overclock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/overclock/{overclock_id}")
    public String testOverclock(@PathVariable("assembly_id") BigInteger id) {
        return null;
    }

    @GetMapping("/assembly/{assembly_id}")
    public String testAssembly(@PathVariable("assembly_id") BigInteger id) {
        return null;
    }

    @GetMapping(("/assembly/{assembly_id}/{program}"))
    public String testAssemblyOnProgram(@PathVariable("assembly_id") BigInteger id, @PathVariable("program") Program program) {
        return null;
    }

    @GetMapping("/overclock/{overclock_id}/{program}")
    public String testOverclockOnProgram(@PathVariable("assembly_id") BigInteger id, @PathVariable("program") Program program) {
        return null;
    }

    @GetMapping("/{cpu_id}")
    public BigInteger testCPU(@PathVariable("cpu_id") BigInteger id) {
        return null;
    }

    @GetMapping("/{gpu_id}")
    public BigInteger testGPU(@PathVariable("gpu_id") BigInteger id) {
        return null;
    }

    @GetMapping("/{ram_id}")
    public BigInteger testRAM(@PathVariable("ram_id") BigInteger id) {
        return null;
    }
}
