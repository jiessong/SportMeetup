package com.example.sportsdating.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * @Author Ke Yan
 */
public class Tokenizer {
    /**
     * token are seperated by space
     * rules:
     * headers
     * 1. time: + number without space or with space
     * 2. activity: + string
     * if not specified or without header, by default it is title
     */
    private final String[] buffer;          // String to be transformed into tokens each time next() is called.
    private Token currentToken;
    private int index;
    private final List<String> activities = new ArrayList<>();

    public Tokenizer(String text) {
        buffer = format(text).split("\\s+"); //regex expression
        index = 0;
        next(); // extracts the first token.
        initActivity();
    }

    // FIXME test
    // format input string
    public String format(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ':') {
                // remove all space after ":"
                while (i < sb.length() - 1  && sb.charAt(i + 1) == ' ') {
                    sb.delete(i + 1, i + 2);
                    i = i + 1;
                }
                // delete all space before ":"
                while (i > 1  && sb.charAt(i - 1) == ' ') {
                    sb.delete(i - 1, i);
                    i = i - 1;
                }
            }
        }
        return sb.toString();
    }

    public void next() {
        // if there's no string left, set currentToken null and return
        if (index >= buffer.length) {
            currentToken = null;
            return;
        }
        currentToken = null;
        // FIXME
        String[] buffer_i = buffer[index].split(":");
        if (buffer_i.length > 2) {
            throw new Token.IllegalTokenException("illegal headers");
        }
        if (buffer_i[0].equalsIgnoreCase("time")) {
            if (buffer_i.length == 1) {
                throw new Token.IllegalTokenException("illegal headers");
            } else {
                currentToken = new Token(buffer[index].toLowerCase(), Token.Type.TIME_HEADER);
                index += 1;
            }
        }
        else if (buffer_i[0].equalsIgnoreCase("activity")) {
            if (buffer_i.length == 1) {
                throw new Token.IllegalTokenException("illegal headers");
            } else {
                currentToken = new Token(buffer[index].toLowerCase(), Token.Type.ACTIVITY_HEADER);
                index += 1;
            }
        }
        else if (buffer_i[0].equalsIgnoreCase("location")) {
            if (buffer_i.length == 1) {
                throw new Token.IllegalTokenException("illegal headers");
            } else {
                currentToken = new Token(buffer[index].toLowerCase(), Token.Type.LOCATION_HEADER);
                index += 1;
            }
        }
//        else if (activities.contains(buffer[index].toLowerCase())) {
//            currentToken = new Token(buffer[index].toLowerCase(), Token.Type.ACTIVITY);
//            index += 1;
//        }
        else {
            currentToken = new Token(buffer[index].toLowerCase(), Token.Type.TITLE);
            index += 1;
        }
    }

    private void initActivity() {
        activities.add("basketball");
        activities.add("running");
    }

    public Token current() {
        return currentToken;
    }

    public boolean hasNext() {
        return currentToken != null;
    }
}
