package com.overclock.overclock.dao;

import com.overclock.overclock.model.RAM;

import java.math.BigInteger;
import java.util.List;

public interface RamDAO {
    RAM getRamById(BigInteger id);
    RAM getRamByAssemblyId(BigInteger assemblyId);
    List<RAM> getAllRams();
    boolean save(RAM ram);
    boolean delete(BigInteger id);
    boolean update(BigInteger id, RAM newRam);
    static final String SQL_SELECT_RAM_BY_ID = "SELECT RAM.OBJECT_ID ID, RAM.NAME NAME, CAPACITY.VALUE CAPACITY, " +
            " FREQ.VALUE FREQUENCY, TIMINGS.VALUE TIMINGS, VOLTAGE.VALUE VOLTAGE " +
            " FROM OBJECTS RAM, ATTRIBUTES CAPACITY, ATTRIBUTES FREQ, ATTRIBUTES TIMINGS, ATTRIBUTES VOLTAGE " +
            "        WHERE RAM.OBJECT_TYPE_ID = 5 " + /* RAM */
            "        AND RAM.OBJECT_ID = CAPACITY.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = FREQ.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = TIMINGS.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = VOLTAGE.OBJECT_ID " +
            "        AND CAPACITY.ATTR_ID = 26 " + /* Capacity */
            "        AND FREQ.ATTR_ID = 27 " + /* Frequency */
            "        AND TIMINGS.ATTR_ID = 28 " + /* Timings */
            "        AND VOLTAGE.ATTR_ID = 29 " + /* Voltage */
            "        AND RAM.OBJECT_ID = ?";

    static final String SQL_SELECT_RAM_BY_ASSEMBLY_ID = "SELECT RAM.OBJECT_ID ID, RAM.NAME NAME, CAPACITY.VALUE CAPACITY, " +
            " FREQ.VALUE FREQUENCY, TIMINGS.VALUE TIMINGS, VOLTAGE.VALUE VOLTAGE " +
            " FROM OBJECTS RAM, OBJECTS ASSEMBLIES, ATTRIBUTES CAPACITY, ATTRIBUTES FREQ, ATTRIBUTES TIMINGS, ATTRIBUTES VOLTAGE, " +
            " OBJREFERENCE RAM_REF " +
            "        WHERE RAM.OBJECT_TYPE_ID = 5 " + /* RAM */
            "        AND ASSEMBLIES.OBJECT_TYPE_ID = 1 " + /* Assembly */
            "        AND RAM.OBJECT_ID = CAPACITY.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = FREQ.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = TIMINGS.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = VOLTAGE.OBJECT_ID " +
            "        AND CAPACITY.ATTR_ID = 26 " + /* Capacity */
            "        AND FREQ.ATTR_ID = 27 " + /* Frequency */
            "        AND TIMINGS.ATTR_ID = 28 " + /* Timings */
            "        AND VOLTAGE.ATTR_ID = 29 " + /* Voltage */
            "        AND RAM_REF.OBJECT_ID = RAM.OBJECT_ID " +
            "        AND RAM_REF.REFERENCE = ASSEMBLIES.OBJECT_ID " +
            "        AND ASSEMBLIES.OBJECT_ID = ?"; /* Assembly ID */

    static final String SQL_SELECT_ALL_RAM = "SELECT RAM.OBJECT_ID ID, RAM.NAME NAME, CAPACITY.VALUE CAPACITY, " +
            " FREQ.VALUE FREQUENCY, TIMINGS.VALUE TIMINGS, VOLTAGE.VALUE VOLTAGE " +
            " FROM OBJECTS RAM, ATTRIBUTES CAPACITY, ATTRIBUTES FREQ, ATTRIBUTES TIMINGS, ATTRIBUTES VOLTAGE " +
            "        WHERE RAM.OBJECT_TYPE_ID = 5 " + /* RAM */
            "        AND RAM.OBJECT_ID = CAPACITY.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = FREQ.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = TIMINGS.OBJECT_ID " +
            "        AND RAM.OBJECT_ID = VOLTAGE.OBJECT_ID " +
            "        AND CAPACITY.ATTR_ID = 26 " + /* Capacity */
            "        AND FREQ.ATTR_ID = 27 " + /* Frequency */
            "        AND TIMINGS.ATTR_ID = 28 " + /* Timings */
            "        AND VOLTAGE.ATTR_ID = 29"; /* Voltage */

}
