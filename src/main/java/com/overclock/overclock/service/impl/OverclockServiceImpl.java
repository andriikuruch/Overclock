package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import com.overclock.overclock.model.Assembly;
import com.overclock.overclock.service.AssemblyService;
import com.overclock.overclock.service.OverclockService;
import com.overclock.overclock.service.ValidationService;
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
    @Autowired
    private ValidationService validationService;

    @Override
    public boolean save(Overclock overclock,  BigInteger assemblyId) {
        validationService.checkOverclockValidity(overclock);
        return overclockDAO.save(overclock, assemblyId);
    }

    @Override
    public boolean update(BigInteger id, Overclock newOverclock) {
        validationService.checkOverclockValidity(newOverclock);
        return overclockDAO.update(id, newOverclock);
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
}
