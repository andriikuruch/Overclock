package com.overclock.overclock.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController implements ErrorController {
    @RequestMapping("/error")
    public String redirectToMain() {
        return "forward:/index.html";
    }
}
