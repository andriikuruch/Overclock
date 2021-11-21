package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.CPU;
import com.overclock.overclock.model.enums.CPUFamily;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CPURowMapper implements RowMapper<CPU> {
    @Override
    public CPU mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CPU.Builder(
                BigInteger.valueOf(rs.getInt("ID")), rs.getString("NAME"))
                .setManufacturer(CPUManufacturer.fromInt(rs.getInt("MANUF_ID")))
                .setSocket(CPUSocket.fromInt(rs.getInt("SOCKET_ID")))
                .setGeneration(CPUGeneration.fromInt(rs.getInt("GEN_ID")))
                .setFamily(CPUFamily.fromInt(rs.getInt("FAMILY_ID")))
                .setVoltage(rs.getBigDecimal("VOLTAGE"))
                .setFrequency(rs.getBigDecimal("FREQUENCY"))
                .setThreadsNumber(BigInteger.valueOf(rs.getInt("NUM_OF_THR")) )
                .setCoresNumber(BigInteger.valueOf(rs.getInt("NUM_OF_CORES")))
                .build();
    }
}