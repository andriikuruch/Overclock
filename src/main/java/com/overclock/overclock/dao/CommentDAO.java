package com.overclock.overclock.dao;

import com.overclock.overclock.model.Comment;

import java.math.BigInteger;
import java.util.List;

public interface CommentDAO {
    boolean save(Comment comment, BigInteger assemblyId, BigInteger authorId);
    List<Comment> getLimitedListOfCommentsByAssemblyId(BigInteger assemblyId, BigInteger limit);
    List<Comment> getAllCommentsByAssemblyId(BigInteger assemblyId);
    boolean delete(BigInteger id);
}
