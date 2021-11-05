package com.overclock.overclock.service;

import com.overclock.overclock.model.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {
    List<Comment> getLimitListOfComments(BigInteger assemblyId, int limit);
    boolean save(Comment comment, BigInteger assemblyId);
    boolean delete(BigInteger id);
    List<Comment> getCommentsByAssembly(BigInteger id);
}

