package com.example.customerui.chat.bean;

import java.util.Date;

/**
 * Created by MrDing on 2016/11/26.
 */

public class Msg {

    public static final int RECIVE = 0;

    public static final int SEND = 1;


    private String content;


    private int type;


    private Date time;


    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }


    public Msg(String content, int type , Date time) {
        this.content = content;
        this.time = time;
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
