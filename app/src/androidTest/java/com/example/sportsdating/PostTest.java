package com.example.sportsdating;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.sportsdating.post.dao.PostDAOImpl;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class PostTest {
    public static PostDAOImpl postDAOImp;
    @BeforeClass
    public static void init(){
        postDAOImp = PostDAOImpl.getInstance();
    }

    @Test()
    public void testLoadAllPost(){
        List<Post> posts = postDAOImp.getAllPosts();
        assertNotNull(posts);
        System.out.println("There are totally:"+posts.size()+" posts");
    }

    @Test()
    public void testgetAllPost(){
        assertNotNull(postDAOImp.getAllPosts());
    }

    @Test()
    public void testPostCreate(){
        User user = UserMgr.getInstance().getCurrentUserState().getUser();
        if(user!=null){
            Post post1 = postDAOImp.createPost("Let's run together", user);
            Post post2 = postDAOImp.createPost("It was a fun game", user);
            Post findPost1 = postDAOImp.findPost(post1);
            Post findPost2 = postDAOImp.findPost(post2);
            assertNotNull(findPost1);
            assertNotNull(findPost2);
        }
    }



    @Test()
    public void testPostFind(){
        User user = UserMgr.getInstance().getCurrentUserState().getUser();
        if(user!=null){
            Post post = postDAOImp.createPost("Let's play basketball", user);
            Post findPost1 = postDAOImp.findPost(post);
            assertNotNull(findPost1);
        }
    }

    @Test()
    public void testDeletePost(){
        User user = UserMgr.getInstance().getCurrentUserState().getUser();
        if(user!=null){
            Post post = postDAOImp.createPost("I love football", user);
            Post findPost1 = postDAOImp.findPost(post);
            assertNotNull(findPost1);
            postDAOImp.deletePost(post);
            findPost1 = postDAOImp.findPost(post);
            assertNull(findPost1);
        }
    }

    @Test()
    public void insertPostTest(){
        int currentUid = UserMgr.getInstance().getCurrentUserState().getId();
        User currentUser = UserDAOImp.getInstance().findUserById(currentUid);
        if(currentUser!=null){
            String post1 = "test the post creation";
            String  post2 = "test the post creation";
            PostDAOImpl.getInstance().createPost(post1, currentUser);
            PostDAOImpl.getInstance().createPost(post2, currentUser);
            List<Post> posts = PostDAOImpl.getInstance().getAllPosts();
            assertNotNull(posts);
        }
    }

//    public void deletePost(Post post);
//    void cleanAllPosts();

}
