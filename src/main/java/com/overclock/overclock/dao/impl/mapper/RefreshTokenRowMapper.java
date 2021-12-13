package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.RefreshToken;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RefreshTokenRowMapper implements RowMapper<RefreshToken> {
    @Override
    public RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RefreshToken(
                rs.getBigDecimal("TOKEN_ID").toBigInteger(),
                rs.getString("TOKEN_VALUE")
        );
    }
}
