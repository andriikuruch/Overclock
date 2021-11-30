package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.RatingService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceImplTest {
    @InjectMocks
    private RatingService ratingService = new RatingServiceImpl();
    @Mock
    private AssemblyService assemblyServiceMock;
    private List<Assembly> testAssemblies = new ArrayList<>();

    @Before
    public void init() {
        Assembly assembly1 = new Assembly.Builder(BigInteger.valueOf(1), "testAssembly1")
                .setOverclock(BigInteger.valueOf(11)).setScore(BigInteger.valueOf(2500)).build();
        Assembly assembly2 = new Assembly.Builder(BigInteger.valueOf(2), "testAssembly2")
                .build();
        Assembly assembly3 = new Assembly.Builder(BigInteger.valueOf(3), "testAssembly3")
                .setOverclock(BigInteger.valueOf(12)).setScore(BigInteger.valueOf(1200)).build();
        Assembly assembly4 = new Assembly.Builder(BigInteger.valueOf(4), "testAssembly4")
                .setOverclock(BigInteger.valueOf(13)).setScore(BigInteger.valueOf(1600)).build();
        Assembly assembly5 = new Assembly.Builder(BigInteger.valueOf(5), "testAssembly5")
                .setScore(BigInteger.valueOf(1750)).build();
        Assembly assembly6 = new Assembly.Builder(BigInteger.valueOf(6), "testAssembly6")
                .setScore(BigInteger.valueOf(2800)).build();

        testAssemblies = new ArrayList<>(Arrays.asList(assembly1, assembly2, assembly3, assembly4, assembly5, assembly6));
    }

    @Test
    public void calculateTopWithoutOverclock() {
        List<Assembly> expected = new ArrayList<>();
        expected.add(testAssemblies.get(5));
        expected.add(testAssemblies.get(4));

        when(assemblyServiceMock.getAllAssemblies()).thenReturn(testAssemblies);

        List<Assembly> actual = ratingService.calculateTopWithoutOverclock();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void calculateTopWithOverclock() {
        List<Assembly> expected = new ArrayList<>();
        expected.add(testAssemblies.get(0));
        expected.add(testAssemblies.get(3));
        expected.add(testAssemblies.get(2));

        when(assemblyServiceMock.getAllAssemblies()).thenReturn(testAssemblies);

        List<Assembly> actual = ratingService.calculateTopWithOverclock();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}