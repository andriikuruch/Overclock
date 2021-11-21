package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.CpuDAO;
import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.enums.CPUFamily;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;
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
public class CpuDAOImplTest {

    @Autowired
    CpuDAO CpuDAO;
    private final CPU cpu = CreateUtilities.createCpu();

    @Test
    public void getByIdValidId() {
        Assertions.assertTrue(cpu.equals(CpuDAO.getById(BigInteger.valueOf(5))));
        Assertions.assertFalse(CpuDAO.getById(BigInteger.valueOf(5)).equals(new CPU.Builder(BigInteger.valueOf(1), "").build()));
        Assertions.assertEquals(cpu.hashCode(), CpuDAO.getById(BigInteger.valueOf(5)).hashCode());
    }

    @Test
    public void getByIdInvalidId() {
        Assertions.assertNull(CpuDAO.getById(BigInteger.valueOf(-1)));
        Assertions.assertNull(CpuDAO.getById(BigInteger.valueOf(0)));
        Assertions.assertNull(CpuDAO.getById(BigInteger.valueOf(1)));
    }

    @Test
    public void getByAssemblyIdValidId() {
        Assertions.assertTrue(cpu.equals(CpuDAO.getByAssemblyId(BigInteger.valueOf(1))));
    }

    @Test
    public void getByAssemblyIdInvalidId() {
        Assertions.assertNull(CpuDAO.getByAssemblyId(BigInteger.valueOf(-1)));
        Assertions.assertNull(CpuDAO.getByAssemblyId(BigInteger.valueOf(0)));
        Assertions.assertNull(CpuDAO.getByAssemblyId(BigInteger.valueOf(10)));
    }

    @Test
    public void getAll() {
        List<CPU> allCpu = CpuDAO.getAll();
        Assertions.assertNotNull(allCpu);
        Assertions.assertTrue(cpu.equals(allCpu.get(0)));
    }

    @Test
    @Transactional
    public void saveValidObject() {
        Assertions.assertTrue(CpuDAO.save(cpu));
    }

    @Test
    @Transactional
    public void saveInvalidObject() {
       Assertions.assertFalse(CpuDAO.save(CreateUtilities.createBadCPU()));
   }

   @Test
    @Transactional
    public void saveNullObject() {
        Assertions.assertFalse(CpuDAO.save(null));
    }

    @Test
    @Transactional
    public void updateValidId() {
        CPU testCPU = new CPU.Builder(BigInteger.valueOf(0), "Test Name")
            .setManufacturer(CPUManufacturer.Intel)
            .setSocket(CPUSocket.Soc1200)
            .setGeneration(CPUGeneration.TenGen)
            .setFamily(CPUFamily.Core_i7)
            .setVoltage(BigDecimal.valueOf(125))
            .setFrequency(BigDecimal.valueOf(3800))
            .setThreadsNumber(BigInteger.valueOf(16))
            .setCoresNumber(BigInteger.valueOf(8))
            .build();
        Assertions.assertTrue(CpuDAO.update(BigInteger.valueOf(5), testCPU));
    }

    @Test
    @Transactional
    public void updateInvalidId() {
        Assertions.assertFalse(CpuDAO.update(BigInteger.valueOf(1000), cpu));
    }

    @Test
    @Transactional
    public void updateInvalidObject() {
        CPU testCPU = new CPU.Builder(BigInteger.valueOf(0), "Test Name Of Bad CPU")
                .setManufacturer(CPUManufacturer.Intel)
                .setFamily(CPUFamily.Core_i3)
                .build();
        Assertions.assertFalse(CpuDAO.update(BigInteger.valueOf(5), testCPU));
    }


    @Test
    @Transactional
    public void deleteValidId() {
        Assertions.assertTrue(CpuDAO.delete(BigInteger.valueOf(5)));
    }

    @Test
    @Transactional
    public void deleteInvalidId() {
        Assertions.assertFalse(CpuDAO.delete(BigInteger.valueOf(1)));
    }
}