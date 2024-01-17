package com.example.sportsdating.post.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.post.model.PostComment;
import com.example.sportsdating.post.model.PostLike;
import com.example.sportsdating.post.utils.PostTree;
import com.example.sportsdating.user.model.User;
import com.example.sportsdating.utils.FileUtil;
import com.example.sportsdating.utils.JSONUtil;
import com.example.sportsdating.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PostDAOImpl implements PostDAO{
    private final String POST_FILE="all_post_data";
    private AtomicInteger postMaxId;
    private PostTree<Post> postsTree;
    private static PostDAOImpl instance;
    private PostDAOImpl(){
        this.loadAllPost();
    }
    public static PostDAOImpl getInstance(){
        if(instance==null){
            instance = new PostDAOImpl();
        }
        return instance;
    }

    /**
     *
     * @param content  post content
     * @param user  post author
     * @return a new Post
     * create a new post, and insert this post into the tree
     */
    @Override
    public Post createPost(String content, User user){
        Post post  = new Post(postMaxId.incrementAndGet(),user.getId(),content);
        postsTree.insert(post);
        saveAllPost();
        return post;
    }

    /**
     * @return A list of Posts
     * tranverse the tree to get all posts
     */
    @Override
    public List<Post> getAllPosts(){
        return postsTree.getAllPost();
    }

    /**
     * get all posts
     */
    @Override
    public void loadAllPost() {
        postsTree = new PostTree<>();
        postMaxId = new AtomicInteger(0);
        String posts = FileUtil.readFile(POST_FILE);
        if(StringUtil.isBlank(posts)){
            return ;
        }

        JSONUtil jsonUtil = new JSONUtil();
        List<Post> postList = jsonUtil.jsonStringToObjList(posts, Post.class);
        for(Post post : postList){
            System.out.println(postMaxId);
            postsTree.insert(post);
            postMaxId.incrementAndGet();
        }
    }

    /**
     * @param post
     * delete a post
     */
    @Override
    public void deletePost(Post post) {
        postsTree.delete(post);
    }

    /**
     * write all post data into file
     */
    @Override
    public void saveAllPost() {
        JSONUtil jsonUtil = new JSONUtil();
        String allPosts = jsonUtil.objListToJSONString(postsTree.getAllPost());
        try {
            FileUtil.writeFile(POST_FILE, allPosts);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Post findPost(Post post) {
        try{
            return postsTree.find(post).getValue();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * delete all posts
     */
    @Override
    public void cleanAllPosts(){
        postsTree = new PostTree<>();
        saveAllPost();
    }
}
