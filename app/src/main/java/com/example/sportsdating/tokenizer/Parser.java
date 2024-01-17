package com.example.sportsdating.tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  Grammar, separate by space
 *  ID: 15 activity: running running group
 *  <exp>   ::= <title> <header> <header_info> <exp> | <title> | <header> <header_info> <exp>
 *  <title>  ::=  <activity> <output> | <output> | <output> <activity> <output> | <output> <activity>
 *  <header_info>  ::=  <activity> | <id>
 *  <activity>  ::=  <output>
 *  <id>  ::=  <output>
 *
 */
 /**
 * @Author Ke Yan
 */
// TODO do we need duplicate output?
public class Parser {
    public static class IllegalInputException extends IllegalArgumentException {
        public IllegalInputException(String errorMessage) {
            super(errorMessage);
        }
    }

    private Tokenizer tokenizer;
    private List<String> title;
    private List<String> time;
    private List<String> activity;
    private List<String> activities;
    private List<String> locations;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.title = new ArrayList<>();
        this.time = new ArrayList<>();
        this.activity = new ArrayList<>();
        this.locations = new ArrayList<>();
        initActivity();
    }

    // parse according to the grammar
    public void parseExp() {
        if (tokenizer.hasNext()) {
            if (tokenizer.current().getType() == Token.Type.TITLE) {
                parseTitle();
            }
            else if (tokenizer.current().getType() == Token.Type.TIME_HEADER) {
                parseTimeHeaderInfo();
            }
            else if (tokenizer.current().getType() == Token.Type.LOCATION_HEADER) {
                parseLocationHeaderInfo();
            }
            else if (tokenizer.current().getType() == Token.Type.ACTIVITY_HEADER) {
                parseActHeaderInfo();
            }
        }
    }

    public void parseTimeHeaderInfo() {
        // get unique id
        time.add(tokenizer.current().getToken().split(":")[1]);
        if (tokenizer.hasNext()) {
            tokenizer.next();
            parseExp();
        }
    }

    public void parseLocationHeaderInfo() {
        // get unique id
        locations.add(tokenizer.current().getToken().split(":")[1]);
        if (tokenizer.hasNext()) {
            tokenizer.next();
            parseExp();
        }
    }

    public void parseActHeaderInfo() {
        // check if is activity
        if (tokenizer.hasNext()) {
            activity.add(tokenizer.current().getToken().split(":")[1]);
            tokenizer.next();
            parseExp();
        }
    }

    public void parseTitle() {
        while (tokenizer.hasNext()) {
            if (tokenizer.current().getType() == Token.Type.TITLE) {
                title.add(tokenizer.current().getToken());
                // if title contain activity, add it to output
                if (activities.contains(tokenizer.current().getToken().toLowerCase()))
                    activity.add(tokenizer.current().getToken());
                tokenizer.next();
            }
            else { // if it is not title anymore, just
                parseExp();
                break;
            }
        }
    }

    public HashMap<String, List<String>> output() {
        HashMap<String, List<String>> rtn = new HashMap<>();
        rtn.put("title", title);
        rtn.put("time", time);
        rtn.put("location", locations);
        rtn.put("activity", activity);
        return rtn;
    }

    private void initActivity() {
        this.activities = new ArrayList<>();
        activities.add("basketball");
        activities.add("running");
    }
}
