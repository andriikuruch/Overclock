package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.UserDAO;
import com.overclock.overclock.dao.impl.mapper.UserFullInformationRowMapper;
import com.overclock.overclock.dao.impl.mapper.UserMainInformationRowMapper;
import com.overclock.overclock.model.User;
import com.overclock.overclock.model.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserFullInformationRowMapper fullInformationRowMapper;

    @Autowired
    private UserMainInformationRowMapper mainInformationRowMapper;

    @Override
    public User getFullInformationById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(GET_WITH_FULL_INFORMATION, fullInformationRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public User getWithMainInformation(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(GET_WITH_MAIN_INFORMATION, mainInformationRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_USERNAME, (rs, rowNum) -> new User.Builder()
                    .setId(BigInteger.valueOf(rs.getLong("USER_ID")))
                    .setUserName(rs.getString("USERNAME"))
                    .build(), username);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public User getWithMainInformationByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(GET_WITH_MAIN_INFORMATION_BY_USERNAME, mainInformationRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public boolean save(String username, String password, String email, boolean isActive) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String userIsActive = isActive ? "1" : "0";
            int result = jdbcTemplate.update(INSERT_USER, username, userIsActive, password, email,
                    dateFormat.format(new Date()), Role.USER.toInt(), username, email);
            return result == 6;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateUsername(BigInteger id, String username) {
        try {
            int result = jdbcTemplate.update(UPDATE_USERNAME, username, id, username);
            return result == 1;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updatePassword(BigInteger id, String password) {
        try {
            jdbcTemplate.update(UPDATE_PASSWORD, password, id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateEmail(BigInteger id, String email) {
        try {
            int result = jdbcTemplate.update(UPDATE_EMAIL, email, id, email);
            return result == 1;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateUserActiveStatus(BigInteger id, boolean isActive) {
        try {
            jdbcTemplate.update(UPDATE_USER_ACTIVE_STATUS, isActive, id);
            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
