package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.Overclock;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OverclockRowMapper implements RowMapper<Overclock> {
    @Override
    public Overclock mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Overclock.Builder()
                .setId(BigInteger.valueOf(rs.getInt("ID")))
                .setCPUFrequency(rs.getBigDecimal("CPU_FREQUENCY"))
                .setCPUVoltage(rs.getBigDecimal("CPU_VOLTAGE"))
                .setGPUCoreFrequency(BigInteger.valueOf(rs.getInt("GPU_CORE_FREQUENCY")))
                .setGPUMemoryFrequency(BigInteger.valueOf(rs.getInt("GPU_MEMORY_FREQUENCY")))
                .setGPUVoltage(rs.getBigDecimal("GPU_VOLTAGE"))
                .setRAMFrequency(BigInteger.valueOf(rs.getInt("RAM_FREQUENCY")))
                .setRAMTimings(rs.getString("RAM_TIMINGS"))
                .setRAMVoltage(rs.getBigDecimal("RAM_VOLTAGE"))
                .build();
    }
}
