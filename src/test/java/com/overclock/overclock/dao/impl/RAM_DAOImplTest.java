package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.RAM_DAO;
import com.overclock.overclock.model.RAM;
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
public class RAM_DAOImplTest {

    @Autowired
    RAM_DAO ram_dao;
    private final RAM good_ram = new RAM.Builder(BigInteger.valueOf(10), "DDR4 RAM 8GB AMD 2400 MHz Radeon R7 Performance")
            .setCapacity(BigInteger.valueOf(8))
            .setFrequency(BigInteger.valueOf(2400))
            .setTimings("5-5-5-15")
            .setVoltage(BigDecimal.valueOf(1.2))
            .build();
    private final RAM bad_ram = new RAM.Builder(BigInteger.valueOf(0), "bad ram")
            .setVoltage(BigDecimal.valueOf(1))
            .build();

    @Test
    public void getRAMById() {
        Assertions.assertEquals(good_ram, ram_dao.getById(BigInteger.valueOf(10)));
        Assertions.assertEquals(good_ram.hashCode(), ram_dao.getById(BigInteger.valueOf(10)).hashCode());
        Assertions.assertNotEquals(ram_dao.getById(BigInteger.valueOf(10)), new RAM.Builder(BigInteger.valueOf(1), "").build());
    }

    @Test
    public void getRAMByInvalidId() {
        Assertions.assertNull(ram_dao.getById(BigInteger.valueOf(-5)));
        Assertions.assertNull(ram_dao.getById(BigInteger.valueOf(5)));
    }

    @Test
    public void getByAssemblyId() {
        Assertions.assertEquals(good_ram, ram_dao.getByAssemblyId(BigInteger.valueOf(2)));
    }

    @Test
    public void getByAssemblyInvalidId() {
        Assertions.assertNull(ram_dao.getByAssemblyId(BigInteger.valueOf(-5)));
        Assertions.assertNull(ram_dao.getByAssemblyId(BigInteger.valueOf(5)));
    }

    @Test
    public void getAll() {
        List<RAM> rams = ram_dao.getAll();
        Assertions.assertNotNull(rams);
        Assertions.assertEquals(good_ram, rams.get(1));
    }

    @Test
    @Transactional
    public void saveRAM() {
        Assertions.assertTrue(ram_dao.save(CreateUtilities.createRAM()));
        Assertions.assertTrue(ram_dao.save(good_ram));
    }

    @Test
    @Transactional
    public void saveNullRam() {
        Assertions.assertFalse(ram_dao.save(null));
    }

    @Test
    @Transactional
    public void saveInvalidRAM() {
        Assertions.assertFalse(ram_dao.save(bad_ram));
    }

    @Test
    @Transactional
    public void update() {
        Assertions.assertTrue(ram_dao.update(BigInteger.valueOf(10), good_ram));
    }

    @Test
    @Transactional
    public void updateInvalidId() {
        Assertions.assertFalse(ram_dao.update(BigInteger.valueOf(1), good_ram));
    }

    @Test
    @Transactional
    public void updateInvalidRAM() {
        RAM ram = new RAM.Builder(BigInteger.valueOf(9), "bad ram")
                .build();
        Assertions.assertFalse(ram_dao.update(BigInteger.valueOf(10), ram));
    }

    @Test
    @Transactional
    public void deleteRAM() {
        Assertions.assertTrue(ram_dao.delete(BigInteger.valueOf(10)));
    }

    @Test
    @Transactional
    public void deleteNotARAM() {
        Assertions.assertFalse(ram_dao.delete(BigInteger.valueOf(1)));
    }
}