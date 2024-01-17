package com.example.sportsdating;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.sportsdating.post.dao.PostLikeDAOImp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PostLikeTest {

    private PostLikeDAOImp postLikeDAOImp;
    @Before
    public void init(){
        postLikeDAOImp = PostLikeDAOImp.getInstance();
    }
    @Test
    public void testLikePost(){
        postLikeDAOImp.likePost(1, 4);
        assertTrue(postLikeDAOImp.userLikedPost(1, 4));
    }

    @Test
    public void testUesrLikePost(){
        postLikeDAOImp.likePost(1, 1);
        assertTrue(postLikeDAOImp.userLikedPost(1, 1));
    }

    @Test
    public void testLikeCount(){
        postLikeDAOImp.likePost(2, 44);
        postLikeDAOImp.likePost(3, 44);
        postLikeDAOImp.likePost(1, 44);
        assertEquals(3, postLikeDAOImp.getLikeCountByPost(44));
    }
}
