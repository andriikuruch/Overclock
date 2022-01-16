package com.overclock.overclock.dao;

import com.overclock.overclock.model.Assembly;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface AssemblyDAO {
    String QUERY_GET_BY_ID = "WITH ASSEMBLY AS (SELECT ASSEMBLY.OBJECT_ID ASSEMBLY_ID, " +
                             "        ASSEMBLY.NAME ASSEMBLY_NAME, SCORE.VALUE SCORE, " +
                             "        AUTHOR_REF.OBJECT_ID AUTHOR_ID " +
                             "    FROM OBJECTS ASSEMBLY, ATTRIBUTES SCORE, " +
                             "        OBJREFERENCE AUTHOR_REF " +
                             "    WHERE ASSEMBLY.OBJECT_ID = ? " +
                             "        AND AUTHOR_REF.ATTR_ID = 1 /*AUTHOR*/ " +
                             "        AND AUTHOR_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                             "        AND SCORE.ATTR_ID = 8 /*SCORE*/ " +
                             "        AND SCORE.OBJECT_ID = ASSEMBLY.OBJECT_ID), " +
                             "OVERCLOCK AS (SELECT OVERCLOCK.OBJECT_ID AS OVERCLOCK_ID, " +
                             "        OVERCLOCK.PARENT_ID AS PARENT_ID " +
                             "    FROM OBJECTS OVERCLOCK " +
                             "    WHERE OVERCLOCK.OBJECT_TYPE_ID = 6 /*OVERCLOCK*/) " +
                             "SELECT ASSEMBLY.ASSEMBLY_ID, " +
                             "    ASSEMBLY.ASSEMBLY_NAME, ASSEMBLY.SCORE, " +
                             "    ASSEMBLY.AUTHOR_ID, OVERCLOCK.OVERCLOCK_ID " +
                             "FROM ASSEMBLY LEFT JOIN OVERCLOCK " +
                             "    ON (ASSEMBLY.ASSEMBLY_ID = OVERCLOCK.PARENT_ID)";

    String QUERY_GET_ALL = "WITH  ALL_OVERCLOCKS AS (SELECT OVERCLOCK.OBJECT_ID AS OVERCLOCK_ID, " +
                            "OVERCLOCK.PARENT_ID AS PARENT_ID " +
                        "FROM OBJECTS OVERCLOCK " +
                        "WHERE OVERCLOCK.OBJECT_TYPE_ID = 6), " +
                    "ALL_ASSEMBLIES AS (SELECT ASSEMBLY.OBJECT_ID ASSEMBLY_ID, ASSEMBLY.NAME ASSEMBLY_NAME, " +
                            "MOTHERBOARD.OBJECT_ID MOTHERBOARD_ID, MOTHERBOARD.NAME MOTHERBOARD_NAME, " +
                            "CPU.OBJECT_ID CPU_ID, CPU.NAME CPU_NAME, " +
                            "GPU.OBJECT_ID GPU_ID, GPU.NAME GPU_NAME, " +
                            "RAM.OBJECT_ID RAM_ID, RAM.NAME RAM_NAME, " +
                            "AUTHOR_REF.OBJECT_ID AUTHOR_ID, SCORE.VALUE SCORE " +
                        "FROM OBJECTS ASSEMBLY, OBJECTS RAM, OBJECTS MOTHERBOARD, " +
                            "OBJECTS CPU, OBJECTS GPU, OBJREFERENCE AUTHOR_REF, " +
                            "OBJREFERENCE MOTHERBOARD_REF, OBJREFERENCE CPU_REF, " +
                            "OBJREFERENCE GPU_REF, OBJREFERENCE RAM_REF, ATTRIBUTES SCORE " +
                        "WHERE ASSEMBLY.OBJECT_TYPE_ID = 1 " +
                            "AND MOTHERBOARD.OBJECT_TYPE_ID = 2 " +
                            "AND CPU.OBJECT_TYPE_ID = 3 " +
                            "AND GPU.OBJECT_TYPE_ID = 4 " +
                            "AND RAM.OBJECT_TYPE_ID = 5 " +
                            "AND AUTHOR_REF.ATTR_ID = 1 " +
                            "AND AUTHOR_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                            "AND SCORE.ATTR_ID = 8 /*SCORE*/ " +
                            "AND SCORE.OBJECT_ID = ASSEMBLY.OBJECT_ID " +
                            "AND MOTHERBOARD_REF.OBJECT_ID = MOTHERBOARD.OBJECT_ID " +
                            "AND MOTHERBOARD_REF.REFERENCE = ASSEMBLY.OBJECT_ID  " +
                            "AND CPU_REF.OBJECT_ID = CPU.OBJECT_ID " +
                            "AND CPU_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                            "AND GPU_REF.OBJECT_ID = GPU.OBJECT_ID " +
                            "AND GPU_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                            "AND RAM_REF.OBJECT_ID = RAM.OBJECT_ID " +
                            "AND RAM_REF.REFERENCE = ASSEMBLY.OBJECT_ID) " +
                    "SELECT ALL_ASSEMBLIES.ASSEMBLY_ID, ALL_ASSEMBLIES.ASSEMBLY_NAME, " +
                            "ALL_ASSEMBLIES.MOTHERBOARD_ID, ALL_ASSEMBLIES.MOTHERBOARD_NAME, " +
                            "ALL_ASSEMBLIES.CPU_ID, ALL_ASSEMBLIES.CPU_NAME, " +
                            "ALL_ASSEMBLIES.GPU_ID, ALL_ASSEMBLIES.GPU_NAME, " +
                            "ALL_ASSEMBLIES.RAM_ID, ALL_ASSEMBLIES.RAM_NAME, " +
                            "ALL_ASSEMBLIES.AUTHOR_ID, ALL_OVERCLOCKS.OVERCLOCK_ID, ALL_ASSEMBLIES.SCORE " +
                    "FROM ALL_ASSEMBLIES LEFT JOIN ALL_OVERCLOCKS " +
                        "ON (ALL_ASSEMBLIES.ASSEMBLY_ID = ALL_OVERCLOCKS.PARENT_ID) " +
                    "ORDER BY ALL_ASSEMBLIES.ASSEMBLY_ID";

    String QUERY_GET_ALL_SORTED_BY_SCORE = "WITH  ALL_OVERCLOCKS AS (SELECT OVERCLOCK.OBJECT_ID AS OVERCLOCK_ID, " +
            "OVERCLOCK.PARENT_ID AS PARENT_ID " +
            "FROM OBJECTS OVERCLOCK " +
            "WHERE OVERCLOCK.OBJECT_TYPE_ID = 6), " +
            "ALL_ASSEMBLIES AS (SELECT ASSEMBLY.OBJECT_ID ASSEMBLY_ID, ASSEMBLY.NAME ASSEMBLY_NAME, " +
            "MOTHERBOARD.OBJECT_ID MOTHERBOARD_ID, MOTHERBOARD.NAME MOTHERBOARD_NAME, " +
            "CPU.OBJECT_ID CPU_ID, CPU.NAME CPU_NAME, " +
            "GPU.OBJECT_ID GPU_ID, GPU.NAME GPU_NAME, " +
            "RAM.OBJECT_ID RAM_ID, RAM.NAME RAM_NAME, " +
            "AUTHOR_REF.OBJECT_ID AUTHOR_ID, SCORE.VALUE SCORE " +
            "FROM OBJECTS ASSEMBLY, OBJECTS RAM, OBJECTS MOTHERBOARD, " +
            "OBJECTS CPU, OBJECTS GPU, OBJREFERENCE AUTHOR_REF, " +
            "OBJREFERENCE MOTHERBOARD_REF, OBJREFERENCE CPU_REF, " +
            "OBJREFERENCE GPU_REF, OBJREFERENCE RAM_REF, ATTRIBUTES SCORE " +
            "WHERE ASSEMBLY.OBJECT_TYPE_ID = 1 " +
            "AND MOTHERBOARD.OBJECT_TYPE_ID = 2 " +
            "AND CPU.OBJECT_TYPE_ID = 3 " +
            "AND GPU.OBJECT_TYPE_ID = 4 " +
            "AND RAM.OBJECT_TYPE_ID = 5 " +
            "AND AUTHOR_REF.ATTR_ID = 1 " +
            "AND AUTHOR_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
            "AND SCORE.ATTR_ID = 8 /*SCORE*/ " +
            "AND SCORE.OBJECT_ID = ASSEMBLY.OBJECT_ID " +
            "AND MOTHERBOARD_REF.OBJECT_ID = MOTHERBOARD.OBJECT_ID " +
            "AND MOTHERBOARD_REF.REFERENCE = ASSEMBLY.OBJECT_ID  " +
            "AND CPU_REF.OBJECT_ID = CPU.OBJECT_ID " +
            "AND CPU_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
            "AND GPU_REF.OBJECT_ID = GPU.OBJECT_ID " +
            "AND GPU_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
            "AND RAM_REF.OBJECT_ID = RAM.OBJECT_ID " +
            "AND RAM_REF.REFERENCE = ASSEMBLY.OBJECT_ID) " +
            "SELECT ALL_ASSEMBLIES.ASSEMBLY_ID, ALL_ASSEMBLIES.ASSEMBLY_NAME, " +
            "ALL_ASSEMBLIES.MOTHERBOARD_ID, ALL_ASSEMBLIES.MOTHERBOARD_NAME, " +
            "ALL_ASSEMBLIES.CPU_ID, ALL_ASSEMBLIES.CPU_NAME, " +
            "ALL_ASSEMBLIES.GPU_ID, ALL_ASSEMBLIES.GPU_NAME, " +
            "ALL_ASSEMBLIES.RAM_ID, ALL_ASSEMBLIES.RAM_NAME, " +
            "ALL_ASSEMBLIES.AUTHOR_ID, ALL_OVERCLOCKS.OVERCLOCK_ID, ALL_ASSEMBLIES.SCORE " +
            "FROM ALL_ASSEMBLIES LEFT JOIN ALL_OVERCLOCKS " +
            "ON (ALL_ASSEMBLIES.ASSEMBLY_ID = ALL_OVERCLOCKS.PARENT_ID) " +
            "ORDER BY SCORE DESC";

    String QUERY_GET_ALL_BY_AUTHOR_ID = "WITH  ALL_OVERCLOCKS AS (SELECT OVERCLOCK.OBJECT_ID AS OVERCLOCK_ID, " +
                            "OVERCLOCK.PARENT_ID AS PARENT_ID " +
                        "FROM OBJECTS OVERCLOCK " +
                        "WHERE OVERCLOCK.OBJECT_TYPE_ID = 6), " +
                    "ALL_ASSEMBLIES AS (SELECT ASSEMBLY.OBJECT_ID ASSEMBLY_ID, ASSEMBLY.NAME ASSEMBLY_NAME, " +
                            "MOTHERBOARD.OBJECT_ID MOTHERBOARD_ID, MOTHERBOARD.NAME MOTHERBOARD_NAME, " +
                            "CPU.OBJECT_ID CPU_ID, CPU.NAME CPU_NAME, " +
                            "GPU.OBJECT_ID GPU_ID, GPU.NAME GPU_NAME, " +
                            "RAM.OBJECT_ID RAM_ID, RAM.NAME RAM_NAME, " +
                            "AUTHOR.OBJECT_ID AUTHOR_ID, SCORE.VALUE SCORE " +
                        "FROM OBJECTS ASSEMBLY, OBJECTS RAM, OBJECTS MOTHERBOARD, " +
                            "OBJECTS CPU, OBJECTS GPU, OBJECTS AUTHOR, OBJREFERENCE AUTHOR_REF," +
                            "OBJREFERENCE MOTHERBOARD_REF, OBJREFERENCE CPU_REF, " +
                            "OBJREFERENCE GPU_REF, OBJREFERENCE RAM_REF, ATTRIBUTES SCORE " +
                        "WHERE ASSEMBLY.OBJECT_TYPE_ID = 1 " +
                            "AND MOTHERBOARD.OBJECT_TYPE_ID = 2 " +
                            "AND CPU.OBJECT_TYPE_ID = 3 " +
                            "AND GPU.OBJECT_TYPE_ID = 4 " +
                            "AND RAM.OBJECT_TYPE_ID = 5 " +
                            "AND AUTHOR_REF.ATTR_ID = 1 " +
                            "AND AUTHOR_REF.OBJECT_ID = ? " +
                            "AND SCORE.ATTR_ID = 8 /*SCORE*/ " +
                            "AND SCORE.OBJECT_ID = ASSEMBLY.OBJECT_ID " +
                            "AND MOTHERBOARD_REF.OBJECT_ID = MOTHERBOARD.OBJECT_ID " +
                            "AND MOTHERBOARD_REF.REFERENCE = ASSEMBLY.OBJECT_ID  " +
                            "AND CPU_REF.OBJECT_ID = CPU.OBJECT_ID " +
                            "AND CPU_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                            "AND GPU_REF.OBJECT_ID = GPU.OBJECT_ID " +
                            "AND GPU_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                            "AND RAM_REF.OBJECT_ID = RAM.OBJECT_ID " +
                            "AND RAM_REF.REFERENCE = ASSEMBLY.OBJECT_ID " +
                            "AND AUTHOR_REF.OBJECT_ID = AUTHOR.OBJECT_ID " +
                            "AND AUTHOR_REF.REFERENCE = ASSEMBLY.OBJECT_ID) " +
                    "SELECT DISTINCT ALL_ASSEMBLIES.ASSEMBLY_ID, ALL_ASSEMBLIES.ASSEMBLY_NAME, " +
                            "ALL_ASSEMBLIES.MOTHERBOARD_ID, ALL_ASSEMBLIES.MOTHERBOARD_NAME, " +
                            "ALL_ASSEMBLIES.CPU_ID, ALL_ASSEMBLIES.CPU_NAME, " +
                            "ALL_ASSEMBLIES.GPU_ID, ALL_ASSEMBLIES.GPU_NAME, " +
                            "ALL_ASSEMBLIES.RAM_ID, ALL_ASSEMBLIES.RAM_NAME, " +
                            "ALL_ASSEMBLIES.AUTHOR_ID, ALL_OVERCLOCKS.OVERCLOCK_ID, ALL_ASSEMBLIES.SCORE " +
                    "FROM ALL_ASSEMBLIES LEFT JOIN ALL_OVERCLOCKS " +
                        "ON (ALL_ASSEMBLIES.ASSEMBLY_ID = ALL_OVERCLOCKS.PARENT_ID) " +
                    "ORDER BY ALL_ASSEMBLIES.ASSEMBLY_ID";

    String SQL_SELECT_USER_ID = "SELECT OBJECTS.OBJECT_ID USER_ID\n" +
            "FROM  OBJECTS\n" +
            "WHERE OBJECTS.NAME = ? ";

    String INSERT_ASSEMBLY = "INSERT ALL " +
            "INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) " +
            "   VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 1, ?, NULL) /*CREATE ASSEMBLY*/ " +
            "INTO ATTRIBUTES (ATTR_ID, OBJECT_ID, VALUE) " +
            "   VALUES (8, OBJECT_ID_SEQ.CURRVAL, ?) /*ADD SCORE*/ " +
            "INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) " +
            "   VALUES (1, OBJECT_ID_SEQ.CURRVAL, ?) /*ADD AUTHOR*/ " +
            "INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) " +
            "   VALUES (3, OBJECT_ID_SEQ.CURRVAL, ?) /*ADD MOTHERBOARD*/ " +
            "INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) " +
            "   VALUES (4, OBJECT_ID_SEQ.CURRVAL, ?) /*ADD CPU*/ " +
            "INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) " +
            "   VALUES (5, OBJECT_ID_SEQ.CURRVAL, ?) /*ADD GPU*/ " +
            "INTO OBJREFERENCE (ATTR_ID, REFERENCE, OBJECT_ID) " +
            "   VALUES (6, OBJECT_ID_SEQ.CURRVAL, ?) /*ADD RAM*/ " +
            "SELECT * FROM DUAL";

    String DELETE_SCORE = "DELETE FROM ATTRIBUTES SCORE WHERE SCORE.ATTR_ID = 8 AND SCORE.OBJECT_ID = ?";

    String DELETE_REFERENCES = "DELETE FROM OBJREFERENCE WHERE ATTR_ID IN (1, 3, 4, 5, 6) AND REFERENCE = ?";

    String DELETE_ASSEMBLY = "DELETE FROM OBJECTS WHERE OBJECT_TYPE_ID = 1 AND OBJECT_ID = ?";

    Assembly getAssemblyById(BigInteger id);
    List<Assembly> getAllAssemblies();
    List<Assembly> getSortedByScoreAssemblies();
    List<Assembly> getAssembliesByAuthorId(BigInteger author);
    List<Assembly> getAssembliesByAuthorName(String author);
    boolean save(Assembly assembly);
    boolean delete(BigInteger id);
    boolean updateScore(BigInteger id, BigDecimal newScore);
}
