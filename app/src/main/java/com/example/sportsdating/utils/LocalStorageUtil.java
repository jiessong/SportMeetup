package com.example.sportsdating.utils;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * Read and write data from 'information.json', which saves data for current user information,
 * the line number of datastream, etc.
 */
public class LocalStorageUtil {
    private String filename="information.json";
    private JSONObject jsonObj = new JSONObject();
    private static LocalStorageUtil localStorageUtil;
    //private constructor
    private LocalStorageUtil(){
        load();
    }
    public static LocalStorageUtil getInstance(){
        if(localStorageUtil==null){
            localStorageUtil = new LocalStorageUtil();
        }
        return localStorageUtil;
    }

    //fetch String value from the json Object
    public String get(String key){
        if(jsonObj.containsKey(key)){
            return jsonObj.getString(key);
        }else{
            return null;
        }
    }

    //fetch the integer value from the json Object
    public Integer getInteger(String key){
        if(jsonObj.containsKey(key)){
            String value = jsonObj.getString(key);
            if(value.matches("[0-9]+")){
                return Integer.parseInt(value);
            }
        }
        return 0;
    }

    //set String value for the given key
    public void set(String key, String value){
        jsonObj.put(key, value);
        saveData();
    }

    //set integer value for the given key
    public void set(String key, Integer value){
        jsonObj.put(key, String.valueOf(value));
        saveData();
    }

    //Convert object to JsonObject and save it to the file
    public void saveData(){
        String content = jsonObj.toJSONString();
        FileUtil.writeFile(filename, content);
    }

    //Read file, and convert to JsonObject
    public void load(){
        String content = FileUtil.readFile(filename);
        if(!StringUtil.isBlank(content)){
            jsonObj = JSONObject.parseObject(content);
        }
    }

    //Remove a key-value from the json object
    public void remove(String key){
        this.jsonObj.remove(key);
        saveData();
    }
}
