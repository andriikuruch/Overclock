package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.model.RAM;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.model.enums.Program;
import com.overclock.overclock.service.OverclockService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {
    @Autowired
    @InjectMocks
    private TestServiceImpl testService;
    @Mock
    private OverclockService overclockServiceMock;
    private static Overclock overclock;
    private static CPU cpu;
    private static GPU gpu;
    private static RAM ram;

    @BeforeClass
    public static void init() {
        overclock = new Overclock();

        cpu = new CPU.Builder(BigInteger.valueOf(1), "cpu")
                .setFrequency(BigDecimal.valueOf(3.7))
                .setCoresNumber(BigInteger.valueOf(10))
                .setThreadsNumber(BigInteger.valueOf(20))
                .setManufacturer(CPUManufacturer.Intel)
                .setGeneration(CPUGeneration.ElevenGen)
                .build();
        gpu = new GPU.Builder(BigInteger.valueOf(2), "gpu")
                .setMemoryFrequency(BigInteger.valueOf(15000))
                .setCoreFrequency(BigInteger.valueOf(1500))
                .setMemoryCapacity(BigInteger.valueOf(24))
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.RX_6900_XT)
                .build();
        ram = new RAM.Builder(BigInteger.valueOf(3), "ram")
                .setFrequency(BigInteger.valueOf(3200))
                .setCapacity(BigInteger.valueOf(64))
                .setTimings("19-19-19-38").build();
    }

    @Test
    public void testCPUIncreaseFreq() {
        double score = testService.testCPU(cpu, null).doubleValue();

        overclock.setCPUFrequency(BigDecimal.valueOf(3.8));
        double scoreAfterOverclock = testService.testCPU(cpu, overclock).doubleValue();

        Assert.assertTrue(scoreAfterOverclock > score);
    }

    @Test
    public void testCPUDecreaseFreq() {
        double score = testService.testCPU(cpu, null).doubleValue();

        overclock.setCPUFrequency(BigDecimal.valueOf(3.6));
        double scoreAfterOverclock = testService.testCPU(cpu, overclock).doubleValue();

        Assert.assertTrue(scoreAfterOverclock < score);
    }

    @Test
    public void testCPUTheSameOverclockFreq() {
        double score = testService.testCPU(cpu, null).doubleValue();

        overclock.setCPUFrequency(BigDecimal.valueOf(3.7));
        double scoreAfterOverclock = testService.testCPU(cpu, overclock).doubleValue();

        Assert.assertEquals(scoreAfterOverclock, score, 0.0);
    }

    @Test
    public void testCPUWhenThrottling() {
        double maxFreqOverclock = cpu.getFrequency().doubleValue() * 1.09;

        overclock.setCPUFrequency(BigDecimal.valueOf(maxFreqOverclock));
        double score = testService.testCPU(cpu, overclock).doubleValue();

        overclock.setCPUFrequency(BigDecimal.valueOf(maxFreqOverclock + 0.1));
        double scoreAfterThrottling = testService.testCPU(cpu, overclock).doubleValue();

        Assert.assertTrue(score > scoreAfterThrottling);
    }

    @Test
    public void testGPUIncreaseCoreAndMemoryFreq() {
        double score = testService.testGPU(gpu, null).doubleValue();

        overclock.setGPUCoreFrequency(BigInteger.valueOf(1520));
        overclock.setGPUMemoryFrequency(BigInteger.valueOf(15500));
        double scoreAfterOverclock = testService.testGPU(gpu, overclock).doubleValue();

        Assert.assertTrue(scoreAfterOverclock > score);
    }

    @Test
    public void testGPUDecreaseCoreAndMemoryFreq() {
        double score = testService.testGPU(gpu, null).doubleValue();

        overclock.setGPUCoreFrequency(BigInteger.valueOf(1400));
        overclock.setGPUMemoryFrequency(BigInteger.valueOf(15000));
        double scoreAfterOverclockCoreFreq = testService.testGPU(gpu, overclock).doubleValue();
        Assert.assertTrue(scoreAfterOverclockCoreFreq < score);

        overclock.setGPUCoreFrequency(BigInteger.valueOf(1500));
        overclock.setGPUMemoryFrequency(BigInteger.valueOf(14000));
        double scoreAfterOverclockMemFreq = testService.testGPU(gpu, overclock).doubleValue();
        Assert.assertTrue(scoreAfterOverclockMemFreq < score);
    }

    @Test
    public void testGPUTheSameOverclockFreq() {
        double score = testService.testGPU(gpu, null).doubleValue();

        overclock.setGPUCoreFrequency(BigInteger.valueOf(1500));
        overclock.setGPUMemoryFrequency(BigInteger.valueOf(15000));
        double scoreAfterOverclock = testService.testGPU(gpu, overclock).doubleValue();

        Assert.assertEquals(scoreAfterOverclock, score, 0.0);
    }

    @Test
    public void testGPUWhenThrottling() {
        double maxCoreFreqOverclock = gpu.getCoreFrequency().intValue() * 1.085;
        double maxMemFreqOverclock = gpu.getMemoryFrequency().intValue() * 1.14;

        overclock.setGPUCoreFrequency(BigInteger.valueOf(Math.round(maxCoreFreqOverclock)));
        overclock.setGPUMemoryFrequency(BigInteger.valueOf(Math.round(maxMemFreqOverclock)));
        double score = testService.testGPU(gpu, overclock).doubleValue();

        overclock.setGPUCoreFrequency(BigInteger.valueOf(Math.round(maxCoreFreqOverclock) + 1));
        overclock.setGPUMemoryFrequency(BigInteger.valueOf(Math.round(maxMemFreqOverclock) + 1));
        double scoreAfterThrottling = testService.testGPU(gpu, overclock).doubleValue();

        Assert.assertTrue(score > scoreAfterThrottling);
    }

    @Test
    public void testRAMDecreaseTimings() {
        double score = testService.testRAM(ram, null).doubleValue();
        overclock.setRAMTimings("18-18-18-36");
        overclock.setRAMFrequency(BigInteger.valueOf(3200));
        double scoreAfterOverclock = testService.testRAM(ram, overclock).doubleValue();

        Assert.assertTrue(scoreAfterOverclock > score);
    }

    @Test
    public void testRAMIncreaseFrequency() {
        double score = testService.testRAM(ram, null).doubleValue();

        overclock.setRAMTimings("19-19-19-38");
        overclock.setRAMFrequency(BigInteger.valueOf(3600));
        double scoreAfterOverclock = testService.testRAM(ram, overclock).doubleValue();

        Assert.assertTrue(scoreAfterOverclock > score);
    }

    @Test
    public void testAssembly() {
        Assembly assembly = new Assembly.Builder(BigInteger.valueOf(1), "assembly")
                .setRam(ram).setGpu(gpu).setCpu(cpu).build();
        double score = testService.testAssembly(assembly).doubleValue();

        Assert.assertTrue(score > 0);
    }

    @Test
    public void testAssemblyOnProgram() {
        Assembly assembly = new Assembly.Builder(BigInteger.valueOf(1), "assembly")
                .setRam(ram).setGpu(gpu).setCpu(cpu).build();

        when(overclockServiceMock.getOverclockById(any())).thenReturn(null);

        double scoreInCSGO = testService.testAssemblyOnProgram(assembly, Program.CS_GO).doubleValue();
        double scoreInMetro = testService.testAssemblyOnProgram(assembly, Program.Metro_Exodus).doubleValue();
        double scoreInBlander = testService.testAssemblyOnProgram(assembly, Program.Blander).doubleValue();
        double scoreInAdobePremiere = testService.testAssemblyOnProgram(assembly, Program.AdobePremiere).doubleValue();

        Assert.assertNotEquals(scoreInCSGO, scoreInMetro);
        Assert.assertNotEquals(scoreInBlander, scoreInAdobePremiere);
    }
}