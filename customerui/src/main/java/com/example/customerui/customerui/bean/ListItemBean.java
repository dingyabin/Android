package com.example.customerui.customerui.bean;

/**
 * Created by MrDing on 2016/11/26.
 */

public class ListItemBean {

    private int imageId;

    private String text;

    public ListItemBean() {
    }

    public ListItemBean(int imageId, String text) {
        this.imageId = imageId;
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String test) {
        this.text = test;
    }
}
