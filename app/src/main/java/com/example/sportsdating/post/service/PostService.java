package com.example.sportsdating.post.service;

import com.example.sportsdating.post.dao.PostDAOImpl;
import com.example.sportsdating.post.dao.PostLikeDAOImp;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.post.model.ViewPost;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PostService is a class used for generating the post data for display in each page,
 * by default, a list of maximum 10 posts are returned in one page
 */
public class PostService {
    private final int PAGE_SIZE = 30;
    private static PostService postService;
    private PostService(){}
    public static PostService getInstance(){
        if(postService==null){
            postService = new PostService();
        }
        return postService;
    }

    /**
     * get a list of posts of the specific index, pagesize is set to 10 by default
     * @param page Integer, the page number, starting from 1
     * @return A list of ViewPost ready for display
     *
     */
    public List<ViewPost> getPostByPage(int page){
        int postPage = page - 1;
        Set<Integer> blockList = new HashSet<>();
        //get all created posts
        List<Post> originalPosts = PostDAOImpl.getInstance().getAllPosts(); //get all posts
        //excluding the posts blocked by current user
        List<Post> nonBlockedPosts = new ArrayList<>();
        int currentUid = UserMgr.getInstance().getCurrentUserState().getId();
        User currentUser = UserDAOImp.getInstance().findUserById(currentUid);
        //get the list of blocked user ids
        if(currentUser!=null){
            blockList = currentUser.getBlocks();
        }
        //exclude posts from blocked users
        for(Post post : originalPosts){
            if(!blockList.contains(post.getUserId())){
                nonBlockedPosts.add(post);
            }
        }

        int startIndex = postPage * PAGE_SIZE; //calculate the index in the list according to page number and page size
        int endIndex = startIndex + PAGE_SIZE;
        if(startIndex < 0 || startIndex > nonBlockedPosts.size()-1){
            return new ArrayList<>();
        }
        if(endIndex > nonBlockedPosts.size()){
            endIndex = nonBlockedPosts.size();
        }
        //fetch the posts of the given page
        List<Post> pageList = nonBlockedPosts.subList(startIndex, endIndex); // the list of posts at this page
        List<ViewPost> viewPostList = new ArrayList<>(pageList.size());

        //form the list of ViewPost for display
        for(Post post: pageList){
            //create a new ViewPost for each Post
            ViewPost viewPost = new ViewPost();
            //set post and the author in viewpost
            viewPost.setPost(post);
            User postAuthor = UserDAOImp.getInstance().findUserById(post.getUserId());
            if(postAuthor==null){
                continue;
            }
            viewPost.setUser(postAuthor);
            //get the number of likes of current post
            int likeCount = PostLikeDAOImp.getInstance().getLikeCountByPost(post.getId());
            viewPost.setLikeCount(likeCount);
            //check if current user has liked this post
            boolean userLikedPost = false;
            if(currentUid != 0){
                userLikedPost = PostLikeDAOImp.getInstance().userLikedPost(currentUid, post.getId());
            }
            viewPost.setUserLikedPost(userLikedPost);
            viewPostList.add(viewPost);
        }
        return viewPostList;
    }

    public void deletePost(Post post){
        PostDAOImpl.getInstance().deletePost(post);
    }
}
