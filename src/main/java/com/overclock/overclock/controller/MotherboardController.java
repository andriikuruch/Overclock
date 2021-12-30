package com.overclock.overclock.controller;

import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.service.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.List;

@RestController
@RequestMapping("/api/motherboard")
public class MotherboardController {
    private MotherboardService motherboardService;

    @Autowired
    public void setMotherboardService(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @DeleteMapping("/{motherboard_id}")
    public ResponseEntity<?> deleteMotherboard(@PathVariable("motherboard_id") BigInteger id) {
        motherboardService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Motherboard> getAllMotherboards() {
        return motherboardService.getAllMotherboards();
    }

    @GetMapping("/{motherboard_id}")
    public Motherboard getMotherboardById(@PathVariable("motherboard_id") BigInteger id) {
        return motherboardService.getMotherboardById(id);
    }

    @PutMapping("/{motherboard_id}")
    public ResponseEntity<?> updateMotherboard(@Valid @RequestBody Motherboard motherboard, @PathVariable("motherboard_id") BigInteger id) {
        motherboardService.updateById(id, motherboard);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addMotherboard(@Valid @RequestBody Motherboard motherboard) {
        motherboardService.save(motherboard);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/chipset_manufacturers")
    public List<String> getMotherboardChipsetManufacturers() {
        return motherboardService.getMotherboardChipsetManufacturers();
    }

    @GetMapping("/sockets/{chipset_manufacturer}")
    public List<String> getMotherboardSockets(@PathVariable("chipset_manufacturer") String chipsetManufacturer) {
        return motherboardService.getMotherboardSockets(chipsetManufacturer);
    }

    @GetMapping("/chipsets/{socket}")
    public List<String> getMotherboardChipsets(@PathVariable("socket") String socket) {
        return motherboardService.getMotherboardChipsets(socket);
    }
}
