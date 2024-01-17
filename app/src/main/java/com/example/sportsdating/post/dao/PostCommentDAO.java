package com.example.sportsdating.post.dao;

public interface PostCommentDAO {
    public void createComment(int uId, int postId, String content);
    public void loadAllComments();
    void saveAllComments();
}
