package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.CpuDAO;
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

    @Override
    public CPU getById(BigInteger id) {
        try {
            String sql = "SELECT CPU_OBJ.OBJECT_ID ID, CPU_OBJ.NAME NAME, MANUF.LIST_VALUE_ID MANUF_ID, " +
                    "FAMILY.LIST_VALUE_ID FAMILY_ID, SOCKET.LIST_VALUE_ID SOCKET_ID, GEN.LIST_VALUE_ID GEN_ID, " +
                    "NUM_OF_CORES.VALUE NUM_OF_CORES, NUM_OF_THR.VALUE NUM_OF_THR, FREQ.VALUE FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS CPU_OBJ, ATTRIBUTES MANUF, ATTRIBUTES FAMILY, ATTRIBUTES SOCKET, ATTRIBUTES GEN, ATTRIBUTES NUM_OF_CORES, " +
                    "ATTRIBUTES NUM_OF_THR, ATTRIBUTES FREQ, ATTRIBUTES VOLTAGE " +
                    "WHERE CPU_OBJ.OBJECT_ID = " + id + " " +
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
            return jdbcTemplate.queryForObject(sql, new CPURowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public CPU getByAssemblyId(BigInteger assemblyId) {
        try {
            String sql = "SELECT CPU_OBJ.OBJECT_ID ID, CPU_OBJ.NAME NAME, MANUF.LIST_VALUE_ID MANUF_ID, SOCKET.LIST_VALUE_ID SOCKET_ID, " +
                    "GEN.LIST_VALUE_ID GEN_ID, FAMILY.LIST_VALUE_ID FAMILY_ID, VOLTAGE.VALUE VOLTAGE, FREQ.VALUE FREQUENCY, " +
                    "NUM_OF_THR.VALUE NUM_OF_THR, NUM_OF_CORES.VALUE NUM_OF_CORES " +
                    "FROM OBJECTS CPU_OBJ, OBJECTS ASSEMBLY_OBJ, OBJREFERENCE CPU_REF, ATTRIBUTES MANUF, ATTRIBUTES SOCKET, " +
                    "ATTRIBUTES GEN, ATTRIBUTES FAMILY, ATTRIBUTES VOLTAGE, ATTRIBUTES FREQ, ATTRIBUTES NUM_OF_THR, ATTRIBUTES NUM_OF_CORES " +
                    "WHERE ASSEMBLY_OBJ.OBJECT_ID = " + assemblyId + " " +
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
            return jdbcTemplate.queryForObject(sql, new CPURowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<CPU> getAll() {
        try {
            String sql = "SELECT CPU_OBJ.OBJECT_ID ID, CPU_OBJ.NAME NAME, MANUF.LIST_VALUE_ID MANUF_ID, FAMILY.LIST_VALUE_ID FAMILY_ID, " +
                    "SOCKET.LIST_VALUE_ID SOCKET_ID, GEN.LIST_VALUE_ID GEN_ID, NUM_OF_CORES.VALUE NUM_OF_CORES, NUM_OF_THR.VALUE NUM_OF_THR, " +
                    "FREQ.VALUE FREQUENCY, VOLTAGE.VALUE VOLTAGE " +
                    "FROM OBJECTS CPU_OBJ, ATTRIBUTES MANUF, ATTRIBUTES FAMILY, ATTRIBUTES SOCKET, ATTRIBUTES GEN, ATTRIBUTES NUM_OF_CORES, " +
                    "ATTRIBUTES NUM_OF_THR, ATTRIBUTES FREQ, ATTRIBUTES VOLTAGE " +
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
            return jdbcTemplate.query(sql, new CPURowMapper());
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
            countOfQueries += jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) " +
                    "VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,3,'" + cpu.getName() + "',NULL)");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                    "VALUES(12, OBJECT_ID_SEQ.CURRVAL, " + cpu.getManufacturer().toInt() + ")");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                    "VALUES(13, OBJECT_ID_SEQ.CURRVAL, " + cpu.getFamily().toInt() + ")");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                    "VALUES(14, OBJECT_ID_SEQ.CURRVAL, " + cpu.getSocket().toInt() + ")");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID) " +
                    "VALUES(15, OBJECT_ID_SEQ.CURRVAL, " + cpu.getGeneration().toInt() + ")");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(16, OBJECT_ID_SEQ.CURRVAL, '" + cpu.getCoresNumber() + "')");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(17, OBJECT_ID_SEQ.CURRVAL, '" + cpu.getThreadsNumber() + "')");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(18, OBJECT_ID_SEQ.CURRVAL, '" + cpu.getFrequency() + "')");
            countOfQueries += jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE) " +
                    "VALUES(19, OBJECT_ID_SEQ.CURRVAL, '" + cpu.getVoltage() + "')");
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
            if (jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID " +
                    "FROM OBJECTS " +
                    "WHERE OBJECT_ID = " + id, Integer.class) != 3) {
                LOGGER.log(Level.WARNING, "Transmitted ID does not belong to the CPU object!");
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
    public boolean update(BigInteger id, CPU newCpu) {
        if (!isValidCPU(newCpu)) {
            LOGGER.log(Level.WARNING, "Invalid CPU!");
            return false;
        }
        try {
            int countOfQueries = 0;
            countOfQueries += jdbcTemplate.update("UPDATE OBJECTS " +
                    "SET NAME = '" + newCpu.getName() + "' " +
                    "WHERE OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET LIST_VALUE_ID = " + newCpu.getManufacturer().toInt() + " " +
                    "WHERE ATTR_ID = 12 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET LIST_VALUE_ID = " + newCpu.getFamily().toInt() + " " +
                    "WHERE ATTR_ID = 13 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET LIST_VALUE_ID = " + newCpu.getSocket().toInt() + " " +
                    "WHERE ATTR_ID = 14 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET LIST_VALUE_ID = " + newCpu.getGeneration().toInt() + " " +
                    "WHERE ATTR_ID = 15 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newCpu.getCoresNumber() + " " +
                    "WHERE ATTR_ID = 16 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newCpu.getThreadsNumber() + " " +
                    "WHERE ATTR_ID = 17 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newCpu.getFrequency() + " " +
                    "WHERE ATTR_ID = 18 " +
                    "AND OBJECT_ID = " + id);
            countOfQueries += jdbcTemplate.update("UPDATE ATTRIBUTES " +
                    "SET VALUE = " + newCpu.getVoltage() + " " +
                    "WHERE ATTR_ID = 19 " +
                    "AND OBJECT_ID = " + id);
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
