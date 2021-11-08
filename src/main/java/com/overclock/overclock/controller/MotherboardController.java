package com.overclock.overclock.controller;

import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.service.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/motherboard")
public class MotherboardController {
    private MotherboardService motherboardService;

    @Autowired
    public void setMotherboardService(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @DeleteMapping("/{motherboard_id}")
    public Boolean deleteMotherboard(@PathVariable("motherboard_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @PatchMapping("/{motherboard_id}")
    public Boolean updateMotherboard(@Valid @RequestBody Motherboard motherboard, @PathVariable("motherboard_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @PostMapping
    public Boolean addMotherboard(@Valid @RequestBody Motherboard motherboard, Authentication authentication) {
        return null;
    }

    @GetMapping("/chipset_manufacturers")
    public List<String> getMotherboardChipsetManufacturers() {
        return null;
    }

    @PostMapping("/sockets")
    public List<String> getMotherboardSockets(@RequestBody String chipsetManufacturer) {
        return null;
    }

    @PostMapping("/chipsets")
    public List<String> getMotherboardChipsets(@RequestBody String socket) {
        return null;
    }
}
