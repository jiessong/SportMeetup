package com.example.sportsdating.datagenerator.action;

import com.example.sportsdating.datagenerator.ActionType;
import com.example.sportsdating.datagenerator.DataGenerator;
import com.example.sportsdating.datagenerator.loader.ActionIterator;
import com.example.sportsdating.datagenerator.loader.ActionLoader;

/**
 * Facotry will create an instance of Action based on the actionType
 */
public class ActionFactory {
    private static ActionFactory actionFactory;
    private ActionLoader userLoader;
    private ActionLoader postLoader;
    private ActionIterator postIterator;
    private ActionIterator userIterator;
    private ActionFactory(){
        postIterator = new ActionLoader("action_posts.txt").getIterator();
        userIterator = new ActionLoader("action_users.csv").getIterator();
    }
    public static ActionFactory getInstance(){
        if(actionFactory==null){
            actionFactory = new ActionFactory();
        }
        return actionFactory;
    }
    public Action createAction(ActionType type){
        ActionType actionType = type;
        if(type==null){
            actionType = ActionType.getType();
        }
        if(actionType.equals(ActionType.NEWUSER)){
            if(this.userIterator.hasNext()){
                String line = (String) userIterator.next();
                return new UserAction(line);
            }else{
                System.out.println("no more data in user datastream");
            }
        }else{
            if(this.postIterator.hasNext()){
                String line = (String) postIterator.next();
                return new PostAction(line);
            }else{
                System.out.println("no more data in user datastream");
                DataGenerator.getInstance().stopStream();
            }
        }
        return null;
    }
}
