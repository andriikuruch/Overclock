package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.GpuDAO;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import com.overclock.overclock.service.GPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class GPUServiceImpl implements GPUService {

    private GpuDAO gpuDAO;

    @Autowired
    public void setGpuDAO(GpuDAO gpuDAO) {
        this.gpuDAO = gpuDAO;
    }

    @Override
    public GPU getById(BigInteger id) {
        return gpuDAO.getById(id);
    }

    @Override
    public List<GPU> getAll() {
        return gpuDAO.getAll();
    }

    @Override
    public boolean save(GPU gpu) {
        return gpuDAO.save(gpu);
    }

    @Override
    public boolean updateById(BigInteger id, GPU newGpu) {
        return gpuDAO.update(id, newGpu);
    }

    @Override
    public boolean delete(BigInteger id) {
        return gpuDAO.delete(id);
    }

    @Override
    public List<GPUChip> getGPUChipsByManufacturer(GPUChipManufacturer manufacturer) {
        List<GPUChip> gpuChips = new ArrayList<>();
        switch (manufacturer) {
            case Nvidia:
                for (int i = 46; i <= 72; i++) {
                    gpuChips.add(GPUChip.fromInt(i));
                }
                break;
            case AMD:
                for (int i = 73; i <= 86; i++) {
                    gpuChips.add(GPUChip.fromInt(i));
                }
                break;
        }
        return gpuChips;
    }

    @Override
    public List<GPU> getGPUsByChip(GPUChip chip) {
        List<GPU> gpuByChip = new ArrayList<>();
        for (GPU gpu : gpuDAO.getAll()) {
            if (gpu.getChip() == chip) {
                gpuByChip.add(gpu);
            }
        }
        return gpuByChip;
    }

    @Override
    public List<GPU> getGPUsByManufacturer(GPUChipManufacturer manufacturer) {
        List<GPU> gpuByManufacturer = new ArrayList<>();
        for (GPU gpu : gpuDAO.getAll()) {
            if (gpu.getChipManufacturer() == manufacturer) {
                gpuByManufacturer.add(gpu);
            }
        }
        return gpuByManufacturer;
    }
}