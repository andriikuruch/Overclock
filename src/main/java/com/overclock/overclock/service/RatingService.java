package com.overclock.overclock.service;

import com.overclock.overclock.model.Assembly;

import java.util.List;

public interface RatingService {
    List<Assembly> calculateTopWithoutOverclock();
    List<Assembly> calculateTopWithOverclock();
}
