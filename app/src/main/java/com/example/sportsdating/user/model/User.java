package com.example.sportsdating.user.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer id;
    private String name;
    private long registerTime;
    private String userImg = "";
    private String password;
    Set<Integer> friends;
    Set<Integer> blocks;

    public User(){
        this.friends = new HashSet<>();
        this.blocks = new HashSet<>();
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.registerTime = System.currentTimeMillis();
        this.password = password;
        this.friends = new HashSet<>();
        this.blocks = new HashSet<>();
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Integer> friends) {
        this.friends = friends;
    }

    public Set<Integer> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Integer> blocks) {
        this.blocks = blocks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
