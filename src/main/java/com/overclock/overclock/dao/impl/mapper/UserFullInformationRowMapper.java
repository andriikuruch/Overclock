package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.User;
import com.overclock.overclock.model.enums.Role;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserFullInformationRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;

        try {
            date = dateFormat.parse(rs.getString("REGISTRATION_DATE"));
        } catch (ParseException e) {
            throw new EmptyResultDataAccessException("Incorrect date", 1);
        }

        return new User.Builder()
                .setId(BigInteger.valueOf(rs.getLong("USER_ID")))
                .setUserName(rs.getString("USERNAME"))
                .setEmail(rs.getString("EMAIL"))
                .setRegistrationDate(date)
                .setPassword(rs.getString("PASSWORD"))
                .setRole(Role.fromInt(rs.getInt("USER_ROLE")))
                .setIsActive(rs.getBoolean("IS_ACTIVE"))
                .build();
    }
}
