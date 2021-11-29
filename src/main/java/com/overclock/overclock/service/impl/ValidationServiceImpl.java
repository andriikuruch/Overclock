package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.*;
import com.overclock.overclock.model.enums.*;
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
            if (motherboard.getChipset().toInt() >= 3 && motherboard.getChipset().toInt() <= 5) {
                return cpu.getGeneration().toInt() >= 36 && cpu.getGeneration().toInt() <= 37;
            }
            if (motherboard.getChipset().toInt() >= 6 && motherboard.getChipset().toInt() <= 8) {
                return cpu.getGeneration() == CPUGeneration.TenGen;
            }
            if (motherboard.getChipset().toInt() >= 9 && motherboard.getChipset().toInt() <= 11) {
                return cpu.getGeneration().toInt() >= 38 && cpu.getGeneration().toInt() <= 39;
            }
            if (motherboard.getChipset().toInt() >= 12 && motherboard.getChipset().toInt() <= 19) {
                return cpu.getGeneration().toInt() >= 40 && cpu.getGeneration().toInt() <= 43;
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
                return motherboard.getChipset().toInt() >= 3 && motherboard.getChipset().toInt() <= 5;
            }
            if (motherboard.getSocket() == MotherboardSocket.Soc1200) {
                return motherboard.getChipset().toInt() >= 6 && motherboard.getChipset().toInt() <= 11;
            }
        }
        if (motherboard.getChipsetManufacturer() == ChipsetManufacturer.AMD) {
            return motherboard.getSocket() == MotherboardSocket.AM4 &&
                    motherboard.getChipset().toInt() >= 12 && motherboard.getChipset().toInt() <= 19;
        }
        return false;
    }

    @Override
    public boolean isValidCPU(CPU cpu) {
        if (cpu.getManufacturer() == CPUManufacturer.Intel &&
                cpu.getFamily().toInt() >= 25 && cpu.getFamily().toInt() <= 28) {
            if (cpu.getSocket() == CPUSocket.Soc1151) {
                return cpu.getGeneration().toInt() >= 36 && cpu.getGeneration().toInt() <= 37;
            }
            if (cpu.getSocket() == CPUSocket.Soc1200) {
                return cpu.getGeneration().toInt() >= 38 && cpu.getGeneration().toInt() <= 39;
            }
        }
        if (cpu.getManufacturer() == CPUManufacturer.AMD &&
                cpu.getFamily().toInt() >= 29 && cpu.getFamily().toInt() <= 32) {
            return cpu.getSocket() == CPUSocket.AM4 &&
                    cpu.getGeneration().toInt() >= 40 && cpu.getGeneration().toInt() <= 43;
        }
        return false;
    }

    @Override
    public boolean isValidGPU(GPU gpu) {
        if (gpu.getChipManufacturer() == GPUChipManufacturer.Nvidia) {
            return gpu.getChip().toInt() >= 46 && gpu.getChip().toInt() <= 72;
        }
        if (gpu.getChipManufacturer() == GPUChipManufacturer.AMD) {
            return gpu.getChip().toInt() >= 73 && gpu.getChip().toInt() <= 86;
        }
        return false;
    }
}