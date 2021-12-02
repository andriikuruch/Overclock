package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.model.Overclock;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("sql/InsertionsForTesting.sql")
@Transactional
public class OverclockDAOImplTest {
    @Autowired
    OverclockDAO overclockDAO;

    @Test
    public void getOverclockById() {
        Overclock overclock = overclockDAO.getOverclockById(BigInteger.valueOf(-5012));

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
    public void getOverclockByWrongId() {
        Overclock overclock = overclockDAO.getOverclockById(BigInteger.valueOf(0));

        Assertions.assertNull(overclock);
    }

    @Test
    public void saveValidOverclock() {
        Assertions.assertTrue(overclockDAO.save(CreateUtilities.createOverclock(), BigInteger.valueOf(-5013)));
    }

    @Test
    public void saveInvalidOverclock() {
        Assertions.assertFalse(overclockDAO.save(CreateUtilities.createBadOverclock(), BigInteger.valueOf(-5013)));
    }

    @Test
    public void saveNull() {
        Assertions.assertFalse(overclockDAO.save(null, BigInteger.valueOf(-5013)));
    }

    @Test
    public void saveWhenOverclockIsAlreadyExists() {
        Assertions.assertFalse(overclockDAO.save(CreateUtilities.createOverclock(), BigInteger.valueOf(-5006)));
    }

    @Test
    public void saveOverclockByWrongAssemblyId() {
        Assertions.assertFalse(overclockDAO.save(CreateUtilities.createOverclock(), BigInteger.valueOf(0)));
    }

    @Test
    public void updateByNewValidOverclock() {
        Assertions.assertTrue(overclockDAO.update(BigInteger.valueOf(-5012), CreateUtilities.createOverclock()));
    }

    @Test
    public void updateByNewInvalidOverclock() {
        Assertions.assertFalse(overclockDAO.update(BigInteger.valueOf(-5012), CreateUtilities.createBadOverclock()));
    }

    @Test
    public void updateOverclockByNull() {
        Assertions.assertFalse(overclockDAO.update(BigInteger.valueOf(-5012), null));
    }

    @Test
    public void updateNotAOverclock() {
        Assertions.assertFalse(overclockDAO.update(BigInteger.valueOf(-5013), CreateUtilities.createOverclock()));
    }

    @Test
    public void deleteOverclock() {
        Assertions.assertTrue(overclockDAO.delete(BigInteger.valueOf(-5012)));
    }

    @Test
    public void deleteNotAnOverclock() {
        Assertions.assertFalse(overclockDAO.delete(BigInteger.valueOf(-5013)));
    }

    @Test
    public void deleteByAssemblyId() {
        Assertions.assertTrue(overclockDAO.deleteByAssemblyId(BigInteger.valueOf(-5006)));
    }

    @Test
    public void deleteByWrongAssemblyId() {
        Assertions.assertFalse(overclockDAO.deleteByAssemblyId(BigInteger.valueOf(0)));
    }

    @Test
    public void deleteByAssemblyIdWhenOverclockIsNotExists() {
        Assertions.assertFalse(overclockDAO.deleteByAssemblyId(BigInteger.valueOf(-5013)));
    }
}