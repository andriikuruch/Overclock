package com.overclock.overclock.dao;

import com.overclock.overclock.model.Overclock;

import java.math.BigInteger;

public interface OverclockDAO {
    static final String SQL_SELECT_OVERCLOCK_ID_BY_ASSEMBLY_ID =
            "SELECT OBJECT_ID FROM OBJREFERENCE " +
                    "WHERE ATTR_ID = 2 AND REFERENCE = ?"; /* Overclock id */

    static final String SQL_SELECT_OVERCLOCK_BY_ID =
            "SELECT OVERCLOCKS.OBJECT_ID ID, CPU_FREQUENCY.VALUE CPU_FREQUENCY,CPU_VOLTAGE.VALUE CPU_VOLTAGE, " +
                    "GPU_CORE_FREQUENCY.VALUE GPU_CORE_FREQUENCY,GPU_MEMORY_FREQUENCY.VALUE GPU_MEMORY_FREQUENCY, GPU_VOLTAGE.VALUE GPU_VOLTAGE, " +
                    "RAM_VOLTAGE.VALUE RAM_VOLTAGE, RAM_TIMINGS.VALUE RAM_TIMINGS, RAM_FREQUENCY.VALUE RAM_FREQUENCY " +
                    "   FROM OBJECTS OVERCLOCKS, ATTRIBUTES CPU_FREQUENCY, ATTRIBUTES CPU_VOLTAGE, " +
                    "   ATTRIBUTES GPU_CORE_FREQUENCY, ATTRIBUTES GPU_MEMORY_FREQUENCY, ATTRIBUTES GPU_VOLTAGE, " +
                    "   ATTRIBUTES RAM_VOLTAGE, ATTRIBUTES RAM_TIMINGS, ATTRIBUTES RAM_FREQUENCY " +
                    "       WHERE OVERCLOCKS.OBJECT_TYPE_ID = 6 " + /* Overclock */
                    "       AND OVERCLOCKS.OBJECT_ID = ? " +
                    "       AND CPU_FREQUENCY.ATTR_ID = 30 " + /* CPU Frequency */
                    "       AND CPU_VOLTAGE.ATTR_ID = 31 " + /* CPU Voltage */
                    "       AND GPU_CORE_FREQUENCY.ATTR_ID = 32 " + /* GPU Core Frequency */
                    "       AND GPU_MEMORY_FREQUENCY.ATTR_ID = 33 " + /* GPU Memory Frequency */
                    "       AND GPU_VOLTAGE.ATTR_ID = 34 " + /* GPU Voltage */
                    "       AND RAM_VOLTAGE.ATTR_ID = 35 " + /* RAM Voltage */
                    "       AND RAM_TIMINGS.ATTR_ID = 36 " + /* RAM Timings */
                    "       AND RAM_FREQUENCY.ATTR_ID = 37 " + /* RAM Frequency */
                    "       AND CPU_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND CPU_VOLTAGE.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND GPU_CORE_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND GPU_MEMORY_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND GPU_VOLTAGE.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND RAM_VOLTAGE.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND RAM_TIMINGS.OBJECT_ID = OVERCLOCKS.OBJECT_ID " +
                    "       AND RAM_FREQUENCY.OBJECT_ID = OVERCLOCKS.OBJECT_ID";

    boolean save(Overclock overclock, BigInteger assemblyId);
    boolean update(BigInteger id, Overclock newOverclock);
    boolean delete(BigInteger id);
    boolean deleteByAssemblyId(BigInteger assemblyId);
    Overclock getOverclockById(BigInteger id);
}
