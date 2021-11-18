package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.GPU_DAO;
import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GPU_DAOImplTest {

    @Autowired
    GPU_DAO GPU_DAO;
    private GPU gpuTest = new GPU.Builder(BigInteger.valueOf(7), "Asus ROG STRIX GTX1080 A8G GAMING")
            .setChipManufacturer(GPUChipManufacturer.Nvidia)
            .setChip(GPUChip.GeForce_GTX_1080)
            .setMemoryCapacity(BigInteger.valueOf(8))
            .setCoreFrequency(BigInteger.valueOf(1695))
            .setMemoryFrequency(BigInteger.valueOf(10010))
            .setVoltage(BigDecimal.valueOf(500))
            .build();

    @Test
    public void getById_ValidID() {
        Assertions.assertTrue(gpuTest.equals(GPU_DAO.getById(BigInteger.valueOf(7))));
        Assertions.assertFalse(GPU_DAO.getById(BigInteger.valueOf(7)).equals(new GPU.Builder(BigInteger.valueOf(1), "").build()));
        Assertions.assertEquals(gpuTest.hashCode(), GPU_DAO.getById(BigInteger.valueOf(7)).hashCode());
    }

    @Test
    public void getById_InvalidID() {
        Assertions.assertNull(GPU_DAO.getById(BigInteger.valueOf(-1)));
        Assertions.assertNull(GPU_DAO.getById(BigInteger.valueOf(0)));
        Assertions.assertNull(GPU_DAO.getById(BigInteger.valueOf(1)));
    }

    @Test
    public void getByAssemblyId_ValidID() {
        Assertions.assertTrue(gpuTest.equals(GPU_DAO.getByAssemblyId(BigInteger.valueOf(1))));
    }

    @Test
    public void getByAssemblyId_InvalidID() {
        Assertions.assertNull(GPU_DAO.getByAssemblyId(BigInteger.valueOf(-1)));
        Assertions.assertNull(GPU_DAO.getByAssemblyId(BigInteger.valueOf(0)));
        Assertions.assertNull(GPU_DAO.getByAssemblyId(BigInteger.valueOf(7)));
    }

    @Test
    public void getAll() {
        List<GPU> allGpu = GPU_DAO.getAll();
        Assertions.assertNotNull(allGpu);
        Assertions.assertTrue(gpuTest.equals(allGpu.get(0)));
    }

    @Test
    @Transactional
    public void save_ValidObject() {
        GPU gpu = new GPU.Builder(BigInteger.valueOf(0), "test save - valid gpu")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.GeForce_GT_1030)
                .setMemoryCapacity(BigInteger.valueOf(1))
                .setCoreFrequency(BigInteger.valueOf(1))
                .setMemoryFrequency(BigInteger.valueOf(1))
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertTrue(GPU_DAO.save(gpu));
    }

    @Test
    @Transactional
    public void save_InvalidObject() {
        GPU gpu_invalid = new GPU.Builder(BigInteger.valueOf(0), "test save - invalid gpu")
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertFalse(GPU_DAO.save(null));
        Assertions.assertFalse(GPU_DAO.save(gpu_invalid));
    }

    @Test
    @Transactional
    public void update_Valid() {
        GPU gpu = new GPU.Builder(BigInteger.valueOf(0), "test update - valid gpu")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.GeForce_GT_1030)
                .setMemoryCapacity(BigInteger.valueOf(1))
                .setCoreFrequency(BigInteger.valueOf(1))
                .setMemoryFrequency(BigInteger.valueOf(1))
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertTrue(GPU_DAO.update(BigInteger.valueOf(8), gpu));
    }

    @Test
    @Transactional
    public void update_InvalidID() {
        Assertions.assertFalse(GPU_DAO.update(BigInteger.valueOf(1), gpuTest));
    }

    @Test
    @Transactional
    public void update_InvalidObject() {
        GPU gpuInvalid = new GPU.Builder(BigInteger.valueOf(7), "test update - invalid gpu")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .build();
        Assertions.assertFalse(GPU_DAO.update(BigInteger.valueOf(8), gpuInvalid));
    }

    @Test
    @Transactional
    public void delete_ValidID() {
        Assertions.assertTrue(GPU_DAO.delete(BigInteger.valueOf(8)));
    }

    @Test
    @Transactional
    public void delete_InvalidID() {
        Assertions.assertFalse(GPU_DAO.delete(BigInteger.valueOf(1)));
    }
}