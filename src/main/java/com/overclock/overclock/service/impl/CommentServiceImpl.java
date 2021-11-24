package com.overclock.overclock.service.impl;

import com.overclock.overclock.dao.CommentDAO;
import com.overclock.overclock.model.Comment;
import com.overclock.overclock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Scope("singleton")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public boolean save(Comment comment, BigInteger assemblyId, BigInteger authorId) {
        return commentDAO.save(comment, assemblyId, authorId);
    }

    @Override
    public boolean delete(BigInteger id) {
        return commentDAO.delete(id);
    }

    @Override
    public boolean deleteAllCommentsByAssemblyId(BigInteger assemblyId) {
        return commentDAO.deleteAllCommentsByAssemblyId(assemblyId);
    }

    @Override
    public List<Comment> getLimitedListOfCommentsByAssemblyId(BigInteger assemblyId, BigInteger limit) {
        return commentDAO.getLimitedListOfCommentsByAssemblyId(assemblyId, limit);
    }

    @Override
    public List<Comment> getAllCommentsByAssemblyId(BigInteger assemblyId) {
        return commentDAO.getAllCommentsByAssemblyId(assemblyId);
    }
}
