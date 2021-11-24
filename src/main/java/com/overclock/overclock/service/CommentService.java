package com.overclock.overclock.service;

import com.overclock.overclock.model.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentService {
    boolean save(Comment comment, BigInteger assemblyId, BigInteger authorId);
    boolean delete(BigInteger id);
    boolean deleteAllCommentsByAssemblyId(BigInteger assemblyId);
    List<Comment> getLimitedListOfCommentsByAssemblyId(BigInteger assemblyId, BigInteger limit);
    List<Comment> getAllCommentsByAssemblyId(BigInteger assemblyId);
}

