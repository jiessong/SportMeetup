package com.example.sportsdating.post.model;

import com.example.sportsdating.user.model.User;

/**
 * To make it convenient to get all data related to posts in MomentsActivity.java,
 * ViewPost has combined all needed information, such as post, the number of likes,
 * whether current user has liked current post or not.
 */
public class ViewPost {
    private Post post;
    private User user;
    private int likeCount;
    private boolean userLikedPost;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean UserLikedPost() {
        return userLikedPost;
    }

    public void setUserLikedPost(boolean userLikedPost) {
        this.userLikedPost = userLikedPost;
    }
}
