package com.example.sportsdating.user;

public enum UserErrorCode {
    USER_ALREADY_LOGGEDIN(1, "User has already looged in"),
    WRONG_PASSWORD(2, "Forget password?"),
    WRONG_USERNAME(3, "Forget username?"),
    LOGIN_SUCCESS(4, "Forget username?");
    private int code;
    private String msg;

    UserErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
