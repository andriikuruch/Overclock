package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssemblyDAOImplTest {
    @Autowired
    private AssemblyDAO assemblyDAO;

    private BigInteger authorId = BigInteger.valueOf(12);
    private BigInteger badAuthorId = BigInteger.valueOf(-1);
    private BigInteger badAssemblyId = BigInteger.valueOf(-1);
    private Assembly expectedAssembly = CreateUtilities.createAssemblyWithFullInformation();

    @Test
    @Transactional
    public void save_CorrectResult() {
        boolean expected = true;

        boolean result = assemblyDAO.save(CreateUtilities.createAssemblyForSaving());

        Assert.assertEquals(expected, result);
    }

    @Test
    @Transactional
    public void save_BadResult() {
        boolean expected = false;

        boolean result = assemblyDAO.save(CreateUtilities.createBadAssemblyForSaving());

        Assert.assertEquals(expected, result);
    }

    @Test
    @Transactional
    public void delete_CorrectResult() {
        boolean expected = true;

        boolean result = assemblyDAO.delete(BigInteger.valueOf(2));

        Assert.assertEquals(expected, result);
    }

    @Test
    @Transactional
    public void delete_BadResult() {
        boolean expected = false;

        boolean result = assemblyDAO.delete(badAssemblyId);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void getById_NullResult() {
        Assembly assembly = assemblyDAO.getById(BigInteger.valueOf(0));
        Assert.assertNull(assembly);
    }

    @Test
    public void getById_CorrectResult() {
        Assembly actualAssembly = assemblyDAO.getById(BigInteger.valueOf(1));

        Assert.assertNotNull(actualAssembly);
        Assert.assertEquals(expectedAssembly, actualAssembly);
    }

    @Test
    public void getAll_CorrectResult() {
        List<Assembly> assemblies = assemblyDAO.getAll();
        int expectedCount = 4;

        Assert.assertFalse(assemblies.isEmpty());
        Assert.assertEquals(expectedCount, assemblies.size());
    }

    @Test
    public void getAllByAuthor_CorrectResult() {
        List<Assembly> assemblies = assemblyDAO.getAllByAuthor(authorId);
        int expectedCount = 4;

        Assert.assertFalse(assemblies.isEmpty());
        Assert.assertEquals(expectedCount, assemblies.size());
    }

    @Test
    public void getAllByAuthor_EmptyResult() {
        List<Assembly> assemblies = assemblyDAO.getAllByAuthor(badAuthorId);
        int expectedCount = 0;

        Assert.assertTrue(assemblies.isEmpty());
        Assert.assertEquals(expectedCount, assemblies.size());
    }

    @Test
    @Transactional
    public void updateScore() {
        Assert.assertTrue(assemblyDAO.updateScore(BigInteger.valueOf(1), BigInteger.valueOf(3400)));
    }

    @Test
    @Transactional
    public void updateByInvalidScore() {
        Assert.assertFalse(assemblyDAO.updateScore(BigInteger.valueOf(1), BigInteger.valueOf(-100)));
    }

    @Test
    @Transactional
    public void updateScoreByInvalidAssemblyId() {
        Assert.assertFalse(assemblyDAO.updateScore(BigInteger.valueOf(5), BigInteger.valueOf(3400)));
    }
}