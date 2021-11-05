package com.overclock.overclock.dao;

import com.overclock.overclock.model.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentDAO {
    boolean save(Comment comment, BigInteger assemblyId);
    List<Comment> getLimitListOfCommentsToAssembly(BigInteger assemblyId, int limit);
    List<Comment> getAllCommentsToAssembly(BigInteger assemblyId);
    boolean delete(BigInteger id);
}
