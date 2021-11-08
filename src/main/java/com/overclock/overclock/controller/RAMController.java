package com.overclock.overclock.controller;

import com.overclock.overclock.model.RAM;
import com.overclock.overclock.service.RAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/RAM/")
public class RAMController {
    private RAMService RAMService;
    @Autowired
    public void setRAMService(RAMService RAMService){this.RAMService = RAMService;}

    @PostMapping
    public Boolean addRAM(@Valid @RequestBody RAM RAM, Authentication authentication){return null;}
    @PatchMapping("{ram_id}")
    public Boolean updateRAM(@Valid @RequestBody RAM RAM, @PathVariable("ram_id") int id, Authentication authentication){return null;}
    @DeleteMapping
    public Boolean deleteRAM(@Valid @RequestBody RAM RAM, Authentication authentication){return null;}
    @GetMapping
    public List<RAM> search(String string){return null;}
}

