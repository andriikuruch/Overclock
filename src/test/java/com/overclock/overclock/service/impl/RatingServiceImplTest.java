package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.RatingService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RatingServiceImplTest {
    @Autowired
    private RatingService ratingService;

    @Test
    public void calculateTopWithoutOverclock() {
        List<BigInteger> expected = new ArrayList<>();
        expected.add(BigInteger.valueOf(1600));
        expected.add(BigInteger.valueOf(1500));

        List<Assembly> assemblies = ratingService.calculateTopWithoutOverclock();
        Assertions.assertNotNull(assemblies);

        List<BigInteger> actual = new ArrayList<>();
        for (Assembly assembly: assemblies) {
            Assertions.assertNull(assembly.getOverclock());
            actual.add(assembly.getScore());
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void calculateTopWithOverclock() {
        List<BigInteger> expected = new ArrayList<>();
        expected.add(BigInteger.valueOf(6000));
        expected.add(BigInteger.valueOf(3500));

        List<Assembly> assemblies = ratingService.calculateTopWithOverclock();
        Assertions.assertNotNull(assemblies);

        List<BigInteger> actual = new ArrayList<>();
        for (Assembly assembly: assemblies) {
            Assertions.assertNotNull(assembly.getOverclock());
            actual.add(assembly.getScore());
        }
        Assertions.assertEquals(expected, actual);
    }
}