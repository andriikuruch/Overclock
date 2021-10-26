package com.overclock.overclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @GetMapping
    public String index() {
        return "Hello! It's work!" +
                "<br>spring.datasource.username=" + System.getProperty("spring.datasource.username")
                + "<br>server.port=" + System.getProperty("server.port")
                + "<br>spring.datasource.username=" + System.getProperty("spring.datasource.username")
                + "<br>spring.datasource.password=" + System.getProperty("spring.datasource.password");
    }
}
