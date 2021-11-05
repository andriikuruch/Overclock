package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.RatingService;
import com.overclock.overclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class RatingServiceImpl implements RatingService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Assembly> calculateTopWithoutOverclock() {
        return null;
    }

    @Override
    public List<Assembly> calculateTopWithOverclock() {
        return null;
    }
}
