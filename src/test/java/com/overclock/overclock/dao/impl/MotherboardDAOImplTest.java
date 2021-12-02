package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
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
public class MotherboardDAOImplTest {
    @Autowired
    MotherboardDAO motherboardDAO;

    private final Motherboard motherboard =  CreateUtilities.createMotherboard();

    @Test
    public void getByIdValidId(){
        Assertions.assertTrue(motherboard.equals(motherboardDAO.getMotherboardById(BigInteger.valueOf(-5008))));
        Assertions.assertFalse(motherboardDAO.getMotherboardById(BigInteger.valueOf(-5008)).equals(new Motherboard.Builder(BigInteger.valueOf(1), "").build()));
        Assertions.assertEquals(motherboard.hashCode(), motherboardDAO.getMotherboardById(BigInteger.valueOf(-5008)).hashCode());
    }

    @Test
    public void getByIdInvalidId(){
        Assertions.assertNull(motherboardDAO.getMotherboardById(BigInteger.valueOf(0)));
    }

    @Test
    public void getByAssemblyIdValidId() {
        Assertions.assertTrue(motherboard.equals(motherboardDAO.getMotherboardByAssemblyId(BigInteger.valueOf(-5006))));
    }

    @Test
    public void getByAssemblyIdInvalidId() {
        Assertions.assertNull(motherboardDAO.getMotherboardByAssemblyId(BigInteger.valueOf(0)));
    }

    @Test
    public void getAll() {
        List<Motherboard> allMotherboards = motherboardDAO.getAllMotherboards();
        Assertions.assertNotNull(allMotherboards);

        for (Motherboard motherboard: allMotherboards) {
            Assertions.assertNotNull(motherboard.getId());
            Assertions.assertNotNull(motherboard.getName());
            Assertions.assertNotNull(motherboard.getSocket());
            Assertions.assertNotNull(motherboard.getChipset());
            Assertions.assertNotNull(motherboard.getChipsetManufacturer());
        }
    }

    @Test
    public void saveValidObject() {
        Assertions.assertTrue(motherboardDAO.save(motherboard));
    }

   @Test
    public void saveInvalidObject() {
       Assertions.assertFalse(motherboardDAO.save(CreateUtilities.createBadMotherboard()));
   }

    @Test
    public void saveNullObject() {
        Assertions.assertFalse(motherboardDAO.save(null));
    }

    @Test
    public void updateValid() {
        Motherboard testMotherboard = new Motherboard.Builder(BigInteger.valueOf(0), "Test Name")
            .setChipsetManufacturer(ChipsetManufacturer.Intel)
            .setChipset(Chipset.B560)
            .setSocket(MotherboardSocket.Soc1200)
            .build();
        Assertions.assertTrue(motherboardDAO.update(BigInteger.valueOf(-5008), testMotherboard));
    }

    @Test
    public void updateInvalidID() {
        Assertions.assertFalse(motherboardDAO.update(BigInteger.valueOf(0), motherboard));
    }

    @Test
    public void updateInvalidObject() {
        Motherboard testMotherboard = new Motherboard.Builder(BigInteger.valueOf(0), "Test Name Of Bad Motherboard")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setSocket(MotherboardSocket.Soc1200)
                .build();
        Assertions.assertFalse(motherboardDAO.update(BigInteger.valueOf(-5008), testMotherboard));
    }

    @Test
    public void deleteValidID() {
        Assertions.assertTrue(motherboardDAO.delete(BigInteger.valueOf(-5008)));
    }

    @Test
    public void deleteInvalidID() {
        Assertions.assertFalse(motherboardDAO.delete(BigInteger.valueOf(0)));
    }

}
