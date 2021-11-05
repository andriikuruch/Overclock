package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.model.Comment;
import com.overclock.overclock.service.CommentService;
import com.overclock.overclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class CommentServiceImpl implements CommentService {
    private CommentDAO commentDAO;
    private UserService userService;

    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<Comment> getLimitListOfComments(BigInteger assemblyId, int limit) {
        return null;
    }

    @Override
    public boolean save(Comment comment, BigInteger assemblyId) {
        return false;
    }

    @Override
    public boolean delete(BigInteger id) {
        return false;
    }

    @Override
    public List<Comment> getCommentsByAssembly(BigInteger id) {
        return null;
    }
}
