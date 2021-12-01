package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
import com.overclock.overclock.service.ValidationService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isCompatibleMotherboardAndCPU(Motherboard motherboard, CPU cpu) {
        if (motherboard.getChipsetManufacturer().name().equals(cpu.getManufacturer().name()) &&
                motherboard.getSocket().name().equals(cpu.getSocket().name())) {
            if (isValidComponent(motherboard.getChipset().toInt(), 3, 5)) {
                return isValidComponent(cpu.getGeneration().toInt(), 36, 37);
            }
            if (isValidComponent(motherboard.getChipset().toInt(), 6, 8)) {
                return isValidComponent(cpu.getGeneration().toInt(), CPUGeneration.TenGen.toInt(), CPUGeneration.TenGen.toInt());
            }
            if (isValidComponent(motherboard.getChipset().toInt(), 9, 11)) {
                return isValidComponent(cpu.getGeneration().toInt(), 38, 39);
            }
            if (isValidComponent(motherboard.getChipset().toInt(), 12, 19)) {
                return isValidComponent(cpu.getGeneration().toInt(), 40, 43);
            }
        }
        return false;
    }

    @Override
    public boolean checkCompatibility(Assembly assembly) {
        return isCompatibleMotherboardAndCPU(assembly.getMotherboard(), assembly.getCpu());
    }

    @Override
    public boolean isValidMotherboard(Motherboard motherboard) {
        if (motherboard.getChipsetManufacturer() == ChipsetManufacturer.Intel) {
            if (motherboard.getSocket() == MotherboardSocket.Soc1151) {
                return isValidComponent(motherboard.getChipset().toInt(), 3, 5);
            }
            if (motherboard.getSocket() == MotherboardSocket.Soc1200) {
                return isValidComponent(motherboard.getChipset().toInt(), 6, 11);
            }
        }
        if (motherboard.getChipsetManufacturer() == ChipsetManufacturer.AMD) {
            return motherboard.getSocket() == MotherboardSocket.AM4 &&
                    isValidComponent(motherboard.getChipset().toInt(), 12, 19);
        }
        return false;
    }

    @Override
    public boolean isValidCPU(CPU cpu) {
        if (cpu.getManufacturer() == CPUManufacturer.Intel &&
                isValidComponent(cpu.getFamily().toInt(), 25, 28)) {
            if (cpu.getSocket() == CPUSocket.Soc1151) {
                return isValidComponent(cpu.getGeneration().toInt(), 36, 37);
            }
            if (cpu.getSocket() == CPUSocket.Soc1200) {
                return isValidComponent(cpu.getGeneration().toInt(), 38, 39);
            }
        }
        if (cpu.getManufacturer() == CPUManufacturer.AMD &&
                isValidComponent(cpu.getFamily().toInt(), 29, 32)) {
            return cpu.getSocket() == CPUSocket.AM4 &&
                    isValidComponent(cpu.getGeneration().toInt(), 40, 43);
        }
        return false;
    }

    @Override
    public boolean isValidGPU(GPU gpu) {
        if (gpu.getChipManufacturer() == GPUChipManufacturer.Nvidia) {
            return isValidComponent(gpu.getChip().toInt(), 46, 72);
        }
        if (gpu.getChipManufacturer() == GPUChipManufacturer.AMD) {
            return isValidComponent(gpu.getChip().toInt(), 73, 86);
        }
        return false;
    }

    private boolean isValidComponent(int value, int lower, int greater) {
        return value >= lower && value <= greater;
    }
}