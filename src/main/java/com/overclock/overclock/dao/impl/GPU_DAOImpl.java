package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.GPU_DAO;
import com.overclock.overclock.dao.impl.mapper.GPURowMapper;
import com.overclock.overclock.model.GPU;
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
public class GPU_DAOImpl implements GPU_DAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(GPU_DAOImpl.class.getName());

    @Override
    public GPU getById(BigInteger id) {
        try {
            String sql = "SELECT GPU_OBJ.OBJECT_ID ID, GPU_OBJ.NAME NAME, CHIP_MANUF.LIST_VALUE_ID CHIP_MANUF_ID, CHIP.LIST_VALUE_ID CHIP_ID, " +
                    "MEM_CAP.VALUE MEM_CAPACITY, CORE_FREQ.VALUE CORE_FREQUENCY, MEMORY_FREQ.VALUE MEMORY_FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS GPU_OBJ, ATTRIBUTES CHIP_MANUF, ATTRIBUTES CHIP, ATTRIBUTES MEM_CAP, ATTRIBUTES CORE_FREQ, ATTRIBUTES MEMORY_FREQ, ATTRIBUTES VOLTAGE " +
                    "WHERE GPU_OBJ.OBJECT_ID = " + id + " " +
                    "AND MEM_CAP.ATTR_ID = 22 " +
                    "AND MEM_CAP.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CORE_FREQ.ATTR_ID = 23 " +
                    "AND CORE_FREQ.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND MEMORY_FREQ.ATTR_ID = 24 " +
                    "AND MEMORY_FREQ.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND VOLTAGE.ATTR_ID = 25 " +
                    "AND VOLTAGE.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CHIP_MANUF.ATTR_ID = 20 " +
                    "AND CHIP_MANUF.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CHIP.ATTR_ID = 21 " +
                    "AND CHIP.OBJECT_ID = GPU_OBJ.OBJECT_ID";
            return jdbcTemplate.queryForObject(sql, new GPURowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public GPU getByAssemblyId(BigInteger assemblyId) {
        try {
            String sql = "SELECT GPU_OBJ.OBJECT_ID ID, GPU_OBJ.NAME NAME, CHIP_MANUF.LIST_VALUE_ID CHIP_MANUF_ID, CHIP.LIST_VALUE_ID CHIP_ID, " +
                    "MEM_CAP.VALUE MEM_CAPACITY, CORE_FREQ.VALUE CORE_FREQUENCY, MEMORY_FREQ.VALUE MEMORY_FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS GPU_OBJ, OBJECTS ASSEMBLY_OBJ, OBJREFERENCE GPU_REF, ATTRIBUTES CHIP_MANUF, ATTRIBUTES CHIP, " +
                    "ATTRIBUTES MEM_CAP, ATTRIBUTES CORE_FREQ, ATTRIBUTES MEMORY_FREQ, ATTRIBUTES VOLTAGE " +
                    "WHERE ASSEMBLY_OBJ.OBJECT_ID = " + assemblyId + " " +
                    "AND GPU_REF.REFERENCE = ASSEMBLY_OBJ.OBJECT_ID " +
                    "AND GPU_OBJ.OBJECT_ID = GPU_REF.OBJECT_ID " +
                    "AND MEM_CAP.ATTR_ID = 22 " +
                    "AND MEM_CAP.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CORE_FREQ.ATTR_ID = 23 " +
                    "AND CORE_FREQ.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND MEMORY_FREQ.ATTR_ID = 24 " +
                    "AND MEMORY_FREQ.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND VOLTAGE.ATTR_ID = 25 " +
                    "AND VOLTAGE.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CHIP_MANUF.ATTR_ID = 20 " +
                    "AND CHIP_MANUF.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CHIP.ATTR_ID = 21 " +
                    "AND CHIP.OBJECT_ID = GPU_OBJ.OBJECT_ID";
            return jdbcTemplate.queryForObject(sql, new GPURowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<GPU> getAll() {
        try {
            String sql = "SELECT GPU_OBJ.OBJECT_ID ID, GPU_OBJ.NAME NAME, CHIP_MANUF.LIST_VALUE_ID CHIP_MANUF_ID, CHIP.LIST_VALUE_ID CHIP_ID, " +
                    "MEM_CAP.VALUE MEM_CAPACITY, CORE_FREQ.VALUE CORE_FREQUENCY, MEMORY_FREQ.VALUE MEMORY_FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS GPU_OBJ, ATTRIBUTES CHIP_MANUF, ATTRIBUTES CHIP, ATTRIBUTES MEM_CAP, ATTRIBUTES CORE_FREQ, ATTRIBUTES MEMORY_FREQ, ATTRIBUTES VOLTAGE " +
                    "WHERE GPU_OBJ.OBJECT_TYPE_ID = 4 " +
                    "AND MEM_CAP.ATTR_ID = 22 " +
                    "AND MEM_CAP.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CORE_FREQ.ATTR_ID = 23 " +
                    "AND CORE_FREQ.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND MEMORY_FREQ.ATTR_ID = 24 " +
                    "AND MEMORY_FREQ.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND VOLTAGE.ATTR_ID = 25 " +
                    "AND VOLTAGE.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CHIP_MANUF.ATTR_ID = 20 " +
                    "AND CHIP_MANUF.OBJECT_ID = GPU_OBJ.OBJECT_ID " +
                    "AND CHIP.ATTR_ID = 21 " +
                    "AND CHIP.OBJECT_ID = GPU_OBJ.OBJECT_ID";
            return jdbcTemplate.query(sql, new GPURowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean save(GPU gpu) {
        if (!isValidGPU(gpu)) {
            LOGGER.log(Level.WARNING, "Invalid GPU!");
            return false;
        }
        try {
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) " +
                    "VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,4,'" + gpu.getName() + "',NULL)");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                    "VALUES(20, OBJECT_ID_SEQ.CURRVAL, " + gpu.getChipManufacturer().toInt() + ")");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                    "VALUES(21, OBJECT_ID_SEQ.CURRVAL, " + gpu.getChip().toInt() + ")");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(22, OBJECT_ID_SEQ.CURRVAL, '" + gpu.getMemoryCapacity() + "')");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(23, OBJECT_ID_SEQ.CURRVAL, '" + gpu.getCoreFrequency() + "')");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(24, OBJECT_ID_SEQ.CURRVAL, '" + gpu.getMemoryFrequency() + "')");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(25, OBJECT_ID_SEQ.CURRVAL, '" + gpu.getVoltage() + "')");
            if (countOfQueries >= 7) {
                return true;
            }
            return false;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean delete(BigInteger id) {
        try {
            if (jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID " +
                    "FROM OBJECTS " +
                    "WHERE OBJECT_ID = " + id, Integer.class) != 4) {
                LOGGER.log(Level.WARNING, "Transmitted ID does not belong to the GPU object!");
                return false;
            }
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update("DELETE FROM OBJREFERENCE " +
                    "WHERE OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("DELETE FROM ATTRIBUTES " +
                    "WHERE OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("DELETE FROM OBJECTS " +
                    "WHERE OBJECT_ID = " + id);
            if (countOfQueries >= 3) {
                return true;
            }
            return false;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean update(BigInteger id, GPU newGpu) {
        if (!isValidGPU(newGpu)) {
            LOGGER.log(Level.WARNING, "Invalid GPU!");
            return false;
        }
        try {
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update("UPDATE OBJECTS " +
                    "SET NAME = '" + newGpu.getName() + "' " +
                    "WHERE OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET LIST_VALUE_ID = " + newGpu.getChipManufacturer().toInt() + " " +
                    "WHERE ATTR_ID = 20 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET LIST_VALUE_ID = " + newGpu.getChip().toInt() + " " +
                    "WHERE ATTR_ID = 21 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newGpu.getMemoryCapacity() + " " +
                    "WHERE ATTR_ID = 22 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newGpu.getCoreFrequency() + " " +
                    "WHERE ATTR_ID = 23 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newGpu.getMemoryFrequency() + " " +
                    "WHERE ATTR_ID = 24 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newGpu.getVoltage() + " " +
                    "WHERE ATTR_ID = 25 " +
                    "AND OBJECT_ID = " + id);
            return countOfQueries >= 7;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private boolean isValidGPU(GPU gpu) {
        return gpu != null && gpu.getName() != null && gpu.getChipManufacturer() != null && gpu.getChip() != null && gpu.getMemoryCapacity() != null
                && gpu.getCoreFrequency() != null && gpu.getMemoryFrequency() != null && gpu.getVoltage() != null;
    }
}