package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.Motherboard;
import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MotherboardRowMapper  implements RowMapper<Motherboard> {
    @Override
    public Motherboard mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Motherboard.Builder(BigInteger.valueOf(rs.getInt("ID")), rs.getString("NAME"))
                .setChipsetManufacturer(ChipsetManufacturer.fromInt(rs.getInt("CHIPSET_MANUFACTURER")))
                .setChipset(Chipset.fromInt(rs.getInt("CHIPSET")))
                .setSocket(MotherboardSocket.fromInt(rs.getInt("SOCKET")))
                .build();
    }
}
