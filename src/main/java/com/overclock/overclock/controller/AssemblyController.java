package com.overclock.overclock.controller;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.util.RequestAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/assembly")
public class AssemblyController {
    private AssemblyService assemblyService;

    @Autowired
    public void setAssemblyService(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @PostMapping
    public String buildAssembly(@Valid @RequestBody RequestAssembly requestAssembly, Authentication authentication) {
        return null;
    }

    @GetMapping("{assembly_id}")
    public Assembly showAssembly(@PathVariable("assembly_id") BigInteger id) {
        return null;
    }

    @GetMapping("/search")
    public List<Assembly> search(@PathVariable("search") String search) {
        return null;
    }

    @GetMapping("/all")
    public List<Assembly> getAll() {
        return null;
    }

    @GetMapping
    public List<Assembly> getAllAssembliesByUser(Authentication authentication) {
        return null;
    }
}
