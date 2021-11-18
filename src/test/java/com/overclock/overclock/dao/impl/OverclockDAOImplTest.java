package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OverclockDAOImplTest {
    @Autowired
    OverclockDAO overclockDAO;

    @Test
    public void getOverclockById() {
        Overclock overclock = overclockDAO.getOverclockById(BigInteger.valueOf(11));

        Assertions.assertNotNull(overclock);
        Assertions.assertNotNull(overclock.getCPUFrequency());
        Assertions.assertNotNull(overclock.getCPUVoltage());
        Assertions.assertNotNull(overclock.getGPUCoreFrequency());
        Assertions.assertNotNull(overclock.getGPUMemoryFrequency());
        Assertions.assertNotNull(overclock.getGPUVoltage());
        Assertions.assertNotNull(overclock.getRAMFrequency());
        Assertions.assertNotNull(overclock.getRAMTimings());
        Assertions.assertNotNull(overclock.getRAMVoltage());
    }

    @Test
    public void getOverclockById_EmptyResult() {
        Overclock overclock = overclockDAO.getOverclockById(BigInteger.valueOf(1));

        Assertions.assertNull(overclock);
    }

    @Test
    @Transactional
    public void saveValidOverclock() {
        Assertions.assertTrue(overclockDAO.save(CreateUtilities.createOverclock(), BigInteger.valueOf(2)));
    }

    @Test
    @Transactional
    public void saveInvalidOverclock() {
        Assertions.assertFalse(overclockDAO.save(CreateUtilities.createBadOverclock(), BigInteger.valueOf(2)));
    }

    @Test
    @Transactional
    public void saveOverclock_Null() {
        Assertions.assertFalse(overclockDAO.save(null, BigInteger.valueOf(2)));
    }

    @Test
    @Transactional
    public void saveOverclock_WhenOverclockIsAlreadyExists() {
        Assertions.assertFalse(overclockDAO.save(CreateUtilities.createOverclock(), BigInteger.valueOf(1)));
    }

    @Test
    @Transactional
    public void updateOverclock_WithNewValidOverclock() {
        Assertions.assertTrue(overclockDAO.update(BigInteger.valueOf(11), CreateUtilities.createOverclock()));
    }

    @Test
    @Transactional
    public void updateOverclock_WithNull() {
        Assertions.assertFalse(overclockDAO.update(BigInteger.valueOf(11), null));
    }

    @Test
    @Transactional
    public void deleteValidOverclock() {
        Assertions.assertTrue(overclockDAO.delete(BigInteger.valueOf(11)));
    }

    @Test
    @Transactional
    public void deleteNotAnOverclock() {
        Assertions.assertFalse(overclockDAO.delete(BigInteger.valueOf(1)));
    }

    @Test
    @Transactional
    public void deleteOverclock_NonExistentId() {
        Assertions.assertFalse(overclockDAO.delete(BigInteger.valueOf(1000000000)));
    }
}