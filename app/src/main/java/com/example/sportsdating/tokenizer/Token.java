package com.example.sportsdating.tokenizer;
/**
 * @Author Ke Yan
 */
public class Token {
    public enum Type {ACTIVITY, TITLE, MESSAGE, TIME_HEADER, ACTIVITY_HEADER, LOCATION_HEADER}

    // show error message for different situation
    public static class IllegalTokenException extends IllegalArgumentException {
        public IllegalTokenException(String errorMessage) {
            super(errorMessage);
        }
    }

    // Fields of the class Token.
    private final String token;
    private final Type type;

    public Token(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + "";
    }
}
