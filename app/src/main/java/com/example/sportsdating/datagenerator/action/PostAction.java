package com.example.sportsdating.datagenerator.action;

import android.net.ConnectivityDiagnosticsManager;

import com.example.sportsdating.datagenerator.DataGenerator;
import com.example.sportsdating.datagenerator.loader.ActionIterator;
import com.example.sportsdating.datagenerator.loader.ActionLoader;
import com.example.sportsdating.post.dao.PostDAOImpl;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import java.util.List;
import java.util.Random;


/**
 * Creating a new Post
 */
public class PostAction implements Action{
    private String content="";
    public PostAction(String line) {
        this.content = line;
    }

    @Override
    public void performAction() {
            List<User> users = UserDAOImp.getInstance().getAllUsers();
            int random = new Random().nextInt(users.size());
            PostDAOImpl.getInstance().createPost(content, users.get(random));
    }
}
