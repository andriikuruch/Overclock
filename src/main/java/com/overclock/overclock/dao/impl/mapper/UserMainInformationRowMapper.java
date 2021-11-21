package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.User;
import com.overclock.overclock.model.enums.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMainInformationRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User.Builder()
                .setId(BigInteger.valueOf(rs.getLong("USER_ID")))
                .setUserName(rs.getString("USERNAME"))
                .setEmail(rs.getString("EMAIL"))
                .setPassword(rs.getString("PASSWORD"))
                .setRole(Role.fromInt(rs.getInt("USER_ROLE")))
                .build();
    }
}
