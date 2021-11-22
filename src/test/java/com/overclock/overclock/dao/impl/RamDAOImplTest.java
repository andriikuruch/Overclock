package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.RamDAO;
import com.overclock.overclock.model.RAM;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RamDAOImplTest {

    @Autowired
    RamDAO ramDao;

    /*private final RAM good_ram = new RAM.Builder(BigInteger.valueOf(10), "DDR4 RAM 8GB AMD 2400 MHz Radeon R7 Performance")
            .setCapacity(BigInteger.valueOf(8))
            .setFrequency(BigInteger.valueOf(2400))
            .setTimings("5-5-5-15")
            .setVoltage(BigDecimal.valueOf(1.2))
            .build();
    private final RAM bad_ram = new RAM.Builder(BigInteger.valueOf(0), "bad ram")
            .setVoltage(BigDecimal.valueOf(1))
            .build();*/

    @Test
    public void getRAMById() {
        RAM ram = ramDao.getById(BigInteger.valueOf(10));

        Assertions.assertNotNull(ram);
        Assertions.assertNotNull(ram.getName());
        Assertions.assertNotNull(ram.getCapacity());
        Assertions.assertNotNull(ram.getFrequency());
        Assertions.assertNotNull(ram.getTimings());
        Assertions.assertNotNull(ram.getVoltage());
    }

    @Test
    public void getRAMByWrongId() {
        Assertions.assertNull(ramDao.getById(BigInteger.valueOf(-5)));
        Assertions.assertNull(ramDao.getById(BigInteger.valueOf(5)));
    }

    @Test
    public void getByAssemblyId() {
        RAM ram = ramDao.getByAssemblyId(BigInteger.valueOf(2));

        Assertions.assertNotNull(ram);
        Assertions.assertNotNull(ram.getName());
        Assertions.assertNotNull(ram.getCapacity());
        Assertions.assertNotNull(ram.getFrequency());
        Assertions.assertNotNull(ram.getTimings());
        Assertions.assertNotNull(ram.getVoltage());
    }

    @Test
    public void getByAssemblyInvalidId() {
        Assertions.assertNull(ramDao.getByAssemblyId(BigInteger.valueOf(-5)));
        Assertions.assertNull(ramDao.getByAssemblyId(BigInteger.valueOf(5)));
    }

    @Test
    public void getAll() {
        List<RAM> rams = ramDao.getAll();
        Assertions.assertNotNull(rams);
    }

    @Test
    @Transactional
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
        Assertions.assertTrue(ramDao.update(BigInteger.valueOf(10), CreateUtilities.createRAM()));
    }

    @Test
    @Transactional
    public void updateByInvalidId() {
        Assertions.assertFalse(ramDao.update(BigInteger.valueOf(1), CreateUtilities.createRAM()));
    }

    @Test
    @Transactional
    public void updateByNewInvalidRAM() {
        Assertions.assertFalse(ramDao.update(BigInteger.valueOf(1), CreateUtilities.createBadRAM()));
    }

    @Test
    @Transactional
    public void deleteRAM() {
        Assertions.assertTrue(ramDao.delete(BigInteger.valueOf(10)));
    }

    @Test
    @Transactional
    public void deleteNotARAM() {
        Assertions.assertFalse(ramDao.delete(BigInteger.valueOf(1)));
    }
}