package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.*;
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
public class MotherboardDAOImplTest {
    @Autowired
    MotherboardDAO motherboardDAO;

    private final Motherboard motherboard =  CreateUtilities.createMotherboard();

    @Test
    public void getByIdValidId(){
        Assertions.assertTrue(motherboard.equals(motherboardDAO.getMotherboardById(BigInteger.valueOf(3))));
        Assertions.assertFalse(motherboardDAO.getMotherboardById(BigInteger.valueOf(3)).equals(new Motherboard.Builder(BigInteger.valueOf(1), "").build()));
        Assertions.assertEquals(motherboard.hashCode(), motherboardDAO.getMotherboardById(BigInteger.valueOf(3)).hashCode());
    }

    @Test
    public void getByIdInvalidId(){
        Assertions.assertNull(motherboardDAO.getMotherboardById(BigInteger.valueOf(-1)));
        Assertions.assertNull(motherboardDAO.getMotherboardById(BigInteger.valueOf(0)));
        Assertions.assertNull(motherboardDAO.getMotherboardById(BigInteger.valueOf(1)));
    }

    @Test
    public void getByAssemblyIdValidId() {
        Assertions.assertTrue(motherboard.equals(motherboardDAO.getMotherboardByAssemblyId(BigInteger.valueOf(1))));
    }

    @Test
    public void getByAssemblyIdInvalidId() {
        Assertions.assertNull(motherboardDAO.getMotherboardByAssemblyId(BigInteger.valueOf(-1)));
        Assertions.assertNull(motherboardDAO.getMotherboardByAssemblyId(BigInteger.valueOf(0)));
        Assertions.assertNull(motherboardDAO.getMotherboardByAssemblyId(BigInteger.valueOf(1000)));
    }

    @Test
    public void getAll() {
        List<Motherboard> allMotherboards = motherboardDAO.getAllMotherboards();
        Assertions.assertNotNull(allMotherboards);
        Assertions.assertTrue(motherboard.equals(allMotherboards.get(0)));
    }

    @Test
    @Transactional
    public void saveValidObject() {
        Assertions.assertTrue(motherboardDAO.save(motherboard));
    }

   @Test
    @Transactional
    public void saveInvalidObject() {
       Assertions.assertFalse(motherboardDAO.save(CreateUtilities.createBadMotherboard()));
   }

    @Test
    @Transactional
    public void saveNullObject() {
        Assertions.assertFalse(motherboardDAO.save(null));
    }

    @Test
    @Transactional
    public void updateValid() {
        Motherboard testMotherboard = new Motherboard.Builder(BigInteger.valueOf(0), "Test Name")
            .setChipsetManufacturer(ChipsetManufacturer.Intel)
            .setChipset(Chipset.B560)
            .setSocket(MotherboardSocket.Soc1200)
            .build();
        Assertions.assertTrue(motherboardDAO.update(BigInteger.valueOf(3), testMotherboard));
    }

    @Test
    @Transactional
    public void updateInvalidID() {
        Assertions.assertFalse(motherboardDAO.update(BigInteger.valueOf(1000), motherboard));
    }

    @Test
    @Transactional
    public void updateInvalidObject() {
        Motherboard testMotherboard = new Motherboard.Builder(BigInteger.valueOf(0), "Test Name Of Bad Motherboard")
                .setChipsetManufacturer(ChipsetManufacturer.Intel)
                .setSocket(MotherboardSocket.Soc1200)
                .build();
        Assertions.assertFalse(motherboardDAO.update(BigInteger.valueOf(3), testMotherboard));
    }

    @Test
    @Transactional
    public void deleteValidID() {
        Assertions.assertTrue(motherboardDAO.delete(BigInteger.valueOf(4)));
    }

    @Test
    @Transactional
    public void deleteInvalidID() {
        Assertions.assertFalse(motherboardDAO.delete(BigInteger.valueOf(33)));
    }

}
