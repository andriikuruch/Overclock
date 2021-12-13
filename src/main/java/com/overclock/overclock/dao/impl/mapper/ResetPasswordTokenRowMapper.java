package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.ResetPasswordToken;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ResetPasswordTokenRowMapper implements RowMapper<ResetPasswordToken> {
    @Override
    public ResetPasswordToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ResetPasswordToken(
                rs.getBigDecimal("TOKEN_ID").toBigInteger(),
                rs.getString("TOKEN_VALUE")
        );
    }
}
