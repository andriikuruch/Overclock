package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.*;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
import com.overclock.overclock.service.ValidationService;
import com.overclock.overclock.util.exception.ValidationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Scope("singleton")
public class ValidationServiceImpl implements ValidationService {

    private static final Logger LOGGER = Logger.getLogger(ValidationServiceImpl.class.getName());

    @Override
    public void isCompatibleMotherboardAndCPU(Motherboard motherboard, CPU cpu) {
        if (!motherboard.getChipsetManufacturer().name().equals(cpu.getManufacturer().name()) ||
                !motherboard.getSocket().name().equals(cpu.getSocket().name()) ||
                ((!isValidComponent(motherboard.getChipset().toInt(), 3, 5) || !isValidComponent(cpu.getGeneration().toInt(), 36, 37)) &&
                        (!isValidComponent(motherboard.getChipset().toInt(), 6, 8) ||
                                !isValidComponent(cpu.getGeneration().toInt(), CPUGeneration.TenGen.toInt(), CPUGeneration.TenGen.toInt())) &&
                        (!isValidComponent(motherboard.getChipset().toInt(), 9, 11) || !isValidComponent(cpu.getGeneration().toInt(), 38, 39)) &&
                        (!isValidComponent(motherboard.getChipset().toInt(), 12, 19) || !isValidComponent(cpu.getGeneration().toInt(), 40, 43)))) {
            logAndThrowValidationException("Motherboard and CPU is not compatibility", new AbstractComponent[]{motherboard, cpu});
        }
    }

    @Override
    public void checkCompatibility(Assembly assembly) {
        isCompatibleMotherboardAndCPU(assembly.getMotherboard(), assembly.getCpu());
    }

    @Override
    public void isValidMotherboard(Motherboard motherboard) {
        if (((motherboard.getChipsetManufacturer() != ChipsetManufacturer.Intel ||
                (motherboard.getSocket() != MotherboardSocket.Soc1151 ||
                        !isValidComponent(motherboard.getChipset().toInt(), 3, 5))) &&
                (motherboard.getSocket() != MotherboardSocket.Soc1200 ||
                        !isValidComponent(motherboard.getChipset().toInt(), 6, 11))) &&
                (motherboard.getChipsetManufacturer() != ChipsetManufacturer.AMD ||
                        motherboard.getSocket() != MotherboardSocket.AM4 ||
                        !isValidComponent(motherboard.getChipset().toInt(), 12, 19))) {
            logAndThrowValidationException("Motherboard is not valid", motherboard);
        }
    }

    @Override
    public void isValidCPU(CPU cpu) {
        if ((cpu.getManufacturer() != CPUManufacturer.Intel ||
                !isValidComponent(cpu.getFamily().toInt(), 25, 28) ||
                ((cpu.getSocket() != CPUSocket.Soc1151 ||
                        !isValidComponent(cpu.getGeneration().toInt(), 36, 37)) &&
                        (cpu.getSocket() != CPUSocket.Soc1200 ||
                                !isValidComponent(cpu.getGeneration().toInt(), 38, 39)))) &&
                (cpu.getManufacturer() != CPUManufacturer.AMD ||
                        !isValidComponent(cpu.getFamily().toInt(), 29, 32) ||
                        cpu.getSocket() != CPUSocket.AM4 ||
                        !isValidComponent(cpu.getGeneration().toInt(), 40, 43))) {
            logAndThrowValidationException("CPU is not valid", cpu);
        }
    }

    @Override
    public void isValidGPU(GPU gpu) {
        if ((gpu.getChipManufacturer() != GPUChipManufacturer.Nvidia ||
                !isValidComponent(gpu.getChip().toInt(), 46, 72)) &&
                (gpu.getChipManufacturer() != GPUChipManufacturer.AMD ||
                        !isValidComponent(gpu.getChip().toInt(), 73, 86))) {
            logAndThrowValidationException("GPU is not valid", gpu);
        }
    }

    @Override
    public void checkOverclockValidity(Overclock overclock) {
        if (overclock == null) {
            logAndThrowValidationException("Overclock is null", overclock);
        }
        if (overclock.getCPUVoltage() == null ||
                overclock.getCPUVoltage().doubleValue() < 0.5 || overclock.getCPUVoltage().doubleValue() > 3.0) {
            logAndThrowValidationException("Overclock CPU Voltage is invalid: " + overclock.getCPUVoltage(), overclock);
        }
        if (overclock.getCPUFrequency() == null ||
                overclock.getCPUFrequency().doubleValue() < 1.0 || overclock.getCPUFrequency().doubleValue() > 8.0) {
            logAndThrowValidationException("Overclock CPU Frequency is invalid: " + overclock.getCPUFrequency(), overclock);
        }
        if (overclock.getGPUVoltage() == null ||
                overclock.getGPUVoltage().doubleValue() < 0.5 || overclock.getGPUVoltage().doubleValue() > 3.0) {
            logAndThrowValidationException("Overclock GPU Voltage is invalid: " + overclock.getGPUVoltage(), overclock);
        }
        if (overclock.getGPUMemoryFrequency() == null ||
                overclock.getGPUMemoryFrequency().intValue() < 1000 || overclock.getGPUMemoryFrequency().intValue() > 230000) {
            logAndThrowValidationException("Overclock GPU Memory Frequency is invalid: " + overclock.getGPUMemoryFrequency(), overclock);
        }
        if (overclock.getGPUCoreFrequency() == null ||
                overclock.getGPUCoreFrequency().intValue() < 1000 || overclock.getGPUCoreFrequency().intValue() > 2000) {
            logAndThrowValidationException("Overclock GPU Core Frequency is invalid: " + overclock.getGPUCoreFrequency(), overclock);
        }
        if (overclock.getRAMVoltage() == null ||
                overclock.getRAMVoltage().doubleValue() < 0.5 || overclock.getRAMVoltage().doubleValue() > 3.0) {
            logAndThrowValidationException("Overclock RAM Voltage is invalid: " + overclock.getRAMVoltage(), overclock);
        }
        if (overclock.getRAMFrequency() == null ||
                overclock.getRAMFrequency().intValue() < 2133 || overclock.getRAMFrequency().intValue() > 5000) {
            logAndThrowValidationException("Overclock RAM Frequency is invalid: " + overclock.getRAMFrequency(), overclock);
        }
        if (overclock.getRAMTimings() == null ||
                !overclock.getRAMTimings().matches("(([1-3]\\d-)|(40-)){3}(([2-5]\\d)|(60))")) {
            logAndThrowValidationException("Overclock RAM Timings is invalid: " + overclock.getRAMTimings(), overclock);
        }
    }

    private void logAndThrowValidationException(String message, Object object) {
        ValidationException validationException = new ValidationException(message, object);
        LOGGER.log(Level.WARNING, validationException.getMessage(), validationException);
        throw validationException;
    }

    private boolean isValidComponent(int value, int lower, int greater) {
        return value >= lower && value <= greater;
    }
}