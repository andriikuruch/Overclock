package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.RamDAO;
import com.overclock.overclock.dao.constant.QueryConstants;
import com.overclock.overclock.dao.impl.mapper.RAMRowMapper;
import com.overclock.overclock.model.RAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class RAMDAOImpl implements RamDAO, QueryConstants {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RAMRowMapper ramRowMapper;

    private static final Logger LOGGER = Logger.getLogger(RAMDAOImpl.class.getName());

    private static boolean isRAMInvalid(RAM ram) {
        if (ram == null)
            return true;
        else return ram.getCapacity() == null || ram.getFrequency() == null || ram.getTimings() == null || ram.getVoltage() == null;
    }

    @Override
    public RAM getRamById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_RAM_BY_ID, ramRowMapper, id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public RAM getRamByAssemblyId(BigInteger assemblyId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_RAM_BY_ASSEMBLY_ID, ramRowMapper, assemblyId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<RAM> getAllRams() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_RAM, ramRowMapper);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean save(RAM ram) {
        if (isRAMInvalid(ram)) {
            LOGGER.log(Level.WARNING, "Invalid RAM");
            return false;
        }
        try {
            jdbcTemplate.update(SQL_INSERT_INTO_OBJECTS, null, 5, ram.getName(), null);                /* RAM Name */
            jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 26, ram.getCapacity());              /* RAM Capacity */
            jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 27, ram.getFrequency());             /* RAM Frequency */
            jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 28, ram.getTimings());               /* RAM Timings */
            jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 29, ram.getVoltage().toString());    /* RAM Voltage */
            return true;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(BigInteger id) {
        try {
            int objectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (objectTypeId == 5) { /* RAM */
                jdbcTemplate.update(SQL_DELETE_FROM_ATTRIBUTES, id);
                jdbcTemplate.update(SQL_DELETE_FROM_OBJREFERENCE, id, id);
                jdbcTemplate.update(SQL_DELETE_FROM_OBJECTS, id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "No RAM with given identifier");
                return false;
            }
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean update(BigInteger id, RAM newRam) {
        if (isRAMInvalid(newRam)) {
            LOGGER.log(Level.WARNING, "Invalid RAM");
            return false;
        }
        try {
            int objectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (objectTypeId == 5) { /* RAM */
                jdbcTemplate.update(SQL_UPDATE_OBJECTS_NAME, newRam.getName(), id);                              /* RAM Name */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newRam.getCapacity(), 26, id);           /* RAM Capacity */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newRam.getFrequency(), 27, id);          /* RAM Frequency */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newRam.getTimings(), 28, id);            /* RAM Timings */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newRam.getVoltage().toString(), 29, id); /* RAM Voltage */
                return true;
            } else {
                LOGGER.log(Level.WARNING, "No RAM with given identifier");
                return false;
            }
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
