package com.overclock.overclock.controller;

import com.overclock.overclock.model.RAM;
import com.overclock.overclock.service.RAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ram")
public class RAMController {
    private RAMService RAMService;

    @Autowired
    public void setRAMService(RAMService RAMService) {
        this.RAMService = RAMService;
    }

    @PostMapping
    public Boolean addRAM(@Valid @RequestBody RAM RAM, Authentication authentication) {
        return null;
    }

    @PutMapping("/{ram_id}")
    public Boolean updateRAM(@Valid @RequestBody RAM RAM, @PathVariable("ram_id") BigInteger id, Authentication authentication) {
        return null;
    }

    @DeleteMapping("/{ram_id}")
    public Boolean deleteRAM(@PathVariable("ram_id") BigInteger id, Authentication authentication) {
        return null;
    }
}

