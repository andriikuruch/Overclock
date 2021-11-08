package com.overclock.overclock.controller;


import com.overclock.overclock.model.Comment;
import com.overclock.overclock.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/assembly/{assembly_id}/comment")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@PathVariable("assembly_id") BigInteger assemblyId) {
        return null;
    }

    @PostMapping
    public String addComment(Comment comment, Authentication authentication) {
        return null;
    }

    @DeleteMapping("/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") BigInteger id) {
        return null;
    }


}
