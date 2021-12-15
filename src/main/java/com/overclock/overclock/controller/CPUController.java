package com.overclock.overclock.controller;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.enums.*;
import com.overclock.overclock.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cpu")
public class CPUController {

    @Autowired
    private CPUService CPUService;

    @PostMapping
    public ResponseEntity<?> addCPU(@Valid @RequestBody CPU cpu) {
        CPUService.save(cpu);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<CPU> getAllCPUs() {
        return CPUService.getAll();
    }

    @PutMapping("/{cpu_id}")
    public ResponseEntity<?> updateCPU(@Valid @RequestBody CPU cpu, @PathVariable("cpu_id") BigInteger id) {
        CPUService.updateById(id, cpu);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{cpu_id}")
    public ResponseEntity<?> deleteCPU(@PathVariable("cpu_id") BigInteger id) {
        CPUService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{cpu_id}")
    public CPU getCPUById(@PathVariable("cpu_id") BigInteger id) {
        return CPUService.getById(id);
    }

    @GetMapping("/manufacturers")
    public List<String> getCPUManufacturers() {
        List<String> manufacturers = new ArrayList<>();
        for (CPUManufacturer manufacturer : CPUManufacturer.values()) {
            manufacturers.add(manufacturer.name());
        }
        return manufacturers;
    }

    @PostMapping("/sockets")
    public List<String> getCPUSockets(@RequestBody String manufacturer) {
        List<String> sockets = new ArrayList<>();
        for (CPUSocket socket : CPUService.getCPUSocketsByManufacturer(CPUManufacturer.valueOf(manufacturer))) {
            sockets.add(socket.name());
        }
        return sockets;
    }

    @PostMapping("/generations")
    public List<String> getCPUGenerations(@RequestBody String socket) {
        List<String> generations = new ArrayList<>();
        for (CPUGeneration generation : CPUService.getCPUGenerationsBySocket(CPUSocket.valueOf(socket))) {
            generations.add(generation.name());
        }
        return generations;
    }

    @PostMapping("/families")
    public List<String> getCPUFamilies(@RequestBody String generation) {
        List<String> families = new ArrayList<>();
        for (CPUFamily family : CPUService.getCPUFamiliesByGeneration(CPUGeneration.valueOf(generation))) {
            families.add(family.name());
        }
        return families;
    }

    @PostMapping("/by_socket")
    public List<CPU> getCPUsBySocket(@RequestBody String socket) {
        return CPUService.getCPUsBySocket(CPUSocket.valueOf(socket));
    }

    @PostMapping("/by_manufacturer")
    public List<CPU> getCPUsByManufacturer(@RequestBody String manufacturer) {
        return CPUService.getCPUsByManufacturer(CPUManufacturer.valueOf(manufacturer));
    }

    @PostMapping("/by_generation")
    public List<CPU> getCPUsByGeneration(@RequestBody String generation) {
        return CPUService.getCPUsByGeneration(CPUGeneration.valueOf(generation));
    }

    @PostMapping("/by_family")
    public List<CPU> getCPUsByFamily(@RequestBody String family) {
        return CPUService.getCPUsByFamily(CPUFamily.valueOf(family));
    }
}
