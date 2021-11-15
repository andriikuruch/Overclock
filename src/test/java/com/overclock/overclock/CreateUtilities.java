package com.overclock.overclock;

import com.overclock.overclock.model.*;
import com.overclock.overclock.model.enums.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CreateUtilities {
    public static Motherboard createMotherboard() {
        return new Motherboard.Builder(BigInteger.valueOf(3), "Asus Prime B560M-A")
                .setSocket(MotherboardSocket.Soc1200)
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.B560)
                .build();
    }

    public static CPU createCpu() {
        return new CPU.Builder(BigInteger.valueOf(5), "Intel Core i7")
                .setManufacturer(CPUManufacturer.Intel)
                .setSocket(CPUSocket.Soc1200)
                .setGeneration(CPUGeneration.TenGen)
                .setFamily(CPUFamily.Core_i7)
                .setVoltage(BigDecimal.valueOf(125))
                .setFrequency(BigDecimal.valueOf(3800))
                .setThreadsNumber(BigInteger.valueOf(16))
                .setCoresNumber(BigInteger.valueOf(8))
                .build();
    }

    public static GPU createGpu() {
        return new GPU.Builder(BigInteger.valueOf(7), "Asus ROG STRIX GTX1080 A8G GAMING")
                .setChipManufacturer(GPUChipManufacturer.Nvidia)
                .setChip(GPUChip.GeForce_GTX_1080)
                .setCoreFrequency(BigInteger.valueOf(1695))
                .setMemoryFrequency(BigInteger.valueOf(10010))
                .setMemoryCapacity(BigInteger.valueOf(8))
                .setVoltage(BigDecimal.valueOf(500))
                .build();
    }

    public static RAM createRAM() {
        return new RAM.Builder(BigInteger.valueOf(9), "HyperX DDR4-2666 16384MB PC4-21300")
                .setTimings("77-7-21")
                .setFrequency(BigInteger.valueOf(2666))
                .setCapacity(BigInteger.valueOf(16))
                .setVoltage(BigDecimal.valueOf(1.2))
                .build();
    }

    public static Assembly createAssembly() {
        return new Assembly.Builder(BigInteger.valueOf(1), "Assembly1")
                .setRam(createRAM())
                .setGpu(createGpu())
                .setCpu(createCpu())
                .setMotherboard(createMotherboard())
                .setScore(BigInteger.valueOf(3500))
                .setAuthor(BigInteger.valueOf(12))
                .build();
    }

    public static Assembly createAssemblyForSaving() {
        return new Assembly.Builder(BigInteger.valueOf(3), "Assembly3")
                .setRam(createRAM())
                .setGpu(createGpu())
                .setCpu(createCpu())
                .setMotherboard(createMotherboard())
                .setScore(BigInteger.valueOf(5600))
                .setAuthor(BigInteger.valueOf(12))
                .build();
    }

    public static Assembly createBadAssemblyForSaving() {
        return new Assembly.Builder(BigInteger.valueOf(4), "Assembly4")
                .setRam(createRAM())
                .setGpu(createGpu())
                .setCpu(createCpu())
                .setMotherboard(createMotherboard())
                .setScore(BigInteger.valueOf(5600))
                .setAuthor(BigInteger.valueOf(-1))
                .build();
    }
}
