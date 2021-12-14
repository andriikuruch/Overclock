package com.overclock.overclock.controller;

import com.overclock.overclock.model.Comment;
import com.overclock.overclock.security.UserDetailsImpl;
import com.overclock.overclock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assembly/{assembly_id}/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments(@PathVariable("assembly_id") BigInteger assemblyId) {
        return commentService.getAllCommentsByAssemblyId(assemblyId);
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable("assembly_id") BigInteger assemblyId) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentService.save(comment, assemblyId, user.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public boolean deleteComment(@PathVariable("comment_id") BigInteger id) {
        return commentService.delete(id);
    }
}
