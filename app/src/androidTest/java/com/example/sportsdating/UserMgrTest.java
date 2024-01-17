package com.example.sportsdating;

import static org.junit.Assert.*;

import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.model.User;

import org.junit.Test;

import java.util.List;
import java.util.Set;

public class UserMgrTest {
    @Test
    public void testLoginLogout(){
        boolean loginStatus = UserMgr.getInstance().isUserLoggedIn();
        if(loginStatus){
            UserMgr.getInstance().getCurrentUserState().logout();
            loginStatus = UserMgr.getInstance().isUserLoggedIn();
        }
        assertFalse(loginStatus);
        UserMgr.getInstance().getCurrentUserState().login("admin","123456");
        loginStatus = UserMgr.getInstance().isUserLoggedIn();
        assertTrue(loginStatus);
    }

    @Test
    public void testImg(){
        String img = UserMgr.getInstance().getCurrentUserState().getUserImg();
        System.out.println("image_url="+img);
    }

    @Test
    public void testUserName(){
        String name = UserMgr.getInstance().getCurrentUserState().getUsername();
        assertEquals("admin",name);
    }
    @Test
    public void testFriends(){
        List<User> friends = UserMgr.getInstance().getCurrentUserState().friends();
        //assertNull(friends);
    }

    @Test
    public void testBlock(){
        List<User> blocks = UserMgr.getInstance().getCurrentUserState().blocks();
        //assertNull(blocks);
    }
}
