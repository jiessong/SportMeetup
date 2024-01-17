package com.example.sportsdating.post.dao;

public interface PostLikeDAO {
    public int getLikeCountByPost(int postId);
    public void likePost(int uid, int postid);
    public void loadAllLikePost();
    public boolean userLikedPost(int uid, int postid);
    void saveAllLikePost();
}
