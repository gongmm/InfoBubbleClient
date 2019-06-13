package com.example.gnaiz.infobubble.vo;

public class Comment {
    private String username;
    private String imgURL;
    private String time;
    private String content;

    public Comment(String username, String imgURL, String time, String content) {
        this.username = username;
        this.imgURL = imgURL;
        this.time = time;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
