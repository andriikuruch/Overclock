package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Comment.Builder()
                .setId(BigInteger.valueOf(rs.getInt("ID")))
                .setCommentMessage(rs.getString("MESSAGE"))
                .setAuthor(rs.getString("AUTHOR"))
                .setDateOfComment(rs.getDate("DATE_OF_COMMENT"))
                .build();
    }
}
