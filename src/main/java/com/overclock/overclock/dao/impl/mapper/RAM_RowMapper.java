package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.RAM;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RAM_RowMapper implements RowMapper<RAM> {
    @Override
    public RAM mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RAM.Builder(
                BigInteger.valueOf(rs.getInt("ID")), rs.getString("NAME"))
                .setCapacity(BigInteger.valueOf(rs.getInt("CAPACITY")))
                .setFrequency(BigInteger.valueOf(rs.getInt("FREQUENCY")))
                .setTimings(rs.getString("TIMINGS"))
                .setVoltage(rs.getBigDecimal("VOLTAGE"))
                .build();
    }
}
