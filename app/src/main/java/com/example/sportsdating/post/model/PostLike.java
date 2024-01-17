package com.example.sportsdating.post.model;

public class PostLike {
    private int likeId;
    private int uId;
    private int postId;

    public PostLike(){}
    public PostLike(int likeId, int uId, int postId) {
        this.likeId = likeId;
        this.uId = uId;
        this.postId = postId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
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
}
