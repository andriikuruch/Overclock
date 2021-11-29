package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.OverclockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Scope("singleton")
public class OverclockServiceImpl implements OverclockService {
    @Autowired
    private OverclockDAO overclockDAO;

    @Autowired
    private AssemblyService assemblyService;

    @Override
    public boolean save(Overclock overclock,  BigInteger assemblyId) {
        if (isValidOverclock(overclock)) {
            return overclockDAO.save(overclock, assemblyId);
        }
        return false;
    }

    @Override
    public boolean update(BigInteger id, Overclock newOverclock) {
        if (isValidOverclock(newOverclock)) {
            return overclockDAO.update(id, newOverclock);
        }
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return overclockDAO.delete(id);
    }

    @Override
    public boolean deleteByAssemblyId(BigInteger assemblyId) {
        return overclockDAO.deleteByAssemblyId(assemblyId);
    }

    @Override
    public Overclock getOverclockById(BigInteger id) {
        return overclockDAO.getOverclockById(id);
    }

    @Override
    public Overclock getDefaultOverclockValues(BigInteger assemblyId) {
        Assembly assembly = assemblyService.getAssemblyById(assemblyId);

        return new Overclock.Builder()
                .setCPUFrequency(assembly.getCpu().getFrequency())
                .setCPUVoltage(assembly.getCpu().getVoltage())
                .setGPUCoreFrequency(assembly.getGpu().getCoreFrequency())
                .setGPUMemoryFrequency(assembly.getGpu().getMemoryFrequency())
                .setGPUVoltage(assembly.getGpu().getVoltage())
                .setRAMFrequency(assembly.getRam().getFrequency())
                .setRAMTimings(assembly.getRam().getTimings())
                .setRAMVoltage(assembly.getRam().getVoltage()).build();
    }

    @Override
    public boolean isValidOverclock(Overclock overclock) {
        if (overclock == null) {
            return false;
        }
        if (overclock.getCPUVoltage() == null || overclock.getCPUFrequency() == null ||
        overclock.getGPUVoltage() == null || overclock.getGPUMemoryFrequency() == null || overclock.getGPUCoreFrequency() == null ||
        overclock.getRAMVoltage() == null || overclock.getRAMFrequency() == null || overclock.getRAMTimings() == null) {
            return false;
        }
        return overclock.getCPUVoltage().doubleValue() >= 0.5 && overclock.getCPUVoltage().doubleValue() <= 3.0 &&
                overclock.getCPUFrequency().doubleValue() >= 1.0 && overclock.getCPUFrequency().doubleValue() <= 8.0 &&
                overclock.getGPUVoltage().doubleValue() >= 0.5 && overclock.getGPUVoltage().doubleValue() <= 3.0 &&
                overclock.getGPUMemoryFrequency().intValue() >= 1000 && overclock.getGPUMemoryFrequency().intValue() <= 230000 &&
                overclock.getGPUCoreFrequency().intValue() >= 1000 && overclock.getGPUCoreFrequency().intValue() <= 2000 &&
                overclock.getRAMVoltage().doubleValue() >= 0.5 && overclock.getRAMVoltage().doubleValue() <= 3.0 &&
                overclock.getRAMFrequency().intValue() >= 2133 && overclock.getRAMFrequency().intValue() <= 5000 &&
                overclock.getRAMTimings().matches("([1-4]\\d-){3}[2-6]\\d");
    }
}
