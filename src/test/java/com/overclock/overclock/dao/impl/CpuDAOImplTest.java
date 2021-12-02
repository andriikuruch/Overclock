package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CpuDAO;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.enums.CPUFamily;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("sql/InsertionsForTesting.sql")
@Transactional
public class CpuDAOImplTest {

    @Autowired
    CpuDAO CpuDAO;
    private CPU cpuTest = new CPU.Builder(BigInteger.valueOf(-5009), "testCPU1")
            .setManufacturer(CPUManufacturer.Intel)
            .setSocket(CPUSocket.Soc1200)
            .setGeneration(CPUGeneration.TenGen)
            .setFamily(CPUFamily.Core_i7)
            .setVoltage(BigDecimal.valueOf(125))
            .setFrequency(BigDecimal.valueOf(3800))
            .setThreadsNumber(BigInteger.valueOf(16))
            .setCoresNumber(BigInteger.valueOf(8))
            .build();

    @Test
    public void getByIdValidId() {
        Assertions.assertTrue(cpuTest.equals(CpuDAO.getById(BigInteger.valueOf(-5009))));
        Assertions.assertFalse(CpuDAO.getById(BigInteger.valueOf(-5009)).equals(new CPU.Builder(BigInteger.valueOf(1), "").build()));
        Assertions.assertEquals(cpuTest.hashCode(), CpuDAO.getById(BigInteger.valueOf(-5009)).hashCode());
    }

    @Test
    public void getByIdInvalidId() {
        Assertions.assertNull(CpuDAO.getById(BigInteger.valueOf(0)));
    }

    @Test
    public void getByAssemblyIdValidId() {
        Assertions.assertTrue(cpuTest.equals(CpuDAO.getByAssemblyId(BigInteger.valueOf(-5006))));
    }

    @Test
    public void getByAssemblyIdInvalidId() {
        Assertions.assertNull(CpuDAO.getByAssemblyId(BigInteger.valueOf(0)));
    }

    @Test
    public void getAll() {
        List<CPU> allCpu = CpuDAO.getAll();
        Assertions.assertNotNull(allCpu);
        Assert.assertFalse(allCpu.isEmpty());
        for (CPU cpu: allCpu) {
            Assertions.assertNotNull(cpu.getId());
            Assertions.assertNotNull(cpu.getName());
            Assertions.assertNotNull(cpu.getFrequency());
            Assertions.assertNotNull(cpu.getVoltage());
            Assertions.assertNotNull(cpu.getGeneration());
            Assertions.assertNotNull(cpu.getSocket());
            Assertions.assertNotNull(cpu.getFamily());
            Assertions.assertNotNull(cpu.getCoresNumber());
            Assertions.assertNotNull(cpu.getThreadsNumber());
        }
    }

    @Test
    public void saveValidObject() {
        CPU cpu = new CPU.Builder(BigInteger.valueOf(0), "test save - valid cpu")
                .setManufacturer(CPUManufacturer.AMD)
                .setSocket(CPUSocket.AM4)
                .setGeneration(CPUGeneration.Zen)
                .setFamily(CPUFamily.Ryzen3)
                .setVoltage(BigDecimal.valueOf(1))
                .setFrequency(BigDecimal.valueOf(1))
                .setThreadsNumber(BigInteger.valueOf(1))
                .setCoresNumber(BigInteger.valueOf(1))
                .build();
        Assertions.assertTrue(CpuDAO.save(cpu));
    }

    @Test
    public void saveInvalidObject() {
        CPU cpu_invalid = new CPU.Builder(BigInteger.valueOf(0), "test save - invalid cpu")
                .setVoltage(BigDecimal.valueOf(1))
                .build();
        Assertions.assertFalse(CpuDAO.save(cpu_invalid));
   }

   @Test
    public void saveNullObject() {
        Assertions.assertFalse(CpuDAO.save(null));
    }

    @Test
    public void updateValidId() {
        CPU testCPU = new CPU.Builder(BigInteger.valueOf(0), "test update - valid cpu")
            .setManufacturer(CPUManufacturer.AMD)
                .setSocket(CPUSocket.AM4)
                .setGeneration(CPUGeneration.Zen)
                .setFamily(CPUFamily.Ryzen3)
                .setVoltage(BigDecimal.valueOf(1))
                .setFrequency(BigDecimal.valueOf(1))
                .setThreadsNumber(BigInteger.valueOf(1))
                .setCoresNumber(BigInteger.valueOf(1))
            .build();
        Assertions.assertTrue(CpuDAO.update(BigInteger.valueOf(-5009), testCPU));
    }

    @Test
    public void updateInvalidId() {
        Assertions.assertFalse(CpuDAO.update(BigInteger.valueOf(0), cpuTest));
    }

    @Test
    public void updateInvalidObject() {
        CPU testCPU = new CPU.Builder(BigInteger.valueOf(0), "Test Name Of Bad CPU")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Core_i3)
                .build();
        Assertions.assertFalse(CpuDAO.update(BigInteger.valueOf(-5009), testCPU));
    }


    @Test
    public void deleteValidId() {
        Assertions.assertTrue(CpuDAO.delete(BigInteger.valueOf(-5009)));
    }

    @Test
    public void deleteInvalidId() {
        Assertions.assertFalse(CpuDAO.delete(BigInteger.valueOf(0)));
    }
}