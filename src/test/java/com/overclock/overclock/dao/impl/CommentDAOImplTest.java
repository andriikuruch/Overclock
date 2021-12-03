package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.model.Comment;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql("sql/InsertionsForTesting.sql")
@Transactional
public class CommentDAOImplTest {
    @Autowired
    CommentDAO commentDAO;

    @Test
    public void getAllCommentsByAssemblyId() {
        List<Comment> comments = commentDAO.getAllCommentsByAssemblyId(BigInteger.valueOf(-5006));

        Assertions.assertNotNull(comments);
        Assert.assertFalse(comments.isEmpty());
        for (Comment comment: comments) {
            Assertions.assertNotNull(comment.getId());
            Assertions.assertNotNull(comment.getCommentMessage());
            Assertions.assertNotNull(comment.getDateOfComment());
            Assertions.assertNotNull(comment.getAuthor());
        }
    }

    @Test
    public void getAllCommentsByWrongAssemblyId() {
        List<Comment> comments = commentDAO.getAllCommentsByAssemblyId(BigInteger.valueOf(0));

        Assertions.assertNotNull(comments);
        Assertions.assertTrue(comments.isEmpty());
    }

    @Test
    public void getLimitedListOfCommentsByAssemblyId() {
        BigInteger limit = BigInteger.valueOf(2);
        List<Comment> comments = commentDAO.getLimitedListOfCommentsByAssemblyId(BigInteger.valueOf(-5006), limit);

        Assertions.assertNotNull(comments);
        Assertions.assertTrue(comments.size() <= limit.intValue());
        for (Comment comment: comments) {
            Assertions.assertNotNull(comment.getId());
            Assertions.assertNotNull(comment.getCommentMessage());
            Assertions.assertNotNull(comment.getDateOfComment());
            Assertions.assertNotNull(comment.getAuthor());
        }
    }

    @Test
    public void getLimitedListOfCommentsByAssemblyIdWithNegativeLimit() {
        BigInteger limit = BigInteger.valueOf(-3);
        List<Comment> comments = commentDAO.getLimitedListOfCommentsByAssemblyId(BigInteger.valueOf(-5006), limit);

        Assertions.assertNotNull(comments);
        Assertions.assertTrue(comments.isEmpty());
    }
    @Test
    public void saveValidComment() {
        Assertions.assertTrue(commentDAO.save(CreateUtilities.createComment(), BigInteger.valueOf(-5006), BigInteger.valueOf(-5007)));
    }

    @Test
    public void saveInvalidComment() {
        Assertions.assertFalse(commentDAO.save(CreateUtilities.createBadComment(), BigInteger.valueOf(-5006), BigInteger.valueOf(-5007)));
    }

    @Test
    public void saveNull() {
       Assertions.assertFalse(commentDAO.save(null, BigInteger.valueOf(-5006), BigInteger.valueOf(-5007)));
    }

    @Test
    public void saveCommentWithWrongAuthorId() {
        Assertions.assertFalse(commentDAO.save(CreateUtilities.createComment(), BigInteger.valueOf(-5006), BigInteger.valueOf(0)));
    }

    @Test
    public void saveCommentWithWrongAssemblyId() {
        Assertions.assertFalse(commentDAO.save(CreateUtilities.createComment(), BigInteger.valueOf(0), BigInteger.valueOf(-5007)));
    }

    @Test
    public void deleteComment() {
        Assertions.assertTrue(commentDAO.delete(BigInteger.valueOf(-5001)));
    }

    @Test
    public void deleteNotAComment() {
        Assertions.assertFalse(commentDAO.delete(BigInteger.valueOf(0)));
    }

    @Test
    public void deleteAllCommentsByAssemblyId() {
        Assertions.assertTrue(commentDAO.deleteAllCommentsByAssemblyId(BigInteger.valueOf(-5006)));
    }

    @Test
    public void deleteAllCommentsByAssemblyIdWithWrongAssemblyId() {
        Assertions.assertFalse(commentDAO.deleteAllCommentsByAssemblyId(BigInteger.valueOf(0)));
    }
}