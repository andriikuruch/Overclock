package com.overclock.overclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @GetMapping
    public String index() {
        return "Hello! It's work!" + "spring.datasource.username";
    }
}
