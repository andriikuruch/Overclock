package com.overclock.overclock.controller;

import com.overclock.overclock.model.GPU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/GPU/")
public class GPUController {
    private GPUController GPUController;
    @Autowired
    public void setGPUController(GPUController GPUController){this.GPUController = GPUController;}

    @PostMapping
    public Boolean addGPU(@Valid @RequestBody GPU GPU, Authentication authentication){return null;}
    @PatchMapping("{gpu_id}")
    public Boolean updateGPU(@Valid @RequestBody GPU  GPU, @PathVariable("gpu_id") int id, Authentication authentication){return null;}
    @DeleteMapping
    public Boolean deleteGPU(@Valid @RequestBody GPU  GPU, Authentication authentication){return null;}
    @GetMapping
    public List<String> getGPUChipManufacturers(){return null;}
    @GetMapping
    public List<String> getGPUChips(String chipManufacturer){return null;}
    @PostMapping
    public List<GPU> search(String string){return null;}
}
