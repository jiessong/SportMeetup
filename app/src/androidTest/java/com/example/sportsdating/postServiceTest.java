package com.example.sportsdating;

import static org.junit.Assert.assertNotNull;

import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.post.model.ViewPost;
import com.example.sportsdating.post.service.PostService;
import com.example.sportsdating.user.model.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class postServiceTest {
    @Test()
    public void testGetPostByPage(){
        PostService postService = PostService.getInstance();
        int pageNumber = 1;
        List<ViewPost> pageposts = postService.getPostByPage(pageNumber);

        while(pageposts.size() > 0){
            assertNotNull(pageposts);
            System.out.println("page size:"+pageposts.size());
            for(ViewPost viewPost:pageposts){
                Post post = viewPost.getPost();
                int likeCount = viewPost.getLikeCount();
                User author = viewPost.getUser();
                boolean userLikedPost = viewPost.UserLikedPost();
                System.out.println("post_user_name:"+author.getName());
                System.out.println("post_content:"+post.getContent());
            }
            pageposts = postService.getPostByPage(++pageNumber);
        }
    }
}
