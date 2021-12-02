package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("sql/InsertionsForTesting.sql")
@Transactional
public class UserDAOImplTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void getFullInformationById() {
        User user = userDAO.getFullInformationById(BigInteger.valueOf(-5007));
        Assert.assertNotNull(user);
    }

    @Test
    public void getWithMainInformation() {
        User user = userDAO.getWithMainInformation(BigInteger.valueOf(-5007));
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserByUsername() {
        String expectedUsername = "testUser1";

        User user = userDAO.getUserByUsername(expectedUsername);

        Assert.assertNotNull(user);
        Assert.assertEquals(expectedUsername, user.getUserName());
    }

    @Test
    public void getUserByUsername_BadResult() {
        String expectedUsername = "0";

        User user = userDAO.getUserByUsername(expectedUsername);

        Assert.assertNull(user);
    }

    @Test
    public void save_CorrectResult() {
        boolean result = userDAO.save("u2","pfassdaf", "email@mail.com", true);
        Assert.assertTrue(result);
    }

    @Test
    public void save_BadResult() {
        boolean result = userDAO.save(null,"pfassdaf", "email@mail.com", false);
        Assert.assertFalse(result);
    }

    @Test
    public void updateUsername() {
        boolean result = userDAO.updateUsername(BigInteger.valueOf(-5007), "u1");
        Assert.assertTrue(result);
    }

    @Test
    public void updatePassword() {
        boolean result = userDAO.updatePassword(BigInteger.valueOf(-5007), "pwd1");
        Assert.assertTrue(result);
    }

    @Test
    public void updateEmail() {
        boolean result = userDAO.updateEmail(BigInteger.valueOf(-5007), "u1@gmail.com");
        Assert.assertTrue(result);
    }

    @Test
    public void updateUserActiveStatus() {
        boolean result = userDAO.updateUserActiveStatus(BigInteger.valueOf(-5007), false);
        Assert.assertTrue(result);
    }
}