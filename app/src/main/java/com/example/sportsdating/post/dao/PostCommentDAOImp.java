package com.example.sportsdating.post.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.post.model.PostComment;
import com.example.sportsdating.utils.FileUtil;
import com.example.sportsdating.utils.JSONUtil;
import com.example.sportsdating.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PostCommentDAOImp implements PostCommentDAO{
    private AtomicInteger commentId;
    private Map<Integer, PostComment> commentMap; //map the comment ids to comments
    private Map<Integer, List<PostComment>> commentsByPostMap; //map each post to all its comments
    private final String COMMENT_FILE_NAME = "comments_data";
    private static PostCommentDAOImp postCommentDAOImp;
    private PostCommentDAOImp(){
        loadAllComments();
    }
    public static PostCommentDAOImp getInstance(){
        if(postCommentDAOImp == null){
            postCommentDAOImp = new PostCommentDAOImp();
        }
        return postCommentDAOImp;
    }

    /**
     * @param uId  user id of this comment
     * @param postId  post id
     * @param content comment content for post id
     * Firstly, create a PostComment Object and add it to the commentMap
     * Secondly, add this comment to the post in postByCommentMap
     */
    @Override
    public void createComment(int uId, int postId, String content) {
        PostComment postComment = new PostComment(commentId.incrementAndGet(), uId, postId, content);
        commentMap.put(postComment.getCommentId(), postComment);
        updateCommentByPost(postComment);
    }

    /**
     * read comments from the file and update commentMap and commentsByPostMap
     */
    @Override
    public void loadAllComments() {
        commentId = new AtomicInteger(0);
        String comments = FileUtil.readFile(COMMENT_FILE_NAME);
        if(StringUtil.isBlank(comments)){
            return ;
        }
        commentMap = new HashMap<>();
        commentsByPostMap = new HashMap<>();

        JSONUtil jsonUtil = new JSONUtil();
        List<PostComment> postCommentList = jsonUtil.jsonStringToObjList(comments, PostComment.class);
        for(PostComment postComment : postCommentList){
            commentMap.put(postComment.getCommentId(), postComment);
            commentId.incrementAndGet();
            updateCommentByPost(postComment);
        }
    }

    /**
     * write all comments to the file
     */
    @Override
    public void saveAllComments(){
       JSONUtil jsonUtil = new JSONUtil();
        String allUserContent = jsonUtil.objListToJSONString(new ArrayList(commentMap.values()));
        try {
            FileUtil.writeFile(COMMENT_FILE_NAME, allUserContent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param comment
     * add a comment to the comment list of a corresponding post
     */
    private void updateCommentByPost(PostComment comment){
        List<PostComment> list = commentsByPostMap.get(comment.getPostId());
        if(list==null){
            list = new ArrayList<>();
            commentsByPostMap.put(comment.getPostId(), list);
        }
        list.add(comment);
    }
}
