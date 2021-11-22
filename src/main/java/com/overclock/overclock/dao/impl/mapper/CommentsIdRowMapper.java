package com.overclock.overclock.dao.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentsIdRowMapper implements RowMapper<BigInteger> {
    @Override
    public BigInteger mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BigInteger.valueOf(rs.getInt("COMMENT_ID"));
    }
}
