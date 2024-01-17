package com.example.sportsdating.user.dao;

import com.example.sportsdating.user.model.User;

import java.util.List;

public interface UserDAO {
    public User addUser(String name, String password);
    public void saveAllUserData();
    public void findAllUsers();
    public User findUserByName(String name);
    public User findUserById(int id);
    List<User> getAllUsers();
    List<User> getAllFriends(int id);
    void addFriend(int uid, int fid);

    void removeFriend(int uid, int fid);

    void addBlock(int uid, int fid);

    List<User> getBlockedList(int id);

    void removeBlock(int uid, int fid);

    int getCount();
}
