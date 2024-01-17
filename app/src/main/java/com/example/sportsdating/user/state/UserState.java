package com.example.sportsdating.user.state;

import com.example.sportsdating.user.UserErrorCode;
import com.example.sportsdating.user.model.User;

import java.util.List;
import java.util.Set;

public interface UserState {
    User getUser();

    public int getId();
    public String getUsername();
    public String getUserImg();
    public UserErrorCode login(String username, String password);
    public void logout();
    public List<User> friends();
    public List<User> blocks();
}
