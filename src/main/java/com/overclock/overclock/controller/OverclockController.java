package com.overclock.overclock.controller;

import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.service.OverclockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController("/api/v1/assembly")
public class OverclockController {
    private OverclockService overclockService;

    @Autowired
    public void setOverclockService(OverclockService overclockService) {
        this.overclockService = overclockService;
    }

    @GetMapping("/{assembly_id}/overclock/{overclock_id}/default")
    public Overclock getDefaultValues(@PathVariable("assembly_id") int overclockId,
                                      @PathVariable("overclock_id") int assemblyId) {
        return null;
    }

    @GetMapping("/{assembly_id}/overclock/{overclock_id}")
    public Overclock getOverclock(@PathVariable("overclock_id") int id) {
        return null;
    }

    @PostMapping("/{assembly_id}/overclock")
    public String save(@Valid @RequestBody Overclock overclock, Authentication authentication) {
        return null;
    }

    @PutMapping("/{assembly_id}/overclock/{overclock_id}")
    public String updateOverclock(@Valid @RequestBody Overclock newOverclock,
                                  @PathVariable("overclock_id") BigInteger id,
                                  Authentication authentication) {
        return null;
    }
}
