package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.RamDAO;
import com.overclock.overclock.model.RAM;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("sql/InsertionsForTesting.sql")
@Transactional
public class RamDAOImplTest {

    @Autowired
    RamDAO ramDao;

    @Test
    public void getRAMById() {
        RAM ram = ramDao.getRamById(BigInteger.valueOf(-5011));

        Assertions.assertNotNull(ram);
        Assertions.assertNotNull(ram.getName());
        Assertions.assertNotNull(ram.getCapacity());
        Assertions.assertNotNull(ram.getFrequency());
        Assertions.assertNotNull(ram.getTimings());
        Assertions.assertNotNull(ram.getVoltage());
    }

    @Test
    public void getRAMByWrongId() {
        Assertions.assertNull(ramDao.getRamById(BigInteger.valueOf(0)));
    }

    @Test
    public void getByAssemblyId() {
        RAM ram = ramDao.getRamByAssemblyId(BigInteger.valueOf(-5006));

        Assertions.assertNotNull(ram);
        Assertions.assertNotNull(ram.getName());
        Assertions.assertNotNull(ram.getCapacity());
        Assertions.assertNotNull(ram.getFrequency());
        Assertions.assertNotNull(ram.getTimings());
        Assertions.assertNotNull(ram.getVoltage());
    }

    @Test
    public void getByAssemblyInvalidId() {
        Assertions.assertNull(ramDao.getRamByAssemblyId(BigInteger.valueOf(0)));
    }

    @Test
    public void getAll() {
        List<RAM> rams = ramDao.getAllRams();

        Assertions.assertNotNull(rams);
        for (RAM ram: rams) {
            Assertions.assertNotNull(ram.getId());
            Assertions.assertNotNull(ram.getName());
            Assertions.assertNotNull(ram.getFrequency());
            Assertions.assertNotNull(ram.getVoltage());
            Assertions.assertNotNull(ram.getTimings());
            Assertions.assertNotNull(ram.getCapacity());
        }
    }

    @Test
    public void saveValidRAM() {
        Assertions.assertTrue(ramDao.save(CreateUtilities.createRAM()));
    }

    @Test
    @Transactional
    public void saveInvalidRAM() {
        Assertions.assertFalse(ramDao.save(CreateUtilities.createBadRAM()));
    }

    @Test
    @Transactional
    public void saveNull() {
        Assertions.assertFalse(ramDao.save(null));
    }

    @Test
    @Transactional
    public void updateByNewValidRAM() {
        Assertions.assertTrue(ramDao.update(BigInteger.valueOf(-5011), CreateUtilities.createRAM()));
    }

    @Test
    @Transactional
    public void updateByInvalidId() {
        Assertions.assertFalse(ramDao.update(BigInteger.valueOf(0), CreateUtilities.createRAM()));
    }

    @Test
    public void updateByNewInvalidRAM() {
        Assertions.assertFalse(ramDao.update(BigInteger.valueOf(-5011), CreateUtilities.createBadRAM()));
    }

    @Test
    @Transactional
    public void deleteRAM() {
        Assertions.assertTrue(ramDao.delete(BigInteger.valueOf(-5011)));
    }

    @Test
    @Transactional
    public void deleteNotARAM() {
        Assertions.assertFalse(ramDao.delete(BigInteger.valueOf(0)));
    }
}