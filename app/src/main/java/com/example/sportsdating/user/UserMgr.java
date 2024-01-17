package com.example.sportsdating.user;

import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;
import com.example.sportsdating.user.state.AnonymousUserState;
import com.example.sportsdating.user.state.LoginUserState;
import com.example.sportsdating.user.state.UserState;
import com.example.sportsdating.utils.LocalStorageUtil;

/**
 * manage current user
 */
public class UserMgr {
    //Singleton, manage users
    private static UserMgr usermgr;
    private UserState userState;

    private UserMgr(){
        this.userState = new AnonymousUserState();
        initiUserStatus();
    }
    public static UserMgr getInstance(){
        if(usermgr == null){
            usermgr = new UserMgr();
        }
        return usermgr;
    }

    public void changeUserState(UserState userState){
        this.userState = userState;
    }

    //check if there is any user logged in before
    public boolean isUserLoggedIn(){
        return this.userState instanceof LoginUserState;
    }

    //initialize user Login state by checking history from the local storage
    public void initiUserStatus(){
        String name = LocalStorageUtil.getInstance().get("username"); //check if there is any logged user information stored in local storage
        String password = LocalStorageUtil.getInstance().get("password");
        if(name!=null && password!=null){
            User user = UserDAOImp.getInstance().findUserByName(name);
            if(user!=null && user.getPassword().equals(password)){
                this.userState = new LoginUserState(user);
            }
        }
    }

    public UserState getCurrentUserState(){
        return this.userState;
    }
}
