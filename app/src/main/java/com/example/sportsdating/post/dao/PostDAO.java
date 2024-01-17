package com.example.sportsdating.post.dao;

import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.user.model.User;

import java.util.List;

public interface PostDAO {
    public Post createPost(String content, User user);
    List<Post> getAllPosts();
    public void loadAllPost();
    public void deletePost(Post post);
    public void saveAllPost();
    public Post findPost(Post post);
    void cleanAllPosts();
}
