package com.example.sportsdating.datagenerator;

import java.util.Random;

/**
 * Action Type
 */
public enum ActionType {
    NEWUSER,    //Create new user
    NEWPOST;    // create new post


    private static int index = 0;
    /**
     * @return generate an action type sequentially
     * source https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
     */
    public static ActionType getType(){
        if(index >= ActionType.values().length){
            index = 0;
        }
        return ActionType.values()[index++];
    }
}
