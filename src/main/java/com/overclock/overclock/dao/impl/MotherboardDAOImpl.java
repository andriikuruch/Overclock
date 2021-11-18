package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.dao.impl.mapper.CommentRowMapper;
import com.overclock.overclock.dao.impl.mapper.MotherboardRowMapper;
import com.overclock.overclock.model.Comment;
import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
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
public class MotherboardDAOImpl implements MotherboardDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = Logger.getLogger(MotherboardDAOImpl.class.getName());

    private static boolean checkNotNull(Motherboard motherboard) {
        if (motherboard == null) {
            return false;
        } else if (motherboard.getSocket() == null || motherboard.getChipset() == null || motherboard.getChipsetManufacturer() == null) {
            return false;
        }
        return true;
    }

    @Override
    public Motherboard getById(BigInteger id) {
        try {
            String sql ="SELECT MOTHERBOARDS.OBJECT_ID ID, MOTHERBOARDS.NAME NAME, LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID CHIPSET_MANUFACTURER,\n" +
                    "LISTS_CHIPSETS.LIST_VALUE_ID CHIPSET, LISTS_SOCKETS.LIST_VALUE_ID SOCKET\n" +
                    "FROM OBJECTS MOTHERBOARDS, ATTRIBUTES CHIPSET_MANUFACTURERS, ATTRIBUTES CHIPSETS, ATTRIBUTES SOCKETS,\n" +
                    "LISTS LISTS_CHIPSET_MANUFACTURERS,LISTS LISTS_CHIPSETS, LISTS LISTS_SOCKETS\n" +
                    "WHERE  MOTHERBOARDS.OBJECT_ID = " + id + "\n" +
                    "\n" +
                    "AND MOTHERBOARDS.OBJECT_ID = CHIPSET_MANUFACTURERS.OBJECT_ID\n" +
                    "AND MOTHERBOARDS.OBJECT_ID = CHIPSETS.OBJECT_ID\n" +
                    "AND MOTHERBOARDS.OBJECT_ID = SOCKETS.OBJECT_ID\n" +
                    "\n" +
                    "AND LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID = CHIPSET_MANUFACTURERS.LIST_VALUE_ID\n" +
                    "AND LISTS_CHIPSETS.LIST_VALUE_ID = CHIPSETS.LIST_VALUE_ID\n" +
                    "AND LISTS_SOCKETS.LIST_VALUE_ID = SOCKETS.LIST_VALUE_ID\n" +
                    "\n" +
                    "AND CHIPSET_MANUFACTURERS.ATTR_ID = 9\n" +
                    "AND CHIPSETS.ATTR_ID = 10\n" +
                    "AND SOCKETS.ATTR_ID = 11";
            return jdbcTemplate.queryForObject(sql, new MotherboardRowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public Motherboard getByAssemblyId(BigInteger assemblyId) {
        try {
            String sql = "SELECT MOTHERBOARDS.OBJECT_ID ID, MOTHERBOARDS.NAME NAME, LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID CHIPSET_MANUFACTURER,\n" +
                    "LISTS_CHIPSETS.LIST_VALUE_ID CHIPSET, LISTS_SOCKETS.LIST_VALUE_ID SOCKET\n" +
                    "FROM OBJECTS MOTHERBOARDS, OBJECTS ASSEMBLY, OBJREFERENCE MOTHERBOARD_REF, ATTRIBUTES CHIPSET_MANUFACTURERS, ATTRIBUTES CHIPSETS, ATTRIBUTES SOCKETS,\n" +
                    "LISTS LISTS_CHIPSET_MANUFACTURERS,LISTS LISTS_CHIPSETS, LISTS LISTS_SOCKETS\n" +
                    "WHERE ASSEMBLY.OBJECT_ID = " + assemblyId + "\n" +
                    "AND MOTHERBOARD_REF.REFERENCE = ASSEMBLY.OBJECT_ID\n" +
                    "AND MOTHERBOARD_REF.OBJECT_ID = MOTHERBOARDS.OBJECT_ID \n" +
                    "\n" +
                    "AND MOTHERBOARDS.OBJECT_ID = CHIPSET_MANUFACTURERS.OBJECT_ID\n" +
                    "AND MOTHERBOARDS.OBJECT_ID = CHIPSETS.OBJECT_ID\n" +
                    "AND MOTHERBOARDS.OBJECT_ID = SOCKETS.OBJECT_ID\n" +
                    "\n" +
                    "AND LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID = CHIPSET_MANUFACTURERS.LIST_VALUE_ID\n" +
                    "AND LISTS_CHIPSETS.LIST_VALUE_ID = CHIPSETS.LIST_VALUE_ID\n" +
                    "AND LISTS_SOCKETS.LIST_VALUE_ID = SOCKETS.LIST_VALUE_ID\n" +
                    "\n" +
                    "AND CHIPSET_MANUFACTURERS.ATTR_ID = 9\n" +
                    "AND CHIPSETS.ATTR_ID = 10\n" +
                    "AND SOCKETS.ATTR_ID = 11";
            return jdbcTemplate.queryForObject(sql, new MotherboardRowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<Motherboard> getAll() {
        try {
            String sql ="SELECT MOTHERBOARDS.OBJECT_ID ID, MOTHERBOARDS.NAME NAME, LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID CHIPSET_MANUFACTURER,\n" +
                    "LISTS_CHIPSETS.LIST_VALUE_ID CHIPSET, LISTS_SOCKETS.LIST_VALUE_ID SOCKET\n" +
                    "    FROM OBJECTS MOTHERBOARDS, ATTRIBUTES CHIPSET_MANUFACTURERS, ATTRIBUTES CHIPSETS, ATTRIBUTES SOCKETS,\n" +
                    "    LISTS LISTS_CHIPSET_MANUFACTURERS,LISTS LISTS_CHIPSETS, LISTS LISTS_SOCKETS\n" +
                    "        WHERE MOTHERBOARDS.OBJECT_TYPE_ID = 2\n" +
                    "        \n" +
                    "        AND MOTHERBOARDS.OBJECT_ID = CHIPSET_MANUFACTURERS.OBJECT_ID\n" +
                    "        AND MOTHERBOARDS.OBJECT_ID = CHIPSETS.OBJECT_ID\n" +
                    "        AND MOTHERBOARDS.OBJECT_ID = SOCKETS.OBJECT_ID\n" +
                    "        \n" +
                    "        AND LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID = CHIPSET_MANUFACTURERS.LIST_VALUE_ID\n" +
                    "        AND LISTS_CHIPSETS.LIST_VALUE_ID = CHIPSETS.LIST_VALUE_ID\n" +
                    "        AND LISTS_SOCKETS.LIST_VALUE_ID = SOCKETS.LIST_VALUE_ID\n" +
                    "        \n" +
                    "        AND CHIPSET_MANUFACTURERS.ATTR_ID = 9\n" +
                    "        AND CHIPSETS.ATTR_ID = 10\n" +
                    "        AND SOCKETS.ATTR_ID = 11";
            return jdbcTemplate.query(sql, new MotherboardRowMapper());
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }


    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean save(Motherboard motherboard) {
        if (!checkNotNull(motherboard)) {
            LOGGER.log(Level.WARNING, "Invalid motherboard");
            return false;
        }
        try {
            jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)"
                    +"VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,2,'" + motherboard.getName() + "',NULL)");
            jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)"
                    +"VALUES(9,OBJECT_ID_SEQ.CURRVAL,'" +  motherboard.getChipsetManufacturer().toInt() + "')");
            jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)"
                    +"VALUES(10, OBJECT_ID_SEQ.CURRVAL,'" +  motherboard.getChipset().toInt() + "')");
            jdbcTemplate.update("INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)"
                    +"VALUES(11,OBJECT_ID_SEQ.CURRVAL,'" +  motherboard.getSocket().toInt() + "')");
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
            int objectTypeId = jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE object_id = " + id, Integer.class);
            if (objectTypeId == 2) {
                jdbcTemplate.update("DELETE FROM OBJREFERENCE WHERE OBJECT_ID = "+ id);
                jdbcTemplate.update("DELETE FROM ATTRIBUTES WHERE OBJECT_ID = "+ id);
                jdbcTemplate.update("DELETE FROM OBJECTS WHERE OBJECT_ID = "+ id);
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Identifier belongs not to a motherboard");
                return false;
            }
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = {DataAccessException.class})
    public boolean update(BigInteger id, Motherboard newMotherboard) {
        if (!checkNotNull(newMotherboard)) {
            LOGGER.log(Level.WARNING, "Invalid motherboard");
            return false;
        }
        try {
            int objectTypeId = jdbcTemplate.queryForObject("SELECT OBJECT_TYPE_ID FROM OBJECTS WHERE object_id = " + id, Integer.class);
            if (objectTypeId == 2){
            jdbcTemplate.update("UPDATE OBJECTS SET NAME = '" + newMotherboard.getName() + "' WHERE OBJECT_ID = " + id);
            jdbcTemplate.update("UPDATE ATTRIBUTES SET LIST_VALUE_ID = " + newMotherboard.getChipsetManufacturer().toInt() + " WHERE ATTR_ID = 9 AND OBJECT_ID = " + id);
            jdbcTemplate.update("UPDATE ATTRIBUTES\n SET LIST_VALUE_ID = " + newMotherboard.getChipset().toInt() + " WHERE ATTR_ID = 10 AND OBJECT_ID = " + id);
            jdbcTemplate.update("UPDATE ATTRIBUTES SET LIST_VALUE_ID = " + newMotherboard.getSocket().toInt() + " WHERE ATTR_ID = 11 AND OBJECT_ID = " + id);
            return true;
            }
            else {
                LOGGER.log(Level.WARNING, "Identifier belongs not to a motherboard");
                return false;
            }
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
