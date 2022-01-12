package com.overclock.overclock.controller;

import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.service.GPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gpu")
public class GPUController {

    @Autowired
    private GPUService gpuService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addGPU(@Valid @RequestBody GPU gpu) {
        gpuService.save(gpu);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<GPU> getAllGPUs() {
        return gpuService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{gpu_id}")
    public ResponseEntity<?> updateGPU(@Valid @RequestBody GPU gpu, @PathVariable("gpu_id") BigInteger id) {
        gpuService.updateById(id, gpu);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{gpu_id}")
    public ResponseEntity<?> deleteGPU(@PathVariable("gpu_id") BigInteger id) {
        gpuService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{gpu_id}")
    public GPU getGPUById(@PathVariable("gpu_id") BigInteger id) {
        return gpuService.getById(id);
    }

    @GetMapping("/chip_manufacturers")
    public List<String> getGPUChipManufacturers() {
        List<String> manufacturers = new ArrayList<>();
        for (GPUChipManufacturer manufacturer : GPUChipManufacturer.values()) {
            manufacturers.add(manufacturer.name());
        }
        return manufacturers;
    }

    @PostMapping("/chips")
    public List<String> getGPUChips(@RequestBody String chipManufacturer) {
        List<String> chips = new ArrayList<>();
        for (GPUChip chip : gpuService.getGPUChipsByManufacturer(GPUChipManufacturer.valueOf(chipManufacturer))) {
            chips.add(chip.name());
        }
        return chips;
    }

    @PostMapping("/by_chip")
    public List<GPU> getGPUsByChip(@RequestBody String chip) {
        return gpuService.getGPUsByChip(GPUChip.valueOf(chip));
    }

    @PostMapping("/by_manufacturer")
    public List<GPU> getGPUsByManufacturer(@RequestBody String manufacturer) {
        return gpuService.getGPUsByManufacturer(GPUChipManufacturer.valueOf(manufacturer));
    }

    @GetMapping("/search/{name}")
    public List<GPU> getGPUsByName(@PathVariable("name") String name) {
        return gpuService.getGPUsByName(name);
    }
}