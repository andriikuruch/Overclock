package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CpuDAO;
import com.overclock.overclock.dao.constant.QueryConstants;
import com.overclock.overclock.dao.impl.mapper.CPURowMapper;
import com.overclock.overclock.model.CPU;
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
public class CpuDAOImpl implements CpuDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(CpuDAOImpl.class.getName());
    private static final String SQL_SELECT_CPU_BY_ID = "SELECT CPU_OBJ.OBJECT_ID ID, CPU_OBJ.NAME NAME, " +
            "MANUF.LIST_VALUE_ID MANUF_ID, FAMILY.LIST_VALUE_ID FAMILY_ID, SOCKET.LIST_VALUE_ID SOCKET_ID, " +
            "GEN.LIST_VALUE_ID GEN_ID, NUM_OF_CORES.VALUE NUM_OF_CORES, NUM_OF_THR.VALUE NUM_OF_THR, " +
            "FREQ.VALUE FREQUENCY, VOLTAGE.VALUE VOLTAGE "+
            "FROM OBJECTS CPU_OBJ, ATTRIBUTES MANUF, ATTRIBUTES FAMILY, ATTRIBUTES SOCKET, ATTRIBUTES GEN, " +
            "ATTRIBUTES NUM_OF_CORES, ATTRIBUTES NUM_OF_THR, ATTRIBUTES FREQ, ATTRIBUTES VOLTAGE " +
            "WHERE CPU_OBJ.OBJECT_ID = ? " +
            "AND MANUF.ATTR_ID = 12 " +
            "AND MANUF.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND FAMILY.ATTR_ID = 13 " +
            "AND FAMILY.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND SOCKET.ATTR_ID = 14 " +
            "AND SOCKET.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND GEN.ATTR_ID = 15 " +
            "AND GEN.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND NUM_OF_CORES.ATTR_ID = 16 " +
            "AND NUM_OF_CORES.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND NUM_OF_THR.ATTR_ID = 17 " +
            "AND NUM_OF_THR.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND FREQ.ATTR_ID = 18 " +
            "AND FREQ.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND VOLTAGE.ATTR_ID = 19 " +
            "AND VOLTAGE.OBJECT_ID = CPU_OBJ.OBJECT_ID ";
    private static final String SQL_SELECT_CPU_BY_ASSEMBLY_ID = "SELECT CPU_OBJ.OBJECT_ID ID, " +
            "CPU_OBJ.NAME NAME, MANUF.LIST_VALUE_ID MANUF_ID, SOCKET.LIST_VALUE_ID SOCKET_ID, " +
            "GEN.LIST_VALUE_ID GEN_ID, FAMILY.LIST_VALUE_ID FAMILY_ID, VOLTAGE.VALUE VOLTAGE, " +
            "FREQ.VALUE FREQUENCY, NUM_OF_THR.VALUE NUM_OF_THR, NUM_OF_CORES.VALUE NUM_OF_CORES " +
            "FROM OBJECTS CPU_OBJ, OBJECTS ASSEMBLY_OBJ, OBJREFERENCE CPU_REF, ATTRIBUTES MANUF, " +
            "ATTRIBUTES SOCKET, ATTRIBUTES GEN, ATTRIBUTES FAMILY, ATTRIBUTES VOLTAGE, ATTRIBUTES FREQ, " +
            "ATTRIBUTES NUM_OF_THR, ATTRIBUTES NUM_OF_CORES " +
            "WHERE ASSEMBLY_OBJ.OBJECT_ID = ? " +
            "AND CPU_REF.REFERENCE = ASSEMBLY_OBJ.OBJECT_ID " +
            "AND CPU_OBJ.OBJECT_ID = CPU_REF.OBJECT_ID " +
            "AND MANUF.ATTR_ID = 12 " +
            "AND MANUF.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND SOCKET.ATTR_ID = 14 " +
            "AND SOCKET.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND GEN.ATTR_ID = 15 " +
            "AND GEN.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND FAMILY.ATTR_ID = 13 " +
            "AND FAMILY.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND VOLTAGE.ATTR_ID = 19 " +
            "AND VOLTAGE.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND FREQ.ATTR_ID = 18 " +
            "AND FREQ.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND NUM_OF_THR.ATTR_ID = 17 " +
            "AND NUM_OF_THR.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND NUM_OF_CORES.ATTR_ID = 16 " +
            "AND NUM_OF_CORES.OBJECT_ID = CPU_OBJ.OBJECT_ID ";
    private static final String SQL_SELECT_ALL_CPUS = "SELECT CPU_OBJ.OBJECT_ID ID, " +
            "CPU_OBJ.NAME NAME, MANUF.LIST_VALUE_ID MANUF_ID, FAMILY.LIST_VALUE_ID FAMILY_ID, " +
            "SOCKET.LIST_VALUE_ID SOCKET_ID, GEN.LIST_VALUE_ID GEN_ID, NUM_OF_CORES.VALUE NUM_OF_CORES, " +
            "NUM_OF_THR.VALUE NUM_OF_THR, FREQ.VALUE FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
            "FROM OBJECTS CPU_OBJ, ATTRIBUTES MANUF, ATTRIBUTES FAMILY, ATTRIBUTES SOCKET, ATTRIBUTES GEN, " +
            "ATTRIBUTES NUM_OF_CORES, ATTRIBUTES NUM_OF_THR, ATTRIBUTES FREQ, ATTRIBUTES VOLTAGE " +
            "WHERE CPU_OBJ.OBJECT_TYPE_ID = 3 " +
            "AND MANUF.ATTR_ID = 12 " +
            "AND MANUF.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND FAMILY.ATTR_ID = 13 " +
            "AND FAMILY.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND SOCKET.ATTR_ID = 14 " +
            "AND SOCKET.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND GEN.ATTR_ID = 15 " +
            "AND GEN.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND NUM_OF_CORES.ATTR_ID = 16 " +
            "AND NUM_OF_CORES.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND NUM_OF_THR.ATTR_ID = 17 " +
            "AND NUM_OF_THR.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND FREQ.ATTR_ID = 18 " +
            "AND FREQ.OBJECT_ID = CPU_OBJ.OBJECT_ID " +
            "AND VOLTAGE.ATTR_ID = 19 " +
            "AND VOLTAGE.OBJECT_ID = CPU_OBJ.OBJECT_ID ";

    @Override
    public CPU getById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CPU_BY_ID, new CPURowMapper(), new Object[]{id});

        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public CPU getByAssemblyId(BigInteger assemblyId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CPU_BY_ASSEMBLY_ID, new CPURowMapper(), assemblyId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<CPU> getAll() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_CPUS, new CPURowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean save(CPU cpu) {
        if (!isValidCPU(cpu)) {
            LOGGER.log(Level.WARNING, "Invalid CPU!");
            return false;
        }
        try {
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_OBJECTS, null, 3,
                    cpu.getName(), null);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID,
                    12, cpu.getManufacturer().toInt());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID,
                    13, cpu.getFamily().toInt());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID,
                    14, cpu.getSocket().toInt());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID,
                    15, cpu.getGeneration().toInt());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE,
                    16, cpu.getCoresNumber());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE,
                    17, cpu.getThreadsNumber());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE,
                    18, cpu.getFrequency());
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_VALUE,
                    19, cpu.getVoltage());
            if (countOfQueries >= 9) {
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
            if (jdbcTemplate.queryForObject(QueryConstants.SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID,
                    Integer.class, id) != 3) {
                LOGGER.log(Level.WARNING, "Transmitted ID does not belong to the CPU object!");
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
    public boolean update(BigInteger id, CPU newCpu) {
        if (!isValidCPU(newCpu)) {
            LOGGER.log(Level.WARNING, "Invalid CPU!");
            return false;
        }
        try {
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_OBJECTS_NAME,
                    newCpu.getName(), id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID,
                    newCpu.getManufacturer().toInt(), 12, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID,
                    newCpu.getFamily().toInt(), 13, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID,
                    newCpu.getSocket().toInt(), 14, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID,
                    newCpu.getGeneration().toInt(), 15, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE,
                    newCpu.getCoresNumber(), 16, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE,
                    newCpu.getThreadsNumber(), 17, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE,
                    newCpu.getFrequency(), 18, id);
            countOfQueries += jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_VALUE,
                    newCpu.getVoltage(), 19, id);
            return countOfQueries >= 9;
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private boolean isValidCPU(CPU cpu) {
        return cpu != null && cpu.getName() != null && cpu.getFamily() != null && cpu.getGeneration() != null &&
                cpu.getManufacturer() != null && cpu.getSocket() != null && cpu.getCoresNumber() != null &&
                cpu.getThreadsNumber() != null && cpu.getFrequency() != null && cpu.getVoltage() != null;
    }
}
