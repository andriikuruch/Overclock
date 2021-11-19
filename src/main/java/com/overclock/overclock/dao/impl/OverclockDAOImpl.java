package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.OverclockDAO;
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
public class OverclockDAOImpl implements OverclockDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(OverclockDAOImpl.class.getName());

    private static boolean checkNotNull(Overclock overclock) {
        if (overclock == null) {
            return false;
        } else if (overclock.getCPUFrequency() == null || overclock.getCPUVoltage() == null ||
                    overclock.getGPUCoreFrequency() == null || overclock.getGPUMemoryFrequency() == null ||
                    overclock.getGPUVoltage() == null || overclock.getRAMVoltage() == null ||
                    overclock.getRAMTimings() == null || overclock.getRAMFrequency() == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean save(Overclock overclock, BigInteger assemblyId) {
        if (!checkNotNull(overclock)) {
            LOGGER.log(Level.WARNING, "Invalid overclock");
            return false;
        }
        try {
            try {
                int potentialOverclockId = jdbcTemplate.queryForObject("SELECT OBJECT_ID " +
                        "FROM OBJREFERENCE " +
                        "WHERE ATTR_ID = 2 AND REFERENCE = " + assemblyId, Integer.class);
                LOGGER.log(Level.WARNING, "Overclock for this assembly is already exists: id = " + potentialOverclockId);
                return false;
            } catch (DataAccessException dataAccessException) {
                int objectTypeId = jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE OBJECT_ID = " + assemblyId, Integer.class);
                if (objectTypeId == 1) {
                    String overclockName = "overclock" + UUID.randomUUID();
                    jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) " +
                            "VALUES (OBJECT_ID_SEQ.NEXTVAL," + assemblyId + ", 6, '" + overclockName + " ', NULL)");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(30, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getCPUFrequency() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(31, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getCPUVoltage() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(32, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getGPUCoreFrequency() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(33, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getGPUMemoryFrequency() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(34, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getGPUVoltage() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(35, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getRAMVoltage() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(36, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getRAMTimings() + "')");
                    jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                            "VALUES(37, OBJECT_ID_SEQ.CURRVAL, '" + overclock.getRAMFrequency() + "')");
                    jdbcTemplate.update("INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) " +
                            "VALUES (2, OBJECT_ID_SEQ.CURRVAL, " + assemblyId + ")");
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
            int objectTypeId = jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE OBJECT_ID = " + id, Integer.class);
            if (objectTypeId == 6) {
                jdbcTemplate.update("DELETE FROM ATTRIBUTES WHERE OBJECT_ID = "+ id);
                jdbcTemplate.update("DELETE FROM OBJREFERENCE WHERE OBJECT_ID = "+ id +" OR REFERENCE = "+ id);
                jdbcTemplate.update("DELETE FROM OBJECTS WHERE OBJECT_ID = "+ id);
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
    public boolean update(BigInteger id, Overclock newOverclock) {
        if (!checkNotNull(newOverclock)) {
            LOGGER.log(Level.WARNING, "Invalid overclock");
            return false;
        }
        try {
            int objectTypeId = jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE OBJECT_ID = " + id, Integer.class);
            if (objectTypeId == 6) {
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getCPUFrequency() + "' " +
                        "WHERE ATTR_ID = 30 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getCPUVoltage() + "' " +
                        "WHERE ATTR_ID = 31 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getGPUCoreFrequency() + "' " +
                        "WHERE ATTR_ID = 32 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getGPUMemoryFrequency() + "' " +
                        "WHERE ATTR_ID = 33 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getGPUVoltage() + "' " +
                        "WHERE ATTR_ID = 34 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getRAMVoltage() + "' " +
                        "WHERE ATTR_ID = 35 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getRAMTimings() + "' " +
                        "WHERE ATTR_ID = 36 AND OBJECT_ID = " + id);
                jdbcTemplate.update("UPDATE ATTRIBUTES " +
                        "SET VALUE = '" + newOverclock.getRAMFrequency() + "' " +
                        "WHERE ATTR_ID = 37 AND OBJECT_ID = " + id);
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
            String sql = "SELECT OVERCLOCKS.OBJECT_ID ID, " +
                    "CPU_FREQUENCY.VALUE CPU_FREQUENCY,CPU_VOLTAGE.VALUE CPU_VOLTAGE, " +
                    "GPU_CORE_FREQUENCY.VALUE GPU_CORE_FREQUENCY,GPU_MEMORY_FREQUENCY.VALUE GPU_MEMORY_FREQUENCY, GPU_VOLTAGE.VALUE GPU_VOLTAGE, " +
                    "RAM_VOLTAGE.VALUE RAM_VOLTAGE, RAM_TIMINGS.VALUE RAM_TIMINGS, RAM_FREQUENCY.VALUE RAM_FREQUENCY " +
                    "FROM OBJECTS OVERCLOCKS, ATTRIBUTES CPU_FREQUENCY, ATTRIBUTES CPU_VOLTAGE, " +
                    "   ATTRIBUTES GPU_CORE_FREQUENCY, ATTRIBUTES GPU_MEMORY_FREQUENCY, ATTRIBUTES GPU_VOLTAGE, " +
                    "   ATTRIBUTES RAM_VOLTAGE, ATTRIBUTES RAM_TIMINGS, ATTRIBUTES RAM_FREQUENCY " +
                    "       WHERE OVERCLOCKS.OBJECT_TYPE_ID = 6 " +
                    "       AND OVERCLOCKS.OBJECT_ID = "+ id +
                    "       AND CPU_FREQUENCY.ATTR_ID = 30 " +
                    "       AND CPU_VOLTAGE.ATTR_ID = 31 " +
                    "       AND GPU_CORE_FREQUENCY.ATTR_ID = 32 " +
                    "       AND GPU_MEMORY_FREQUENCY.ATTR_ID = 33 " +
                    "       AND GPU_VOLTAGE.ATTR_ID = 34 " +
                    "       AND RAM_VOLTAGE.ATTR_ID = 35 " +
                    "       AND RAM_TIMINGS.ATTR_ID = 36 " +
                    "       AND RAM_FREQUENCY.ATTR_ID = 37 " +
                    "       AND CPU_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND CPU_VOLTAGE.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND GPU_CORE_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND GPU_MEMORY_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND GPU_VOLTAGE.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND RAM_VOLTAGE.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND RAM_TIMINGS.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND RAM_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID";
            return jdbcTemplate.queryForObject(sql, new OverclockRowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }
}
