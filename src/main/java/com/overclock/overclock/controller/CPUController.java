package com.overclock.overclock.controller;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/CPU/")
public class CPUController {
    private CPUService CPUService;
    @Autowired
    public void setCPUService(CPUService CPUService){this.CPUService = CPUService;}

    @PostMapping()
    public Boolean addCPU(@Valid @RequestBody CPU CPU, Authentication authentication){return null;}
    @PatchMapping("{cpu_id}")
    public Boolean updateCPU(@Valid @RequestBody CPU CPU,  @PathVariable("cpu_id") int id, Authentication authentication){return null;}
    @DeleteMapping
    public Boolean deleteCPU(@Valid @RequestBody CPU CPU, Authentication authentication){return null;}
    @GetMapping
    public List<String> getCPUManufacturers(){return null;}
    @GetMapping
    public List<String> getCPUSockets(String manufacturer){return null;}
    @GetMapping
    public List<String> getCPUGenerations(String socket){return null;}
    @GetMapping
    public List<String> getCPUFamilies(String generation){return null;}
    @PostMapping
    public List<CPU> search(String string){return null;}
}
