package com.overclock.overclock.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class Comment {

    @Positive
    private BigInteger id;

    @NotNull
    @NotEmpty
    @Size(max = 4096)
    private String commentMessage;

    @NotNull
    @NotEmpty
    private String author;

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

        public Builder setAuthor(String  author) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(Date dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getCommentMessage(), comment.getCommentMessage()) &&
                Objects.equals(getAuthor(), comment.getAuthor()) &&
                Objects.equals(getDateOfComment(), comment.getDateOfComment());
    }
}