package com.example.supunnethmal.slide.API;

public class User {
    private String type;
    private String msg;
    private String msg_rtrn;


    public User() {
    }

    public User(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }


    public String getMsg_rtrn() {
        return msg_rtrn;
    }

    public void setMsg_rtrn(String msg_rtrn) {
        this.msg_rtrn = msg_rtrn;
    }
}
