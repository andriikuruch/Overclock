package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.RAM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RAMRowMapper implements RowMapper<RAM> {
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
