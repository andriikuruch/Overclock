package com.overclock.overclock.service.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.service.OverclockService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OverclockServiceImplTest {
    @Autowired
    private OverclockService overclockService;

    @Test
    public void validateValidOverclock() {
        Assertions.assertTrue(overclockService.isValidOverclock(CreateUtilities.createOverclock()));
    }

    @Test
    public void validateInvalidOverclock() {
        Assertions.assertFalse(overclockService.isValidOverclock(CreateUtilities.createOverclockWithWrongTimings()));
    }
}