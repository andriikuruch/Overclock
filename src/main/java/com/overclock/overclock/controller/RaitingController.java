package com.overclock.overclock.controller;

import com.overclock.overclock.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/raiting/")
public class RaitingController {
    private RatingService ratingService;
    @Autowired
    public void setRatingService(RatingService ratingService){this.ratingService = ratingService;}

    @GetMapping
    public String viewTopByDefault(){return null;}
    @GetMapping
    public String viewTopByOverclock(){return null;}
}
