package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.CpuDAO;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.enums.*;
import com.overclock.overclock.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("singleton")
public class CPUServiceImpl implements CPUService {
    private CpuDAO cpuDAO;

    @Autowired
    public void setCpuDAO(CpuDAO cpuDAO) {
        this.cpuDAO = cpuDAO;
    }

    @Override
    public CPU getById(BigInteger id) {
        return cpuDAO.getById(id);
    }

    @Override
    public List<CPU> getAll() {
        return cpuDAO.getAll();
    }

    @Override
    public boolean save(CPU cpu) {
        return cpuDAO.save(cpu);
    }

    @Override
    public boolean updateById(BigInteger id, CPU newCpu) {
        return cpuDAO.update(id, newCpu);
    }

    @Override
    public boolean delete(BigInteger id) {
        return cpuDAO.delete(id);
    }

    @Override
    public List<CPUSocket> getCPUSocketsByManufacturer(CPUManufacturer manufacturer) {
        List<CPUSocket> cpuSockets = new ArrayList<>();
        switch (manufacturer) {
            case Intel:
                for (int i = 33; i <= 34; i++) {
                    cpuSockets.add(CPUSocket.fromInt(i));
                }
                break;
            case AMD:
                for (int i = 35; i <= 35; i++) {
                    cpuSockets.add(CPUSocket.fromInt(i));
                }
                break;
        }
        return cpuSockets;
    }

    @Override
    public List<CPUGeneration> getCPUGenerationsBySocket(CPUSocket socket) {
        List<CPUGeneration> cpuGenerations = new ArrayList<>();
        switch (socket) {
            case Soc1151:
                for (int i = 36; i <= 37; i++) {
                    cpuGenerations.add(CPUGeneration.fromInt(i));
                }
                break;
            case Soc1200:
                for (int i = 38; i <= 39; i++) {
                    cpuGenerations.add(CPUGeneration.fromInt(i));
                }
                break;
            case AM4:
                for (int i = 40; i <= 43; i++) {
                    cpuGenerations.add(CPUGeneration.fromInt(i));
                }
                break;
        }
        return cpuGenerations;
    }

    @Override
    public List<CPUFamily> getCPUFamiliesByGeneration(CPUGeneration generation) {
        List<CPUFamily> cpuFamilies = new ArrayList<>();
        switch (generation) {
            case EightGen:
                for (int i = 25; i <= 27; i++) {
                    cpuFamilies.add(CPUFamily.fromInt(i));
                }
                break;
            case TenGen:
            case NineGen:
            case ElevenGen:
                for (int i = 25; i <= 28; i++) {
                    cpuFamilies.add(CPUFamily.fromInt(i));
                }
                break;
            case Zen:
            case ZenPlus:
                for (int i = 29; i <= 31; i++) {
                    cpuFamilies.add(CPUFamily.fromInt(i));
                }
                break;
            case ZenTwo:
            case ZenThree:
                for (int i = 29; i <= 32; i++) {
                    cpuFamilies.add(CPUFamily.fromInt(i));
                }
                break;
        }
        return cpuFamilies;
    }

    @Override
    public List<CPU> getCPUsByManufacturer(CPUManufacturer manufacturer) {
        List<CPU> cpuByManufacturer = new ArrayList<>();
        for (CPU cpu : cpuDAO.getAll()) {
            if (cpu.getManufacturer() == manufacturer) {
                cpuByManufacturer.add(cpu);
            }
        }
        return cpuByManufacturer;
    }

    @Override
    public List<CPU> getCPUsBySocket(CPUSocket socket) {
        List<CPU> cpuBySocket = new ArrayList<>();
        for (CPU cpu : cpuDAO.getAll()) {
            if (cpu.getSocket() == socket) {
                cpuBySocket.add(cpu);
            }
        }
        return cpuBySocket;
    }

    @Override
    public List<CPU> getCPUsByGeneration(CPUGeneration generation) {
        List<CPU> cpuByGeneration = new ArrayList<>();
        for (CPU cpu : cpuDAO.getAll()) {
            if (cpu.getGeneration() == generation) {
                cpuByGeneration.add(cpu);
            }
        }
        return cpuByGeneration;
    }

    @Override
    public List<CPU> getCPUsByFamily(CPUFamily family) {
        List<CPU> cpuByFamily = new ArrayList<>();
        for (CPU cpu : cpuDAO.getAll()) {
            if (cpu.getFamily() == family) {
                cpuByFamily.add(cpu);
            }
        }
        return cpuByFamily;
    }

    @Override
    public List<CPU> getCPUsByName(String name) {
        List<CPU> cpus = new ArrayList<>();
        Pattern pattern = Pattern.compile("(" + name + ")+", Pattern.CASE_INSENSITIVE);
        for (CPU cpu : getAll()) {
            Matcher matcher = pattern.matcher(cpu.getName());
            if (matcher.find()) {
                cpus.add(cpu);
            }
        }
        return cpus;
    }
}