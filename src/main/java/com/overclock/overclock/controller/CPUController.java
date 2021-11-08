package com.overclock.overclock.controller;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cpu")
public class CPUController {
    private CPUService CPUService;

    @Autowired
    public void setCPUService(CPUService CPUService) {
        this.CPUService = CPUService;
    }

    @PostMapping
    public Boolean addCPU(@Valid @RequestBody CPU CPU, Authentication authentication) {
        return null;
    }

    @PutMapping("/{cpu_id}")
    public Boolean updateCPU(@Valid @RequestBody CPU CPU,  @PathVariable("cpu_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @DeleteMapping("/{cpu_id}")
    public Boolean deleteCPU(@PathVariable("cpu_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @GetMapping("/manufacturers")
    public List<String> getCPUManufacturers() {
        return null;
    }

    @PostMapping("/sockets")
    public List<String> getCPUSockets(@RequestBody String manufacturer) {
        return null;
    }

    @PostMapping("/generations")
    public List<String> getCPUGenerations(@RequestBody String socket) {
        return null;
    }

    @PostMapping("/families")
    public List<String> getCPUFamilies(@RequestBody String generation) {
        return null;
    }
}
