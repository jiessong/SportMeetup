package com.example.sportsdating;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDAOTest {
    private UserDAOImp userDAOImp;
    @Before
    public void init(){
        userDAOImp = UserDAOImp.getInstance();
    }

    @Test
    public void testAddFindUser(){
        User user = userDAOImp.addUser("testUser","123");
        assertNotNull(userDAOImp.findUserByName("testUser"));
    }

    @Test
    public void testFindAllUsrs(){
        List<User> userList = userDAOImp.getAllUsers();
        assertNotNull(userList);
    }

    @Test
    public void testFindUserById(){
        User user = userDAOImp.findUserById(1);
        assertEquals("admin", user.getName());
    }

    @Test
    public void testAddGetFriend(){
        userDAOImp.addFriend(1, 2);
        List<User> friendList = userDAOImp.getAllFriends(1);
        assertNotNull(friendList);
    }

    @Test
    public void testAddGetRemoveBlock(){
        userDAOImp.addBlock(1, 3);
        List<User> blockList = userDAOImp.getBlockedList(1);
        assertNotNull(blockList);
        userDAOImp.removeBlock(1, 3);
        blockList = userDAOImp.getBlockedList(1);
        assertEquals(0, blockList.size());
    }

    @Test
    public void testGetCount(){
        //the number of all users
        int counts = userDAOImp.getCount();
        assertTrue(counts > 0);
    }
}
