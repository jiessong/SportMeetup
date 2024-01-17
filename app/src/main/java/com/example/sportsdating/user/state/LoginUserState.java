package com.example.sportsdating.user.state;

import com.example.sportsdating.user.UserErrorCode;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.dao.UserDAO;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;
import com.example.sportsdating.utils.LocalStorageUtil;

import java.util.List;
import java.util.Set;

/**
 * A login user, manages all information and activities of current user
 */
public class LoginUserState implements UserState{
    User user;
    public LoginUserState(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public int getId(){
        return this.user.getId();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    @Override
    public String getUserImg() {
        return user.getUserImg();
    }

    @Override
    public UserErrorCode login(String username, String password) {
        return UserErrorCode.USER_ALREADY_LOGGEDIN;
    }

    /**
     * remove the login info from the local file when this user log out
     */
    @Override
    public void logout() {
        UserMgr.getInstance().changeUserState(new AnonymousUserState());
        LocalStorageUtil.getInstance().remove("username");
        LocalStorageUtil.getInstance().remove("password");
    }

    @Override
    public List<User> friends() {
        return UserDAOImp.getInstance().getAllFriends(user.getId());
    }

    @Override
    public List<User> blocks() {
        return UserDAOImp.getInstance().getAllFriends(user.getId());
    }

}
