package com.overclock.overclock;

import com.overclock.overclock.model.*;
import com.overclock.overclock.model.enums.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateUtilities {
    public static Motherboard createMotherboard() {
        return new Motherboard.Builder(BigInteger.valueOf(3), "Asus Prime B560M-A")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.B560)
                .setSocket(MotherboardSocket.Soc1200)
                .build();
    }

    public static Motherboard createBadMotherboard() {
        return new Motherboard.Builder(BigInteger.valueOf(3), "Bad Motherboard")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
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

    public static CPU createBadCPU() {
        return new CPU.Builder(BigInteger.valueOf(5), "Bad CPU")
                .setManufacturer(CPUManufacturer.Intel)
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

    public static RAM createBadRAM() {
        return new RAM.Builder(BigInteger.valueOf(9), "Bad RAM")
                .setVoltage(BigDecimal.valueOf(1.0))
                .build();
    }

    public static Assembly createAssemblyWithFullInformation() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date dateOfComment = null;

        try {
            dateOfComment = dateFormat.parse("12/10/2021 17:34");
        } catch (ParseException e) {

        }

        List<Comment> comments = Arrays.asList(
                new Comment.Builder()
                        .setId(BigInteger.valueOf(15))
                        .setAuthor("user1")
                        .setDateOfComment(dateOfComment)
                        .setCommentMessage("OMG!")
                        .build(),
                new Comment.Builder()
                        .setId(BigInteger.valueOf(14))
                        .setAuthor("user1")
                        .setDateOfComment(dateOfComment)
                        .setCommentMessage("Good!")
                        .build()
        );

        return new Assembly.Builder(BigInteger.valueOf(1), "Assembly1")
                .setRam(createRAM())
                .setGpu(createGpu())
                .setCpu(createCpu())
                .setMotherboard(createMotherboard())
                .setScore(BigInteger.valueOf(3500))
                .setAuthor(BigInteger.valueOf(12))
                .setOverclock(BigInteger.valueOf(11))
                .setComments(comments)
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

    public static Comment createComment() {
        return new Comment.Builder()
                .setId(BigInteger.valueOf(18))
                .setCommentMessage("Test Message")
                .setAuthor("Mike")
                .setDateOfComment(new Date())
                .build();
    }

    public static Comment createBadComment() {
        return new Comment.Builder()
                .setCommentMessage("Bad Message")
                .build();
    }

    public static Overclock createOverclock() {
        return new Overclock.Builder()
                .setId(BigInteger.valueOf(1))
                .setCPUFrequency(BigDecimal.valueOf(4.0))
                .setCPUVoltage(BigDecimal.valueOf(1.5))
                .setGPUCoreFrequency(BigInteger.valueOf(1500))
                .setGPUMemoryFrequency(BigInteger.valueOf(18000))
                .setGPUVoltage(BigDecimal.valueOf(2.0))
                .setRAMVoltage(BigDecimal.valueOf(2.5))
                .setRAMTimings("5-5-5-15")
                .setRAMFrequency(BigInteger.valueOf(3112)).build();
    }

    public static Overclock createBadOverclock() {
        return new Overclock.Builder()
                .setId(BigInteger.valueOf(1))
                .setCPUFrequency(BigDecimal.valueOf(4.0))
                .setGPUVoltage(BigDecimal.valueOf(2.0))
                .setRAMFrequency(BigInteger.valueOf(3112)).build();
    }
}
