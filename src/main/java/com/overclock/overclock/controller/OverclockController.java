package com.overclock.overclock.controller;

import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.service.OverclockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/v1/assembly")
public class OverclockController {
    @Autowired
    private OverclockService overclockService;

    @GetMapping(value = "/{assembly_id}/overclock/default")
    public Overclock getDefaultValues(@PathVariable("assembly_id") BigInteger assemblyId) {
        return overclockService.getDefaultOverclockValues(assemblyId);
    }

    @GetMapping("/{assembly_id}/overclock/{overclock_id}")
    public Overclock getOverclock(@PathVariable("overclock_id") BigInteger id) {
        return overclockService.getOverclockById(id);
    }

    @PostMapping("/{assembly_id}/overclock")
    public ResponseEntity<?> saveOverclock(@RequestBody Overclock overclock, @PathVariable("assembly_id") BigInteger assemblyId) {
        overclockService.save(overclock, assemblyId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{assembly_id}/overclock/{overclock_id}")
    public ResponseEntity<?> updateOverclock(@RequestBody Overclock newOverclock, @PathVariable("overclock_id") BigInteger id) {
        overclockService.update(id, newOverclock);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
