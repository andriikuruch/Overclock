package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.MotherboardDAO;
import com.overclock.overclock.dao.constant.QueryConstants;
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

    private static final String SQL_SELECT_MOTHERBOARD_BY_ID = "SELECT MOTHERBOARDS.OBJECT_ID ID, MOTHERBOARDS.NAME NAME," +
            " LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID CHIPSET_MANUFACTURER,\n" +
            "LISTS_CHIPSETS.LIST_VALUE_ID CHIPSET, LISTS_SOCKETS.LIST_VALUE_ID SOCKET\n" +
            "FROM OBJECTS MOTHERBOARDS, ATTRIBUTES CHIPSET_MANUFACTURERS, ATTRIBUTES CHIPSETS, ATTRIBUTES SOCKETS,\n" +
            "LISTS LISTS_CHIPSET_MANUFACTURERS,LISTS LISTS_CHIPSETS, LISTS LISTS_SOCKETS\n" +
            "WHERE  MOTHERBOARDS.OBJECT_ID =  ? " +
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
    private static final String SQL_SELECT_MOTHERBOARD_BY_ASSEMBLY_ID = "SELECT MOTHERBOARDS.OBJECT_ID ID, MOTHERBOARDS.NAME NAME, LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID CHIPSET_MANUFACTURER,\n" +
            "LISTS_CHIPSETS.LIST_VALUE_ID CHIPSET, LISTS_SOCKETS.LIST_VALUE_ID SOCKET\n" +
            "FROM OBJECTS MOTHERBOARDS, OBJECTS ASSEMBLY, OBJREFERENCE MOTHERBOARD_REF, ATTRIBUTES CHIPSET_MANUFACTURERS, ATTRIBUTES CHIPSETS, ATTRIBUTES SOCKETS,\n" +
            "LISTS LISTS_CHIPSET_MANUFACTURERS,LISTS LISTS_CHIPSETS, LISTS LISTS_SOCKETS\n" +
            "WHERE ASSEMBLY.OBJECT_ID = ? \n" +
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
    private static final String SQL_SELECT_ALL_MOTHERBOARDS = "SELECT MOTHERBOARDS.OBJECT_ID ID, MOTHERBOARDS.NAME NAME, " +
            "LISTS_CHIPSET_MANUFACTURERS.LIST_VALUE_ID CHIPSET_MANUFACTURER,\n" +
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

            return jdbcTemplate.queryForObject(SQL_SELECT_MOTHERBOARD_BY_ID, new MotherboardRowMapper(), new Object[]{id});
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public Motherboard getByAssemblyId(BigInteger assemblyId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_MOTHERBOARD_BY_ASSEMBLY_ID, new MotherboardRowMapper(), assemblyId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.log(Level.WARNING, dataAccessException.getMessage(), dataAccessException);
            return null;
        }
    }

    @Override
    public List<Motherboard> getAll() {
        try {
            return jdbcTemplate.query(SQL_SELECT_ALL_MOTHERBOARDS, new MotherboardRowMapper());
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
            jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_OBJECTS,null, 2, motherboard.getName(), null);
            jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID, 9, motherboard.getChipsetManufacturer().toInt());
            jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID, 10, motherboard.getChipset().toInt());
            jdbcTemplate.update(QueryConstants.SQL_INSERT_INTO_ATTRIBUTES_LIST_VALUE_ID, 11, motherboard.getSocket().toInt());
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
            int objectTypeId = jdbcTemplate.queryForObject(QueryConstants.SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (objectTypeId == 2) {
                jdbcTemplate.update(QueryConstants.SQL_DELETE_FROM_OBJREFERENCE, id, id);
                jdbcTemplate.update(QueryConstants.SQL_DELETE_FROM_ATTRIBUTES, id);
                jdbcTemplate.update(QueryConstants.SQL_DELETE_FROM_OBJECTS, id);
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
            int objectTypeId = jdbcTemplate.queryForObject(QueryConstants.SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (objectTypeId == 2){
            jdbcTemplate.update(QueryConstants.SQL_UPDATE_OBJECTS_NAME,newMotherboard.getName(), id);
            jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID, newMotherboard.getChipsetManufacturer().toInt(), 9, id); /* Chipset_manufacturer */
            jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID, newMotherboard.getChipset().toInt(), 10, id); /* Chipset */
            jdbcTemplate.update(QueryConstants.SQL_UPDATE_ATTRIBUTES_LIST_VALUE_ID, newMotherboard.getSocket().toInt(), 11, id); /* Socket */
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
