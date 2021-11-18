package com.overclock.overclock.dao.impl;

import com.overclock.overclock.CreateUtilities;
import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentDAOImplTest {
    @Autowired
    CommentDAO commentDAO;

    @Test
    public void getAllCommentsByAssemblyId() {
        List<Comment> comments = commentDAO.getAllCommentsByAssemblyId(BigInteger.valueOf(1));

        Assertions.assertNotNull(comments);
        for (Comment comment: comments) {
            Assertions.assertNotNull(comment.getId());
            Assertions.assertNotNull(comment.getCommentMessage());
            Assertions.assertNotNull(comment.getDateOfComment());
            Assertions.assertNotNull(comment.getAuthor());
        }
    }

    @Test
    public void getAllCommentsByAssemblyId_EmptyResult() {
        List<Comment> comments = commentDAO.getAllCommentsByAssemblyId(BigInteger.valueOf(4));

        Assertions.assertNotNull(comments);
        Assertions.assertEquals(new ArrayList<Comment>(), comments);
    }

    @Test
    public void getLimitedListOfCommentsByAssemblyId() {
        BigInteger limit = BigInteger.valueOf(2);
        List<Comment> comments = commentDAO.getLimitedListOfCommentsByAssemblyId(BigInteger.valueOf(1), limit);

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
    public void getLimitedListOfCommentsByAssemblyId_WithNegativeLimit() {
        BigInteger limit = BigInteger.valueOf(-3);
        List<Comment> comments = commentDAO.getLimitedListOfCommentsByAssemblyId(BigInteger.valueOf(1), limit);

        Assertions.assertNotNull(comments);
        Assertions.assertEquals(new ArrayList<Comment>(), comments);
    }
    @Test
    @Transactional
    public void saveValidComment() {
        Assertions.assertTrue(commentDAO.save(CreateUtilities.createComment(), BigInteger.valueOf(2), BigInteger.valueOf(12)));
    }

    @Test
    @Transactional
    public void saveComment_Null() {
       Assertions.assertFalse(commentDAO.save(null, BigInteger.valueOf(2), BigInteger.valueOf(12)));
    }

    @Test
    @Transactional
    public void saveInvalidComment() {
        Assertions.assertFalse(commentDAO.save(CreateUtilities.createBadComment(), BigInteger.valueOf(2), BigInteger.valueOf(12)));
    }

    @Test
    @Transactional
    public void deleteComment() {
        Assertions.assertTrue(commentDAO.delete(BigInteger.valueOf(16)));
    }

    @Test
    @Transactional
    public void deleteNotAComment() {
        Assertions.assertFalse(commentDAO.delete(BigInteger.valueOf(1)));
    }
}