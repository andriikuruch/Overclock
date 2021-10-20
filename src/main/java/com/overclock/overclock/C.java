package com.overclock.overclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C {

    @GetMapping
    public String index() {
        return "hello";
    }
}
