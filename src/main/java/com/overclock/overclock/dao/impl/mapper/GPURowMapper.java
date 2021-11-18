package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.GPU;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GPURowMapper implements RowMapper<GPU> {
    @Override
    public GPU mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GPU.Builder(
                BigInteger.valueOf(rs.getInt("ID")), rs.getString("NAME"))
                .setChipManufacturer(GPUChipManufacturer.fromInt(rs.getInt("CHIP_MANUF_ID")))
                .setChip(GPUChip.fromInt(rs.getInt("CHIP_ID")))
                .setMemoryCapacity(BigInteger.valueOf(rs.getInt("MEM_CAPACITY")))
                .setCoreFrequency(BigInteger.valueOf(rs.getInt("CORE_FREQUENCY")))
                .setMemoryFrequency(BigInteger.valueOf(rs.getInt("MEMORY_FREQUENCY")))
                .setVoltage(rs.getBigDecimal("VOLTAGE"))
                .build();
    }
}