package com.overclock.overclock.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

public class Comment {

    @Positive
    private BigInteger id;

    @NotNull
    @NotEmpty
    @Size(max = 4096)
    private String commentMessage;

    @NotNull
    private User author;

    @NotNull
    private Date dateOfComment;

    public static class Builder {

        private Comment comment;

        public Builder() {
            this.comment = new Comment();
        }

        public Builder setId(BigInteger id) {
            comment.id = id;
            return this;
        }

        public Builder setCommentMessage(String commentMessage) {
            comment.commentMessage = commentMessage;
            return this;
        }

        public Builder setAuthor(User author) {
            comment.author = author;
            return this;
        }

        public Builder setDateOfComment(Date dateOfComment) {
            comment.dateOfComment = dateOfComment;
            return this;
        }

        public Comment build() {
            return comment;
        }
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(Date dateOfComment) {
        this.dateOfComment = dateOfComment;
    }
}