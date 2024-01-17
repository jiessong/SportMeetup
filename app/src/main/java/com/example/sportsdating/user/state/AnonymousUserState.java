package com.example.sportsdating.user.state;

import com.example.sportsdating.user.UserErrorCode;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;
import com.example.sportsdating.utils.LocalStorageUtil;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represent the user without login
 * the id of an unknown user is set to -1, username to 'guest'
 * This class help to control the activities of an unknown user
 */
public class AnonymousUserState implements UserState{
    public AnonymousUserState() {}

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getUsername() {
        return "guest";
    }

    @Override
    public String getUserImg() {
        return "";
    }

    @Override
    public UserErrorCode login(String username, String password) {
        User user = UserDAOImp.getInstance().findUserByName(username);
        if(user!=null){
            if(password.equals(user.getPassword())){
                UserMgr.getInstance().changeUserState(new LoginUserState(user));
                LocalStorageUtil.getInstance().set("username",username); //remember the login info
                LocalStorageUtil.getInstance().set("password",password);
                return UserErrorCode.LOGIN_SUCCESS;
            }else{
                return UserErrorCode.WRONG_PASSWORD;
            }
        }else{
            return UserErrorCode.WRONG_USERNAME;
        }
    }

    @Override
    public void logout() {
    }

    @Override
    public List<User> friends() {
        return null;
    }

    @Override
    public List<User> blocks() {
        return null;
    }
}
