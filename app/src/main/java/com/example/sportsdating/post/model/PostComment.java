package com.example.sportsdating.post.model;

public class PostComment {
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    private int commentId;
    private int uId;
    private int postId;
    private String comment;
    private long commentTime;

    public PostComment(){}

    public PostComment(int commentId, int uId, int postId, String comment) {
        this.commentId = commentId;
        this.uId = uId;
        this.postId = postId;
        this.comment = comment;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }
}
