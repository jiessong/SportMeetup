package com.example.sportsdating.post.utils;

import com.example.sportsdating.datastructure.RBTree;
import com.example.sportsdating.post.model.Post;

import java.util.LinkedList;
import java.util.List;

public class PostTree<T extends Comparable<T>> extends RBTree<T> {

    public List<T> getAllPost(){
        List<T> posts = new LinkedList<>();
        if(this.root!=null){
            postOrderTranverse(this.root, posts);
        }
        return posts;
    }

    public void postOrderTranverse(Node node, List<T> posts){
        if(node.getValue() != null){
            if(node.right.getValue() !=null ){
                postOrderTranverse(node.right, posts);
            }
            posts.add((T) node.getValue());
            if(node.left.getValue() !=null ){
                postOrderTranverse(node.left, posts);
            }
        }
    }
}
