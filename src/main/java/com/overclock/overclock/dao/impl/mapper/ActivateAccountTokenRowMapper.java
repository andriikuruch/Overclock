package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.ActivateAccountToken;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActivateAccountTokenRowMapper implements RowMapper<ActivateAccountToken> {
    @Override
    public ActivateAccountToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ActivateAccountToken(
                rs.getBigDecimal("TOKEN_ID").toBigInteger(),
                rs.getString("TOKEN_VALUE")
        );
    }
}
