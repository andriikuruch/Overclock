package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.RAM_DAO;
import com.overclock.overclock.dao.impl.mapper.RAM_RowMapper;
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
public class RAM_DAOImpl implements RAM_DAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(RAM_DAOImpl.class.getName());

    @Override
    public RAM getById(BigInteger id) {
        try {
            String sql = "SELECT RAM.OBJECT_ID ID, RAM.NAME NAME, CAPACITY.VALUE CAPACITY, FREQ.VALUE FREQUENCY, TIMINGS.VALUE TIMINGS, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS RAM, ATTRIBUTES CAPACITY, ATTRIBUTES FREQ, ATTRIBUTES TIMINGS, ATTRIBUTES VOLTAGE " +
                    "        WHERE RAM.OBJECT_TYPE_ID = 5 " +
                    "        AND RAM.OBJECT_ID = CAPACITY.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = FREQ.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = TIMINGS.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = VOLTAGE.OBJECT_ID " +
                    "        AND CAPACITY.ATTR_ID = 26 " +
                    "        AND FREQ.ATTR_ID = 27 " +
                    "        AND TIMINGS.ATTR_ID = 28 " +
                    "        AND VOLTAGE.ATTR_ID = 29 " +
                    "        AND RAM.OBJECT_ID = " + id;
            return jdbcTemplate.queryForObject(sql, new RAM_RowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public RAM getByAssemblyId(BigInteger assemblyId) {
        try {
            String sql = "SELECT RAM.OBJECT_ID ID, RAM.NAME NAME, CAPACITY.VALUE CAPACITY, FREQ.VALUE FREQUENCY, TIMINGS.VALUE TIMINGS, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS RAM, OBJECTS ASSEMBLIES, ATTRIBUTES CAPACITY, ATTRIBUTES FREQ, ATTRIBUTES TIMINGS, ATTRIBUTES VOLTAGE, OBJREFERENCE RAM_REF " +
                    "        WHERE RAM.OBJECT_TYPE_ID = 5 " +
                    "        AND ASSEMBLIES.OBJECT_TYPE_ID = 1 " +
                    "        AND RAM.OBJECT_ID = CAPACITY.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = FREQ.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = TIMINGS.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = VOLTAGE.OBJECT_ID " +
                    "        AND CAPACITY.ATTR_ID = 26 " +
                    "        AND FREQ.ATTR_ID = 27 " +
                    "        AND TIMINGS.ATTR_ID = 28 " +
                    "        AND VOLTAGE.ATTR_ID = 29 " +
                    "        AND RAM_REF.OBJECT_ID = RAM.OBJECT_ID " +
                    "        AND RAM_REF.REFERENCE = ASSEMBLIES.OBJECT_ID " +
                    "        AND ASSEMBLIES.OBJECT_ID = " + assemblyId;
            return jdbcTemplate.queryForObject(sql, new RAM_RowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<RAM> getAll() {
        try {
            String sql = "SELECT RAM.OBJECT_ID ID, RAM.NAME NAME, CAPACITY.VALUE CAPACITY, FREQ.VALUE FREQUENCY, TIMINGS.VALUE TIMINGS, VOLTAGE.VALUE VOLTAGE " +
                    " FROM OBJECTS RAM, ATTRIBUTES CAPACITY, ATTRIBUTES FREQ, ATTRIBUTES TIMINGS, ATTRIBUTES VOLTAGE " +
                    "        WHERE RAM.OBJECT_TYPE_ID = 5 " +
                    "        AND RAM.OBJECT_ID = CAPACITY.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = FREQ.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = TIMINGS.OBJECT_ID " +
                    "        AND RAM.OBJECT_ID = VOLTAGE.OBJECT_ID " +
                    "        AND CAPACITY.ATTR_ID = 26 " +
                    "        AND FREQ.ATTR_ID = 27 " +
                    "        AND TIMINGS.ATTR_ID = 28 " +
                    "        AND VOLTAGE.ATTR_ID = 29";
            return jdbcTemplate.query(sql, new RAM_RowMapper());
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
            int queryCount = 0;
            queryCount += jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) "
                    + "VALUES (OBJECT_ID_SEQ.NEXTVAL, 5, '" + ram.getName() + "', NULL)");
            queryCount += jdbcTemplate.update( "INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) "
                    + "VALUES (26, OBJECT_ID_SEQ.CURRVAL, " + ram.getCapacity() + ")");
            queryCount += jdbcTemplate.update("INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) "
                    + "VALUES (27, OBJECT_ID_SEQ.CURRVAL, '" + ram.getFrequency() + "')");
            queryCount += jdbcTemplate.update("INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) "
                    + "VALUES (28, OBJECT_ID_SEQ.CURRVAL, '" + ram.getTimings() + "')");
            queryCount += jdbcTemplate.update("INSERT INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) "
                    + "VALUES (29, OBJECT_ID_SEQ.CURRVAL, '" + ram.getVoltage() + "')");
            return queryCount >= 5;
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
            int objectTypeId = jdbcTemplate.queryForObject(
                    "SELECT OBJECT_TYPE_ID FROM OBJECTS " +
                    "WHERE object_id = " + id, Integer.class);
            if (objectTypeId == 5) {
                int queryCount = 0;
                queryCount += jdbcTemplate.update("DELETE FROM ATTRIBUTES WHERE OBJECT_ID = "+ id);
                queryCount += jdbcTemplate.update("DELETE FROM OBJREFERENCE WHERE OBJECT_ID = "+ id +" OR REFERENCE = "+ id);
                queryCount += jdbcTemplate.update("DELETE FROM OBJECTS WHERE OBJECT_ID = "+ id);
                return queryCount >= 3;
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
            int queryCount = 0;
            queryCount += jdbcTemplate.update("UPDATE OBJECTS SET NAME = '" + newRam.getName() +
                    "' WHERE OBJECT_ID = " + id);
            queryCount += jdbcTemplate.update("UPDATE ATTRIBUTES SET VALUE = " + newRam.getCapacity() +
                    " WHERE OBJECT_ID = " + id + " AND ATTR_ID = 26");
            queryCount += jdbcTemplate.update("UPDATE ATTRIBUTES SET VALUE = " + newRam.getFrequency() +
                    " WHERE OBJECT_ID = " + id + " AND ATTR_ID = 27");
            queryCount += jdbcTemplate.update("UPDATE ATTRIBUTES SET VALUE = " + newRam.getTimings() +
                    " WHERE OBJECT_ID = " + id + " AND ATTR_ID = 28");
            queryCount += jdbcTemplate.update("UPDATE ATTRIBUTES SET VALUE = " + newRam.getVoltage() +
                    " WHERE OBJECT_ID = " + id + " AND ATTR_ID = 29");
            return queryCount >= 5;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private static boolean isRAMInvalid(RAM ram) {
        if (ram == null)
            return true;
        else return ram.getCapacity() == null || ram.getFrequency() == null || ram.getTimings() == null || ram.getVoltage() == null;
    }
}
