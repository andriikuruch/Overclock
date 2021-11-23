package com.overclock.overclock.dao.impl.mapper;

import com.overclock.overclock.model.Comment;
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
public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;

        try {
            date = dateFormat.parse(rs.getString("DATE_OF_COMMENT"));
        } catch (ParseException e) {
            throw new EmptyResultDataAccessException("Incorrect date", 1);
        }

        return new Comment.Builder()
                .setId(BigInteger.valueOf(rs.getInt("ID")))
                .setCommentMessage(rs.getString("MESSAGE"))
                .setAuthor(rs.getString("AUTHOR"))
                .setDateOfComment(date)
                .build();
    }
}
