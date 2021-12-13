package com.overclock.overclock.controller;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/default")
    public List<Assembly> viewTopByDefault() {
        return ratingService.calculateTopWithoutOverclock();
    }

    @GetMapping
    public List<Assembly> viewTopByOverclock() {
        return ratingService.calculateTopWithOverclock();
    }
}
