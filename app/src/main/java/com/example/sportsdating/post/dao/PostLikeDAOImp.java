package com.example.sportsdating.post.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.sportsdating.post.model.PostComment;
import com.example.sportsdating.post.model.PostLike;
import com.example.sportsdating.utils.FileUtil;
import com.example.sportsdating.utils.JSONUtil;
import com.example.sportsdating.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PostLikeDAOImp implements PostLikeDAO{
    private final String POSTLIKE_FILE_NAME="all_postlike_data";
    private AtomicInteger postLikeMaxId;
    private Map<Integer, PostLike> likeMap; //map the like id and PostLike
    private Map<Integer,Integer> likeCountMap; //map the post id and like counts
    private static PostLikeDAOImp postLikeDAOImp;
    private PostLikeDAOImp(){
        loadAllLikePost();
    }
    public static PostLikeDAOImp getInstance(){
        if(postLikeDAOImp == null){
            postLikeDAOImp = new PostLikeDAOImp();
        }
        return postLikeDAOImp;
    }

    /**
     * Count the number of likes a post of given id has received
     * @param postId
     * @return The number of likes this post received
     */
    @Override
    public int getLikeCountByPost(int postId) {
        if(likeCountMap.containsKey(postId)){
            return likeCountMap.get(postId);
        }else{
            return 0;
        }
    }

    /**
     * @param uid: user id
     * @param postid: Post id
     * create a new like
     */
    @Override
    public void likePost(int uid, int postid) {
        if(!userLikedPost(uid,postid)){
            int postLikeId = postLikeMaxId.incrementAndGet();
            PostLike postLike = new PostLike(postLikeId, uid, postid);
            this.likeMap.put(postLike.getLikeId(), postLike);
            increasePostLikeCount(postLike.getPostId());
            saveAllLikePost();
        }
    }

    /**
     *
     * @param uid user id
     * @param postid  post id
     * @return boolean
     * check if the user has ever liked the post
     */
    @Override
    public boolean userLikedPost(int uid, int postid){
        for(PostLike postLike : likeMap.values()){
            if(postLike.getuId() == uid && postLike.getPostId() == postid)
                return true;
        }
        return false;
    }

    /**
     * read like data from the file, update the likeMap and likeCountMap
     */
    @Override
    public void loadAllLikePost() {
        postLikeMaxId = new AtomicInteger(0);
        likeMap = new HashMap<>();
        likeCountMap = new HashMap<>();
        String likeString = FileUtil.readFile(POSTLIKE_FILE_NAME);
        if(StringUtil.isBlank(likeString)){
            return ;
        }

        JSONUtil jsonUtil = new JSONUtil();
        List<PostLike> likeList = jsonUtil.jsonStringToObjList(likeString, PostLike.class);
        for(PostLike postLike : likeList){
            likeMap.put(postLike.getLikeId(), postLike);
            postLikeMaxId.incrementAndGet();
            increasePostLikeCount(postLike.getPostId());
        }
    }

    /**
     * Write the like data into the file.
     */
    @Override
    public void saveAllLikePost(){
        JSONUtil jsonUtil = new JSONUtil();
        String allLikes = jsonUtil.objListToJSONString(new ArrayList(likeMap.values()));
        try {
            FileUtil.writeFile(POSTLIKE_FILE_NAME, allLikes);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Increase the count of likes for a post
     * @param postId
     */
    private void increasePostLikeCount(int postId){
        if (likeCountMap.containsKey(postId)) {
            int count = likeCountMap.get(postId) + 1;
            likeCountMap.put(postId, count);
        }else{
            likeCountMap.put(postId, 1);
        }
        saveAllLikePost();
    }


}
