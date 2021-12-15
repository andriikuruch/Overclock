package com.overclock.overclock.controller;

import com.overclock.overclock.model.RAM;
import com.overclock.overclock.service.RAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ram")
public class RAMController {

    @Autowired
    private RAMService RAMService;

    @PostMapping
    public ResponseEntity<?> addRAM(@RequestBody RAM RAM) {
        RAMService.save(RAM);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<RAM> getAllRAMs() {
        return RAMService.getAll();
    }

    @GetMapping("/{ram_id}")
    public RAM getRAM(@PathVariable("ram_id") BigInteger id) {
        return RAMService.getById(id);
    }

    @PutMapping("/{ram_id}")
    public ResponseEntity<?> updateRAM(@RequestBody RAM RAM, @PathVariable("ram_id") BigInteger id) {
        RAMService.updateById(id, RAM);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{ram_id}")
    public boolean deleteRAM(@PathVariable("ram_id") BigInteger id) {
        return RAMService.delete(id);
    }
}

