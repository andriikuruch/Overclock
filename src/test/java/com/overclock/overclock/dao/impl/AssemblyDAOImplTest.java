package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.AssemblyDAO;
import com.overclock.overclock.model.Assembly;
import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("sql/InsertionsForTesting.sql")
@Transactional
public class AssemblyDAOImplTest {
    @Autowired
    private AssemblyDAO assemblyDAO;

    private static boolean isAssemblyNotNull(Assembly assembly) {
        return assembly != null && assembly.getName() != null && assembly.getId() != null
                && assembly.getCpu() != null && assembly.getGpu() != null
                && assembly.getRam() != null && assembly.getMotherboard() != null &&
                assembly.getAuthor() != null;
    }

    @Test
    public void saveCorrectResult() {
        Assert.assertTrue(assemblyDAO.save(CreateUtilities.createAssemblyForSaving()));
    }

    @Test
    public void saveBadResult() {
        Assert.assertFalse(assemblyDAO.save(CreateUtilities.createBadAssemblyForSaving()));
    }

    @Test
    public void deleteCorrectResult() {
        Assert.assertTrue(assemblyDAO.delete(BigInteger.valueOf(-5006)));
    }

    @Test
    public void deleteBadResult() {
        Assert.assertFalse(assemblyDAO.delete(BigInteger.valueOf(0)));
    }

    @Test
    public void getByIdNullResult() {
        Assembly assembly = assemblyDAO.getAssemblyById(BigInteger.valueOf(0));
        Assert.assertNull(assembly);
    }

    @Test
    public void getByIdCorrectResult() {
        Assembly actualAssembly = assemblyDAO.getAssemblyById(BigInteger.valueOf(-5006));

        Assert.assertTrue(isAssemblyNotNull(actualAssembly));
    }

    @Test
    public void getAllAssembliesCorrectResult() {
        List<Assembly> assemblies = assemblyDAO.getAllAssemblies();

        Assert.assertNotNull(assemblies);
        Assert.assertFalse(assemblies.isEmpty());
        for (Assembly assembly: assemblies) {
            Assert.assertTrue(isAssemblyNotNull(assembly));
        }
    }

    @Test
    public void getAssembliesByAuthorIdCorrectResult() {
        List<Assembly> assemblies = assemblyDAO.getAssembliesByAuthorId(BigInteger.valueOf(-5007));

        Assertions.assertNotNull(assemblies);
        Assert.assertFalse(assemblies.isEmpty());
        for (Assembly assembly: assemblies) {
            Assert.assertTrue(isAssemblyNotNull(assembly));
        }
    }

    @Test
    public void getAssembliesByAuthorIdEmptyResult() {
        List<Assembly> assemblies = assemblyDAO.getAssembliesByAuthorId(BigInteger.valueOf(0));

        Assert.assertTrue(assemblies.isEmpty());
    }
    @Test
    public void getAssembliesByAuthorNameCorrectResult() {
        List<Assembly> assemblies = assemblyDAO.getAssembliesByAuthorName("testUser1");

        Assertions.assertNotNull(assemblies);
        Assert.assertFalse(assemblies.isEmpty());
        for (Assembly assembly: assemblies) {
            Assert.assertTrue(isAssemblyNotNull(assembly));
        }
    }

    @Test
    public void getAssembliesByAuthorNameIncorrectResult() {
        List<Assembly> assemblies = assemblyDAO.getAssembliesByAuthorName("Wrong name");
        Assert.assertNull(assemblies);
    }

    @Test
    public void updateScore() {
        Assert.assertTrue(assemblyDAO.updateScore(BigInteger.valueOf(-5006), BigInteger.valueOf(3400)));
    }

    @Test
    public void updateByInvalidScore() {
        Assert.assertFalse(assemblyDAO.updateScore(BigInteger.valueOf(-5006), BigInteger.valueOf(-100)));
    }

    @Test
    public void updateScoreByInvalidAssemblyId() {
        Assert.assertFalse(assemblyDAO.updateScore(BigInteger.valueOf(0), BigInteger.valueOf(3400)));
    }
}