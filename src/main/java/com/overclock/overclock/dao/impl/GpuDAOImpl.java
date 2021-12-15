package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.GpuDAO;
import com.overclock.overclock.dao.constant.QueryConstants;
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
public class GpuDAOImpl implements GpuDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(GpuDAOImpl.class.getName());
    private static final String SQL_SELECT_GPU_BY_ID = "SELECT GPU_OBJ.OBJECT_ID ID, GPU_OBJ.NAME NAME, CHIP_MANUF.LIST_VALUE_ID CHIP_MANUF_ID, CHIP.LIST_VALUE_ID CHIP_ID, " +
            "MEM_CAP.VALUE MEM_CAPACITY, CORE_FREQ.VALUE CORE_FREQUENCY, MEMORY_FREQ.VALUE MEMORY_FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
            "FROM OBJECTS GPU_OBJ, ATTRIBUTES CHIP_MANUF, ATTRIBUTES CHIP, ATTRIBUTES MEM_CAP, ATTRIBUTES CORE_FREQ, ATTRIBUTES MEMORY_FREQ, ATTRIBUTES VOLTAGE " +
            "WHERE GPU_OBJ.OBJECT_ID = ? " +
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
    private static final String SQL_SELECT_GPU_BY_ASSEMBLY_ID = "SELECT GPU_OBJ.OBJECT_ID ID, GPU_OBJ.NAME NAME, CHIP_MANUF.LIST_VALUE_ID CHIP_MANUF_ID, CHIP.LIST_VALUE_ID CHIP_ID, " +
            "MEM_CAP.VALUE MEM_CAPACITY, CORE_FREQ.VALUE CORE_FREQUENCY, MEMORY_FREQ.VALUE MEMORY_FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
            "FROM OBJECTS GPU_OBJ, OBJECTS ASSEMBLY_OBJ, OBJREFERENCE GPU_REF, ATTRIBUTES CHIP_MANUF, ATTRIBUTES CHIP, " +
            "ATTRIBUTES MEM_CAP, ATTRIBUTES CORE_FREQ, ATTRIBUTES MEMORY_FREQ, ATTRIBUTES VOLTAGE " +
            "WHERE ASSEMBLY_OBJ.OBJECT_ID = ? " +
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
    private static final String SQL_SELECT_ALL_GPUS = "SELECT GPU_OBJ.OBJECT_ID ID, GPU_OBJ.NAME NAME, CHIP_MANUF.LIST_VALUE_ID CHIP_MANUF_ID, CHIP.LIST_VALUE_ID CHIP_ID, " +
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

    @Override
    public GPU getById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_GPU_BY_ID, new GPURowMapper(), new Object[]{id});
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public GPU getByAssemblyId(BigInteger assemblyId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_GPU_BY_ASSEMBLY_ID, new GPURowMapper(), assemblyId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<GPU> getAll() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_GPUS, new GPURowMapper());
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
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_OBJECTS, null, 4, gpu.getName(), null);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID, 20, gpu.getChipManufacturer().toInt());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID, 21, gpu.getChip().toInt());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE, 22, gpu.getMemoryCapacity());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE, 23, gpu.getCoreFrequency());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE, 24, gpu.getMemoryFrequency());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE, 25, gpu.getVoltage().toString());
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
            if (jdbcTemplate.queryForObject(QueryConstants.SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id) != 4) {
                LOGGER.log(Level.WARNING, "Transmitted ID does not belong to the GPU object!");
                return false;
            }
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_DELETE_FROM_OBJREFERENCE, id, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_DELETE_FROM_ATTRIBUTES, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_DELETE_FROM_OBJECTS, id);
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
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_OBJECTS_NAME, newGpu.getName(), id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID, newGpu.getChipManufacturer().toInt(), 20, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID, newGpu.getChip().toInt(), 21, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE, newGpu.getMemoryCapacity(), 22, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE, newGpu.getCoreFrequency(), 23, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE, newGpu.getMemoryFrequency(), 24, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE, newGpu.getVoltage(), 25, id);
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