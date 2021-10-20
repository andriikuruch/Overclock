package com.overclock.overclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitControler {

    @GetMapping
    public String index() {
        return "HELLO";
    }
}
