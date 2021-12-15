package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.OverclockDAO;
import com.overclock.overclock.dao.constant.QueryConstants;
import com.overclock.overclock.dao.impl.mapper.OverclockRowMapper;
import com.overclock.overclock.model.Overclock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class OverclockDAOImpl implements OverclockDAO, QueryConstants {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OverclockRowMapper overclockRowMapper;

    private static final Logger LOGGER = Logger.getLogger(OverclockDAOImpl.class.getName());

    private static boolean isOverclockInvalid(Overclock overclock) {
        if (overclock == null) {
            return true;
        }
        return overclock.getCPUFrequency() == null || overclock.getCPUVoltage() == null ||
                overclock.getGPUCoreFrequency() == null || overclock.getGPUMemoryFrequency() == null ||
                overclock.getGPUVoltage() == null || overclock.getRAMVoltage() == null ||
                overclock.getRAMTimings() == null || overclock.getRAMFrequency() == null;
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean save(Overclock overclock, BigInteger assemblyId) {
        if (isOverclockInvalid(overclock)) {
            LOGGER.log(Level.WARNING, "Invalid overclock");
            return false;
        }
        try {
            try {
                int potentialOverclockId = jdbcTemplate.queryForObject(SQL_SELECT_OVERCLOCK_ID_BY_ASSEMBLY_ID, Integer.class, assemblyId);
                LOGGER.log(Level.WARNING, "Overclock for this assembly is already exists: id = " + potentialOverclockId);
                return false;
            } catch (DataAccessException dataAccessException) {
                int assemblyObjectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, assemblyId);
                if (assemblyObjectTypeId == 1) { /* Assembly */
                    String overclockName = "overclock" + UUID.randomUUID();
                    jdbcTemplate.update(SQL_INSERT_INTO_OBJECTS, assemblyId, 6, overclockName, null);                  /* Overclock */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 30, overclock.getCPUFrequency().toString()); /* CPU Frequency */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 31, overclock.getCPUVoltage().toString());   /* CPU Voltage */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 32, overclock.getGPUCoreFrequency());        /* GPU Core Frequency */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 33, overclock.getGPUMemoryFrequency());      /* GPU Memory Frequency */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 34, overclock.getGPUVoltage().toString());   /* GPU Voltage */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 35, overclock.getRAMVoltage().toString());   /* RAM Voltage */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 36, overclock.getRAMTimings());              /* RAM Timings */
                    jdbcTemplate.update(SQL_INSERT_INTO_ATTRIBUTES_VALUE, 37, overclock.getRAMFrequency());            /* RAM Frequency */
                    jdbcTemplate.update(SQL_INSERT_INTO_OBJREFERENCE_REFERENCE, 2, assemblyId);                        /* Overclock id */
                    return true;
                } else {
                    LOGGER.log(Level.WARNING, "Identifier belongs not to a assembly");
                    return false;
                }
            }
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
            if (objectTypeId == 6) { /* Overclock */
                jdbcTemplate.update(SQL_DELETE_FROM_ATTRIBUTES, id);
                jdbcTemplate.update(SQL_DELETE_FROM_OBJREFERENCE, id, id);
                jdbcTemplate.update(SQL_DELETE_FROM_OBJECTS, id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Identifier belongs not to a overclock");
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
    public boolean deleteByAssemblyId(BigInteger assemblyId) {
        try {
            int overclockObjectId = jdbcTemplate.queryForObject(SQL_SELECT_OVERCLOCK_ID_BY_ASSEMBLY_ID, Integer.class, assemblyId);
            return delete(BigInteger.valueOf(overclockObjectId));
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean update(BigInteger id, Overclock newOverclock) {
        if (isOverclockInvalid(newOverclock)) {
            LOGGER.log(Level.WARNING, "Invalid overclock");
            return false;
        }
        try {
            int objectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (objectTypeId == 6) { /* Overclock */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getCPUFrequency().toString(), 30, id); /* CPU Frequency */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getCPUVoltage().toString(), 31, id);   /* CPU Voltage */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getGPUCoreFrequency(), 32, id);        /* GPU Core Frequency */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getGPUMemoryFrequency(), 33, id);      /* GPU Memory Frequency */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getGPUVoltage().toString(), 34, id);   /* GPU Voltage */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getRAMVoltage().toString(), 35, id);   /* RAM Voltage */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getRAMTimings(), 36, id);              /* RAM Timings */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newOverclock.getRAMFrequency(), 37, id);            /* RAM Frequency */
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Identifier belongs not to a overclock");
                return false;
            }
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public Overclock getOverclockById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_OVERCLOCK_BY_ID, overclockRowMapper, id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }
}