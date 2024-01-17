package com.example.sportsdating.datagenerator.action;

import com.example.sportsdating.datagenerator.loader.ActionIterator;
import com.example.sportsdating.datagenerator.loader.ActionLoader;
import com.example.sportsdating.user.dao.UserDAOImp;

/**
 * A new user registration
 */
public class UserAction implements Action{

    String username = "";
    String password = "";
    public UserAction(String user) {
        String[] userArr = user.split(",");
        if(userArr.length==2){
            this.username = userArr[0];
            this.password = userArr[1];
        }
    }


    @Override
    public void performAction() {
        UserDAOImp.getInstance().addUser(username, password);
    }
}
