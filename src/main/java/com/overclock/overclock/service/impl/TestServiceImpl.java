package com.overclock.overclock.service.impl;

import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.RAM;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.model.enums.Program;
import com.overclock.overclock.service.OverclockService;
import com.overclock.overclock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Scope("singleton")
public class TestServiceImpl implements TestService {
    @Autowired
    private OverclockService overclockService;

    @Override
    public BigDecimal testAssembly(Assembly assembly) {
        BigDecimal score = BigDecimal.ZERO;
        for (Program program : Program.values()) {
            score = score.add(testAssemblyOnProgram(assembly, program));
        }
        return score.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal testAssemblyOnProgram(Assembly assembly, Program program) {
        Overclock overclock = overclockService.getOverclockById(assembly.getOverclock());
        switch (program) {
            case Blander:
                return BigDecimal.valueOf(calculateScoreOnProgram(assembly, overclock, 0.4, 0.2, 0.4));
            case Adobe_Premiere:
                return BigDecimal.valueOf(calculateScoreOnProgram(assembly, overclock, 0.5, 0.2, 0.3));
            case CS_GO:
                return BigDecimal.valueOf(calculateScoreOnProgram(assembly, overclock, 0.45, 0.4, 0.15));
            case Metro_Exodus:
                return BigDecimal.valueOf(calculateScoreOnProgram(assembly, overclock, 0.325, 0.425, 0.25));
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal testCPU(CPU cpu, Overclock overclock) {
        double freq = cpu.getFrequency().doubleValue();
        if (overclock != null) {
            freq = recalculateFreq(cpu, overclock);
        }
        double numberCore = cpu.getCoresNumber().intValue();
        double numberThread = cpu.getThreadsNumber().intValue();

        int boostGeneration;
        if (cpu.getManufacturer() == CPUManufacturer.Intel) {
            boostGeneration = cpu.getGeneration().toInt() - CPUGeneration.EightGen.toInt();
        } else {
            boostGeneration = cpu.getGeneration().toInt() - CPUGeneration.Zen.toInt();
        }

        double score = (freq * (numberCore * (numberThread / Math.sqrt(2))) / Math.sqrt(2));
        return BigDecimal.valueOf((score + score * (boostGeneration / 10.0)) * 10).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal testGPU(GPU gpu, Overclock overclock) {
        double coreFreq = gpu.getCoreFrequency().doubleValue();
        double memoryFreq = gpu.getMemoryFrequency().doubleValue();

        if (overclock != null) {
            double overFreqCore = overclock.getGPUCoreFrequency().doubleValue();
            double maxFreqCore = 2000;
            double minFreqCore = gpu.getCoreFrequency().doubleValue();
            double normalizeCore = (overFreqCore - minFreqCore) / (maxFreqCore - minFreqCore);
            double normalizeExpCore = (Math.exp(normalizeCore + Math.sqrt(2)) - 1) / (Math.exp(Math.sqrt(2) + 1) - 1);
            double resultFreqCore = overFreqCore;
            if (overFreqCore > minFreqCore * 1.085) {
                resultFreqCore = overFreqCore - overFreqCore * normalizeExpCore;
            }
            coreFreq = resultFreqCore;

            double overFreqMem = overclock.getGPUMemoryFrequency().doubleValue();
            double maxFreqMem = 23000;
            double minFreqMem = gpu.getMemoryFrequency().doubleValue();
            double normalizeMem = (overFreqMem - minFreqMem) / (maxFreqMem - minFreqMem);
            double normalizeExpMem = (Math.exp(normalizeMem + Math.sqrt(2)) - 1) / (Math.exp(Math.sqrt(2) + 1) - 1);
            double resultFreqMem = overFreqMem;
            if (overFreqMem > minFreqMem * 1.14) {
                resultFreqMem = overFreqMem - overFreqMem * normalizeExpMem;
            }
            memoryFreq = resultFreqMem;
        }

        double memCapacity = gpu.getMemoryCapacity().intValue();
        double normalizeMemFreq = memoryFreq / 23000;
        double normalizeCoreFreq = coreFreq / 2000;
        int chip;
        double normalizeChip;

        if (gpu.getChipManufacturer() == GPUChipManufacturer.AMD) {
            chip = gpu.getChip().toInt();
            double range = (GPUChip.RX_6900_XT.toInt() - GPUChip.RX_550.toInt());
            normalizeChip = (chip - GPUChip.RX_550.toInt()) / range;
        } else {
            chip = gpu.getChip().toInt();
            double range = (GPUChip.GeForce_RTX_3090.toInt() - GPUChip.GeForce_GT_1030.toInt());
            normalizeChip = (chip - GPUChip.GeForce_GT_1030.toInt()) / range;
        }

        double score = (normalizeMemFreq * normalizeCoreFreq * 200) * (memCapacity / Math.sqrt(2));
        return BigDecimal.valueOf(score + score * normalizeChip).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal testRAM(RAM ram, Overclock overclock) {
        double freq = ram.getFrequency().doubleValue();
        String timings = ram.getTimings();

        if (overclock != null) {
            freq = recalculateFreq(ram, overclock);
            timings = overclock.getRAMTimings();
        }

        int memCapacity = ram.getCapacity().intValue();
        String[] timingsArray = timings.split("-");
        int tCL = Integer.parseInt(timingsArray[0]);
        int tRCD = Integer.parseInt(timingsArray[1]);
        int tRP = Integer.parseInt(timingsArray[2]);
        int tRAS = Integer.parseInt(timingsArray[3]);
        double avgT = (tCL + tRCD + tRP + tRAS * 0.5) / 4 * 0.95;
        double maxPossibleFrequency = avgT * 3200 / 15;

        if (maxPossibleFrequency < freq) {
            freq = maxPossibleFrequency;
        }

        double speed = avgT * 2000 / freq;
        return BigDecimal.valueOf((1 / speed) * 1000 * (memCapacity / Math.sqrt(2))).setScale(2, RoundingMode.HALF_UP);
    }

    private double recalculateFreq(RAM ram, Overclock overclock) {
        double overFreq = overclock.getRAMFrequency().doubleValue();
        double maxFreq = 5000;
        double minFreq = ram.getFrequency().doubleValue();
        double normalize = (overFreq - minFreq) / (maxFreq - minFreq);
        double normalizeExp = (Math.exp(normalize + Math.sqrt(2)) - 1) / (Math.exp(Math.sqrt(2) + 1) - 1);
        double resultFreq = overFreq;
        if (overFreq > minFreq * 1.2) {
            resultFreq = overFreq - overFreq * normalizeExp;
        }
        return resultFreq;
    }

    private double recalculateFreq(CPU cpu, Overclock overclock) {
        double overFreq = overclock.getCPUFrequency().doubleValue();
        double maxFreq = 6;
        double minFreq = cpu.getFrequency().doubleValue();
        double normalize = (overFreq - minFreq) / (maxFreq - minFreq);
        double normalizeExp = (Math.exp(normalize + Math.sqrt(2)) - 1) / (Math.exp(Math.sqrt(2) + 1) - 1);
        double resultFreq = overFreq;
        if (overFreq > minFreq * 1.09) {
            resultFreq = overFreq - overFreq * normalizeExp;
        }
        return resultFreq;
    }

    private double calculateScoreOnProgram(Assembly assembly, Overclock overclock,
                                           double impactCPU, double impactGPU, double impactRAM) {
        double score = 0;
        score += testCPU(assembly.getCpu(), overclock).doubleValue() * impactCPU;
        score += testGPU(assembly.getGpu(), overclock).doubleValue() * impactGPU;
        score += testRAM(assembly.getRam(), overclock).doubleValue() * impactRAM;
        return score;
    }
}