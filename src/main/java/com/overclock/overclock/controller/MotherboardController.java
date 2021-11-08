package com.overclock.overclock.controller;

import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.service.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/motherboard/")
public class MotherboardController {
    private MotherboardService motherboardService;
    @Autowired
    public void setMotherboardService(MotherboardService motherboardService){this.motherboardService = motherboardService;}

    @DeleteMapping
    public Boolean deleteMotherboard(Motherboard motherboard, Authentication authentication){return null;}
    @PatchMapping("{motherborad_id}")
    public Boolean updateMotherboard(Motherboard motherboard, @PathVariable("motherborad_id") int id, Authentication authentication){return null;}
    @PostMapping
    public Boolean addMotherboard(Motherboard motherboard, Authentication authentication){return null;}
    @GetMapping
    public List<String> getMotherboardChipsetManufacturers(){return null;}
    @GetMapping
    public List<String> getMotherboardSockets(String chipsetManufacturer){return null;}
    @GetMapping
    public List<String> getMotherboardChipsets(String socket){return null;}
    public List<Motherboard> search(String string){return null;}
}
