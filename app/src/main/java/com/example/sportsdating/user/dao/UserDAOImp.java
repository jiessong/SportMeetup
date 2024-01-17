package com.example.sportsdating.user.dao;

import com.example.sportsdating.user.model.User;
import com.example.sportsdating.utils.FileUtil;
import com.example.sportsdating.utils.JSONUtil;
import com.example.sportsdating.utils.StringUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDAOImp implements UserDAO{
    private AtomicInteger maxID;
    private final String filePath = "UserData.json";
    private Map<Integer, User> userMap;
    private static UserDAOImp userDAOImp;
    private UserDAOImp(){
        findAllUsers();
    }

    public static UserDAOImp getInstance(){
        if(userDAOImp == null){
            userDAOImp = new UserDAOImp();
        }
        return userDAOImp;
    }

    /**
     *
     * @param name user name
     * @param password password
     * @return a new User object
     * create a new user
     * Firstly, check if this user has existed
     * Secondly, if not, create an object and add it to the userMap
     */
    @Override
    public User addUser(String name, String password) {
        User user = findUserByName(name);
        if(user!=null){
            return null;
        }
        int uid = maxID.incrementAndGet();
        User newUser = new User(uid, name, password);
        userMap.put(uid, newUser);
        saveAllUserData();
        return newUser;
    }

    /**
     * write all user data into file
     */
    public void saveAllUserData(){
        JSONUtil jsonUtil = new JSONUtil();
        String allUserContent = jsonUtil.objListToJSONString(new ArrayList(userMap.values()));
        try {
            FileUtil.writeFile(filePath, allUserContent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * read from file the user data and put them in the map
     */
    @Override
    public void findAllUsers() {
        List<User> allUsers = new LinkedList<>();
        userMap = new HashMap<>();
        maxID = new AtomicInteger(0);
        String allUserContent = FileUtil.readFile(filePath);
        if(StringUtil.isBlank(allUserContent)){
            return;
        }

        JSONUtil jsonUtil = new JSONUtil();
        allUsers = jsonUtil.jsonStringToObjList(allUserContent, User.class);
        for(User user:allUsers){
            userMap.put(user.getId(), user);
            if(user.getId() > maxID.get()){
                maxID.set(user.getId());
            }
        }
    }

    /**
     *
     * @param name username
     * @return a User
     * return the user with this user name
     */
    @Override
    public User findUserByName(String name) {
        if(StringUtil.isBlank(name)){
            return null;
        }
        for(User user: userMap.values()){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @param id user id
     * @return a User object
     * find a user of the specified id
     */
    @Override
    public User findUserById(int id) {
        return this.userMap.get(id);
    }

    /**
     * @return a list of users
     * get all users data
     */
    @Override
    public List<User> getAllUsers(){
        return new LinkedList<User>(userMap.values());
    }

    /**
     * @param  id user id
     * @return a list of users
     * get the list of followed user
     */
    @Override
    public List<User> getAllFriends(int id) {
        return idToUsers(this.userMap.get(id).getFriends());
    }

    /**
     * @param  id user id
     * @return a list of users
     * get the list of followed user
     */
    @Override
    public List<User> getBlockedList(int id) {
        return idToUsers(this.userMap.get(id).getBlocks());
    }

    /**
     *
     * @param uid user id
     * @param fid friend(follow) id
     * follow a new user, add this user to the friend list
     */
    @Override
    public void addFriend(int uid, int fid) {
        if(uid>=0 && uid<=maxID.get() && fid>=0 && fid<=maxID.get() && uid!=fid){
            User user = this.userMap.get(uid);
            if(!user.getBlocks().contains(fid)){
                user.getFriends().add(fid);
                saveAllUserData();
            }
        }
    }

    /**
     *
     * @param uid user id
     * @param fid block user id
     * remove a new user from friend list
     */
    @Override
    public void removeFriend(int uid, int fid) {
        if(uid>=0 && uid<=maxID.get() && fid>=0 && fid<=maxID.get() && uid!=fid){
            User user = this.userMap.get(uid);
            if(user.getFriends().contains(fid)){
                user.getFriends().remove(new Integer(fid));
                saveAllUserData();
            }
        }
    }

    /**
     *
     * @param uid user id
     * @param fid block user id
     * block a new user, add this user to the block list
     */
    @Override
    public void addBlock(int uid, int fid) {
        if(uid>=0 && uid<=maxID.get() && fid>=0 && fid<=maxID.get() && uid!=fid){
            User user = this.userMap.get(uid); //引用？？ 需要在map更新吗？？
            if(!user.getBlocks().contains(fid)){
                user.getBlocks().add(fid);
                saveAllUserData();
            }
        }
    }

    /**
     *
     * @param uid user id
     * @param fid block user id
     * remove a new user from block list
     */
    @Override
    public void removeBlock(int uid, int fid) {
        if(uid>=0 && uid<=maxID.get() && fid>=0 && fid<=maxID.get() && uid!=fid){
            User user = this.userMap.get(uid);
            if(user.getBlocks().contains(fid)){
                user.getBlocks().remove(new Integer(fid));
                saveAllUserData();
            }
        }
    }

    /**
     * @param ids a list of user id
     * @return a list of users
     * convert the list of friends/blocks user id to users
     */
    private List<User> idToUsers(Set<Integer> ids){
        List<User> list = new ArrayList<>();
        for(int uid:ids){
            User user = this.userMap.get(uid);
            if(user!=null){
                list.add(user);
            }
        }
        return list;
    }

    /**
     * @return the number of all users
     */
    @Override
    public int getCount(){
        return maxID.get();
    }
}
