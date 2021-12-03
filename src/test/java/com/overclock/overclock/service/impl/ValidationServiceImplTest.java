package com.overclock.overclock.service.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.CPUFamily;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;
import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
import com.overclock.overclock.util.exception.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidationServiceImplTest {

    @Autowired
    ValidationServiceImpl service;

    @Test
    public void isCompatibleMotherboardAndCPUValid() throws RuntimeException {
        Motherboard motherboard1 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 1")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.Z490)
                .setSocket(MotherboardSocket.Soc1200)
                .build();
        CPU cpu1 = new CPU.Builder(BigInteger.ZERO, "cpu 2")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Core_i5)
                .setSocket(CPUSocket.Soc1200)
                .setGeneration(CPUGeneration.TenGen)
                .build();
        Motherboard motherboard2 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 2")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.H310)
                .setSocket(MotherboardSocket.Soc1151)
                .build();
        CPU cpu2 = new CPU.Builder(BigInteger.ZERO, "cpu 2")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Core_i5)
                .setSocket(CPUSocket.Soc1151)
                .setGeneration(CPUGeneration.EightGen)
                .build();
        Motherboard motherboard3 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 3")
                .setChipsetManufacturer(ChipsetManufacturer.AMD)
                .setChipset(Chipset.X370)
                .setSocket(MotherboardSocket.AM4)
                .build();
        CPU cpu3 = new CPU.Builder(BigInteger.ZERO, "cpu 3")
                .setManufacturer(CPUManufacturer.AMD)
                .setFamily(CPUFamily.Ryzen3)
                .setSocket(CPUSocket.AM4)
                .setGeneration(CPUGeneration.ZenPlus)
                .build();

        service.isCompatibleMotherboardAndCPU(motherboard1, cpu1);
        service.isCompatibleMotherboardAndCPU(motherboard2, cpu2);
        service.isCompatibleMotherboardAndCPU(motherboard3, cpu3);

    }

    @Test(expected = RuntimeException.class)
    public void isCompatibleMotherboardAndCPUInvalid1() throws RuntimeException {
        Motherboard motherboard1 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 1")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.Z490)
                .setSocket(MotherboardSocket.Soc1200)
                .build();
        CPU cpu1 = new CPU.Builder(BigInteger.ZERO, "cpu 1")
                .setManufacturer(CPUManufacturer.AMD)
                .setFamily(CPUFamily.Ryzen3)
                .setSocket(CPUSocket.AM4)
                .setGeneration(CPUGeneration.ZenPlus)
                .build();
        service.isCompatibleMotherboardAndCPU(motherboard1, cpu1);
    }

    @Test(expected = RuntimeException.class)
    public void isCompatibleMotherboardAndCPUInvalid2() throws RuntimeException {
        Motherboard motherboard1 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 1")
                .setChipsetManufacturer(ChipsetManufacturer.AMD)
                .setChipset(Chipset.X370)
                .setSocket(MotherboardSocket.AM4)
                .build();
        CPU cpu1 = new CPU.Builder(BigInteger.ZERO, "cpu 1")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Core_i5)
                .setSocket(CPUSocket.Soc1151)
                .setGeneration(CPUGeneration.EightGen)
                .build();
        service.isCompatibleMotherboardAndCPU(motherboard1, cpu1);
    }

    @Test
    public void isValidMotherboardValid() throws RuntimeException {
        Motherboard motherboard1 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 1")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.H310)
                .setSocket(MotherboardSocket.Soc1151)
                .build();
        Motherboard motherboard2 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 2")
                .setChipsetManufacturer(ChipsetManufacturer.AMD)
                .setChipset(Chipset.X370)
                .setSocket(MotherboardSocket.AM4)
                .build();
        service.isValidMotherboard(motherboard1);
        service.isValidMotherboard(motherboard2);
    }

    @Test(expected = RuntimeException.class)
    public void isValidMotherboardInvalid1() throws RuntimeException {
        Motherboard motherboard1 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 1")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setChipset(Chipset.A320)
                .setSocket(MotherboardSocket.Soc1151)
                .build();
        service.isValidMotherboard(motherboard1);
    }

    @Test(expected = RuntimeException.class)
    public void isValidMotherboardInvalid2() throws RuntimeException {
        Motherboard motherboard1 = new Motherboard.Builder(BigInteger.ZERO, "motherboard 1")
                .setChipsetManufacturer(ChipsetManufacturer.AMD)
                .setChipset(Chipset.X370)
                .setSocket(MotherboardSocket.Soc1200)
                .build();
        service.isValidMotherboard(motherboard1);
    }

    @Test
    public void isValidCPUValid() throws RuntimeException {
        CPU cpu1 = new CPU.Builder(BigInteger.ZERO, "cpu 1")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Core_i5)
                .setSocket(CPUSocket.Soc1200)
                .setGeneration(CPUGeneration.TenGen)
                .build();
        CPU cpu2 = new CPU.Builder(BigInteger.ZERO, "cpu 2")
                .setManufacturer(CPUManufacturer.AMD)
                .setFamily(CPUFamily.Ryzen3)
                .setSocket(CPUSocket.AM4)
                .setGeneration(CPUGeneration.ZenPlus)
                .build();
        service.isValidCPU(cpu1);
        service.isValidCPU(cpu2);
    }

    @Test(expected = RuntimeException.class)
    public void isValidCPUInvalid1() throws RuntimeException {
        CPU cpu1 = new CPU.Builder(BigInteger.ZERO, "cpu 1")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Ryzen3)
                .setSocket(CPUSocket.Soc1200)
                .setGeneration(CPUGeneration.TenGen)
                .build();
        service.isValidCPU(cpu1);
    }

    @Test(expected = RuntimeException.class)
    public void isValidCPUInvalid2() throws RuntimeException {
        CPU cpu1 = new CPU.Builder(BigInteger.ZERO, "cpu 2")
                .setManufacturer(CPUManufacturer.AMD)
                .setFamily(CPUFamily.Ryzen3)
                .setSocket(CPUSocket.Soc1151)
                .setGeneration(CPUGeneration.ZenPlus)
                .build();
        service.isValidCPU(cpu1);
    }

    @Test
    public void isValidGPUValid() throws RuntimeException {
        GPU gpu1 = new GPU.Builder(BigInteger.ZERO, "gpu 1")
                .setChipManufacturer(GPUChipManufacturer.Nvidia)
                .setChip(GPUChip.GeForce_GTX_1080)
                .build();
        GPU gpu2 = new GPU.Builder(BigInteger.ZERO, "gpu 2")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.RX_6600)
                .build();
        service.isValidGPU(gpu1);
        service.isValidGPU(gpu2);
    }

    @Test(expected = RuntimeException.class)
    public void isValidGPUInvalid1() throws RuntimeException {
        GPU gpu1 = new GPU.Builder(BigInteger.ZERO, "gpu 1")
                .setChipManufacturer(GPUChipManufacturer.Nvidia)
                .setChip(GPUChip.RX_6800_XT)
                .build();
        service.isValidGPU(gpu1);
    }

    @Test(expected = RuntimeException.class)
    public void isValidGPUInvalid2() throws RuntimeException {
        GPU gpu1 = new GPU.Builder(BigInteger.ZERO, "gpu 1")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.GeForce_GTX_1080_Ti)
                .build();
        service.isValidGPU(gpu1);
    }

    @Test
    public void checkValidOverclockValidity() {
        service.checkOverclockValidity(CreateUtilities.createOverclock());
    }

    @Test(expected = ValidationException.class)
    public void validateInvalidOverclock() {
        service.checkOverclockValidity(CreateUtilities.createOverclockWithWrongTimings());
    }
}