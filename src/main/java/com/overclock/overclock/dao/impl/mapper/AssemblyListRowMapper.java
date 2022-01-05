package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AssemblyListRowMapper implements RowMapper<Assembly> {
    @Override
    public Assembly mapRow(ResultSet rs, int rowNum) throws SQLException {
        BigInteger assemblyId = BigInteger.valueOf(rs.getLong("ASSEMBLY_ID"));

        long overclockIdLong = rs.getLong("OVERCLOCK_ID");
        BigInteger overclockId = overclockIdLong != 0 ? BigInteger.valueOf(overclockIdLong) : null;

        BigInteger authorId = BigInteger.valueOf(rs.getLong("AUTHOR_ID"));
        BigDecimal score = BigDecimal.valueOf(rs.getDouble("SCORE"));

        BigInteger motherboardId = BigInteger.valueOf(rs.getLong("MOTHERBOARD_ID"));
        Motherboard motherboard = new Motherboard.Builder(motherboardId,
                rs.getString("MOTHERBOARD_NAME")).build();

        BigInteger cpuId = BigInteger.valueOf(rs.getLong("CPU_ID"));
        CPU cpu = new CPU.Builder(cpuId, rs.getString("CPU_NAME"))
                .build();

        BigInteger gpuId = BigInteger.valueOf(rs.getLong("GPU_ID"));
        GPU gpu = new GPU.Builder(gpuId, rs.getString("GPU_NAME"))
                .build();

        BigInteger ramId = BigInteger.valueOf(rs.getLong("RAM_ID"));
        RAM ram = new RAM.Builder(ramId, rs.getString("RAM_NAME"))
                .build();

        return new Assembly.Builder(assemblyId, rs.getString("ASSEMBLY_NAME"))
                .setAuthor(authorId)
                .setOverclock(overclockId)
                .setMotherboard(motherboard)
                .setCpu(cpu)
                .setGpu(gpu)
                .setRam(ram)
                .setScore(score)
                .build();
    }
}
