package com.overclock.overclock.controller;

import com.overclock.overclock.model.GPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/gpu")
public class GPUController {
    private GPUController GPUController;

    @Autowired
    public void setGPUController(GPUController GPUController) {
        this.GPUController = GPUController;
    }

    @PostMapping
    public Boolean addGPU(@Valid @RequestBody GPU GPU, Authentication authentication) {
        return null;
    }

    @PutMapping("/{gpu_id}")
    public Boolean updateGPU(@Valid @RequestBody GPU  GPU, @PathVariable("gpu_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @DeleteMapping("/{gpu_id}")
    public Boolean deleteGPU(@PathVariable("gpu_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @GetMapping("/chip_manufacturers")
    public List<String> getGPUChipManufacturers() {
        return null;
    }

    @PostMapping("/chips")
    public List<String> getGPUChips(@RequestBody String chipManufacturer) {
        return null;
    }
}
