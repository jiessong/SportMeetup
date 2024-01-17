package com.example.sportsdating.utils;


public class StringUtil {
    //verify if the given content is null or empty
    public static boolean isBlank(String content){
        if(content == null || content.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
