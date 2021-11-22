package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.GpuDAO;
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
public class GpuDAOImplTest {

    @Autowired
    GpuDAO GpuDAO;
    private GPU gpuTest = new GPU.Builder(BigInteger.valueOf(7), "Asus ROG STRIX GTX1080 A8G GAMING")
            .setChipManufacturer(GPUChipManufacturer.Nvidia)
            .setChip(GPUChip.GeForce_GTX_1080)
            .setMemoryCapacity(BigInteger.valueOf(8))
            .setCoreFrequency(BigInteger.valueOf(1695))
            .setMemoryFrequency(BigInteger.valueOf(10010))
            .setVoltage(BigDecimal.valueOf(500))
            .build();

    @Test
    public void getByIdValidID() {
        Assertions.assertTrue(gpuTest.equals(GpuDAO.getById(BigInteger.valueOf(7))));
        Assertions.assertFalse(GpuDAO.getById(BigInteger.valueOf(7)).equals(new GPU.Builder(BigInteger.valueOf(1), "").build()));
        Assertions.assertEquals(gpuTest.hashCode(), GpuDAO.getById(BigInteger.valueOf(7)).hashCode());
    }

    @Test
    public void getByIdInvalidID() {
        Assertions.assertNull(GpuDAO.getById(BigInteger.valueOf(-1)));
        Assertions.assertNull(GpuDAO.getById(BigInteger.valueOf(0)));
        Assertions.assertNull(GpuDAO.getById(BigInteger.valueOf(1)));
    }

    @Test
    public void getByAssemblyIdValidID() {
        Assertions.assertTrue(gpuTest.equals(GpuDAO.getByAssemblyId(BigInteger.valueOf(1))));
    }

    @Test
    public void getByAssemblyIdInvalidID() {
        Assertions.assertNull(GpuDAO.getByAssemblyId(BigInteger.valueOf(-1)));
        Assertions.assertNull(GpuDAO.getByAssemblyId(BigInteger.valueOf(0)));
        Assertions.assertNull(GpuDAO.getByAssemblyId(BigInteger.valueOf(7)));
    }

    @Test
    public void getAll() {
        List<GPU> allGpu = GpuDAO.getAll();
        Assertions.assertNotNull(allGpu);
        Assertions.assertTrue(gpuTest.equals(allGpu.get(0)));
    }

    @Test
    @Transactional
    public void saveValidObject() {
        GPU gpu = new GPU.Builder(BigInteger.valueOf(0), "test save - valid gpu")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.GeForce_GT_1030)
                .setMemoryCapacity(BigInteger.valueOf(1))
                .setCoreFrequency(BigInteger.valueOf(1))
                .setMemoryFrequency(BigInteger.valueOf(1))
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertTrue(GpuDAO.save(gpu));
    }

    @Test
    @Transactional
    public void saveInvalidObject() {
        GPU gpu_invalid = new GPU.Builder(BigInteger.valueOf(0), "test save - invalid gpu")
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertFalse(GpuDAO.save(null));
        Assertions.assertFalse(GpuDAO.save(gpu_invalid));
    }

    @Test
    @Transactional
    public void updateValid() {
        GPU gpu = new GPU.Builder(BigInteger.valueOf(0), "test update - valid gpu")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .setChip(GPUChip.GeForce_GT_1030)
                .setMemoryCapacity(BigInteger.valueOf(1))
                .setCoreFrequency(BigInteger.valueOf(1))
                .setMemoryFrequency(BigInteger.valueOf(1))
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertTrue(GpuDAO.update(BigInteger.valueOf(8), gpu));
    }

    @Test
    @Transactional
    public void updateInvalidID() {
        Assertions.assertFalse(GpuDAO.update(BigInteger.valueOf(1), gpuTest));
    }

    @Test
    @Transactional
    public void updateInvalidObject() {
        GPU gpuInvalid = new GPU.Builder(BigInteger.valueOf(7), "test update - invalid gpu")
                .setChipManufacturer(GPUChipManufacturer.AMD)
                .build();
        Assertions.assertFalse(GpuDAO.update(BigInteger.valueOf(8), gpuInvalid));
    }

    @Test
    @Transactional
    public void deleteValidID() {
        Assertions.assertTrue(GpuDAO.delete(BigInteger.valueOf(8)));
    }

    @Test
    @Transactional
    public void deleteInvalidID() {
        Assertions.assertFalse(GpuDAO.delete(BigInteger.valueOf(1)));
    }
}