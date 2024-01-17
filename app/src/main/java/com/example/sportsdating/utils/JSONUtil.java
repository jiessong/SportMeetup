package com.example.sportsdating.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Convert between Json Object/Object list and Json Strings.
 * Reference Source: https://mkyong.com/java/fastjson-convert-java-objects-to-from-json/
 */
public class JSONUtil<T> {
    public String objListToJSONString(List<T> obj){
        String jsonString = JSON.toJSONString(obj, true);
        return jsonString;
    }

    //Object to String
    public List<T> jsonStringToObjList(String content, Class<T> clazz){
        List<T> list = JSON.parseArray(content, clazz);
        return list;
    }
}
