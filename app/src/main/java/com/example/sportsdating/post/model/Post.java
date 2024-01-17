package com.example.sportsdating.post.model;

public class Post implements Comparable<Post> {
    private int id;
    private String content;
    private long createTime;
    private int userId;
    private String postImg;

    public Post(){}

    public Post(int postId, int userId,String content  ) {
        this.id = postId;
        this.content = content;
        this.userId = userId;
        this.createTime = System.currentTimeMillis();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    @Override
    public int compareTo(Post post) {
         return this.getId() - post.getId();
    }
}
