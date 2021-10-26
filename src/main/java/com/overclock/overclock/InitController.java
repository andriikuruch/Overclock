package com.overclock.overclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @GetMapping
    public String index() {
        return "Hello! It's work!\n" +
                "spring.datasource.username" + System.getProperty("spring.datasource.username")
                + "\nserver.port=" + System.getProperty("server.port")
                + "\nspring.datasource.username=" + System.getProperty("spring.datasource.username")
                + "\nspring.datasource.password=" + System.getProperty("spring.datasource.password");
    }
}
