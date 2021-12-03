package com.overclock.overclock.dao.impl;

import com.overclock.overclock.dao.*;
import com.overclock.overclock.dao.constant.QueryConstants;
import com.overclock.overclock.dao.impl.mapper.AssemblyListRowMapper;
import com.overclock.overclock.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;
import java.util.List;


@Repository
public class AssemblyDAOImpl implements AssemblyDAO, QueryConstants {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssemblyDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AssemblyListRowMapper assemblyListRowMapper;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private OverclockDAO overclockDAO;

    @Autowired
    private CpuDAO cpuDAO;

    @Autowired
    private GpuDAO gpuDao;

    @Autowired
    private MotherboardDAO motherboardDAO;

    @Autowired
    private RamDAO ramDao;

    @Override
    public Assembly getAssemblyById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(QUERY_GET_BY_ID, (rs, rowNum) -> {
                BigInteger assemblyId = BigInteger.valueOf(rs.getLong("ASSEMBLY_ID"));
                BigInteger score = BigInteger.valueOf(rs.getLong("SCORE"));

                long overclockIdLong = rs.getLong("OVERCLOCK_ID");
                BigInteger overclockId = overclockIdLong != 0 ? BigInteger.valueOf(overclockIdLong) : null;
                BigInteger authorId = BigInteger.valueOf(rs.getLong("AUTHOR_ID"));

                Motherboard motherboard = motherboardDAO.getMotherboardByAssemblyId(id);
                CPU cpu = cpuDAO.getByAssemblyId(id);
                GPU gpu = gpuDao.getByAssemblyId(id);
                RAM ram = ramDao.getRamByAssemblyId(id);
                List<Comment> comments = commentDAO.getAllCommentsByAssemblyId(id);

                return new Assembly.Builder(assemblyId, rs.getString("ASSEMBLY_NAME"))
                        .setScore(score)
                        .setAuthor(authorId)
                        .setRam(ram)
                        .setGpu(gpu)
                        .setCpu(cpu)
                        .setMotherboard(motherboard)
                        .setOverclock(overclockId)
                        .setComments(comments)
                        .build();
            }, id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Assembly> getAllAssemblies() {
        return jdbcTemplate.query(QUERY_GET_ALL, assemblyListRowMapper);
    }

    @Override
    public List<Assembly> getAssembliesByAuthorId(BigInteger author) {
        return jdbcTemplate.query(QUERY_GET_ALL_BY_AUTHOR_ID, assemblyListRowMapper, author);
    }

    @Override
    public List<Assembly> getAssembliesByAuthorName(String author) {
        try {
        Integer id = jdbcTemplate.queryForObject(SQL_SELECT_USER_ID, Integer.class, author);
        return getAssembliesByAuthorId(BigInteger.valueOf(id));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No assemblies with such author name", e);
            return null;
        }
    }

    @Override
    @Transactional
    public boolean save(Assembly assembly) {
        try {
            jdbcTemplate.update(INSERT_ASSEMBLY, assembly.getName(),
                    assembly.getScore(),
                    assembly.getAuthor(),
                    assembly.getMotherboard().getId(),
                    assembly.getCpu().getId(),
                    assembly.getGpu().getId(),
                    assembly.getRam().getId());
            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(BigInteger id) {
        try {
            int result = 0;
            result += jdbcTemplate.update(DELETE_SCORE, id);
            boolean successfulCommentDeletingResult = commentDAO.deleteAllCommentsByAssemblyId(id);
            boolean successfulOverclockDeletingResult = overclockDAO.deleteByAssemblyId(id);
            result += jdbcTemplate.update(DELETE_REFERENCES, id);
            result += jdbcTemplate.update(DELETE_ASSEMBLY, id);

            if (result != 7 && !successfulCommentDeletingResult && !successfulOverclockDeletingResult) {
                LOGGER.warn("Can not delete assembly by id=" + id);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }

            return true;
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateScore(BigInteger id, BigInteger newScore) {
        if (newScore.intValue() < 0) {
            LOGGER.error("Invalid score");
            return false;
        }
        try {
            int assemblyObjectTypeId = jdbcTemplate.queryForObject(SQL_SELECT_OBJECT_TYPE_ID_BY_OBJECT_ID, Integer.class, id);
            if (assemblyObjectTypeId == 1) { /* Assembly */
                jdbcTemplate.update(SQL_UPDATE_ATTRIBUTES_VALUE, newScore, 8, id); /* Score */
                return true;
            } else {
                LOGGER.error("Identifier belongs not to a assembly");
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
