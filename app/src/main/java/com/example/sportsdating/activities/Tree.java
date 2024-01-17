package com.example.sportsdating.activities;

import com.example.sportsdating.datastructure.RBTree;
import com.example.sportsdating.activities.model.SportActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tree<T extends Comparable<T>> extends RBTree {

    public Set<SportActivity> findAll(T value) {
        Node root = super.root;
        Set<SportActivity> set = new HashSet<>();
        findAll(root, set, value);
        return set;
    }

    public void findAll(Node root, Set<SportActivity> set, T value) {
        if (root.getValue() == null) {
            return;
        }
        if (root.getValue().compareTo(value) == 0) {
            set.add((SportActivity) root.getValue());
            findAll(root.left, set, value);
            findAll(root.right, set, value);
        } else if (root.getValue().compareTo(value) > 0) {
            findAll(root.left, set, value);
        } else {
            findAll(root.right, set, value);
        }
    }

}
