package com.example.gnaiz.infobubble.vo;

public class News {
    private String newsID;
    private String title;
    private String author;
    private String time;
    private String location;
    private String imgURL;
    private String num_like;
    private String num_view;
    private String num_comment;
    private String content;

    public News(String newsID, String title, String author, String time, String location, String imgURL, String num_like, String num_view, String num_comment, String content) {
        this.newsID = newsID;
        this.title = title;
        this.author = author;
        this.time = time;
        this.location = location;
        this.imgURL = imgURL;
        this.num_like = num_like;
        this.num_view = num_view;
        this.num_comment = num_comment;
        this.content = content;
    }

    public News(String newsID, String title, String time, String location, String imgURL) {
        this.newsID = newsID;
        this.title = title;
        this.time = time;
        this.location = location;
        this.imgURL = imgURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getNum_like() {
        return num_like;
    }

    public void setNum_like(String num_like) {
        this.num_like = num_like;
    }

    public String getNum_view() {
        return num_view;
    }

    public void setNum_view(String num_view) {
        this.num_view = num_view;
    }

    public String getNum_comment() {
        return num_comment;
    }

    public void setNum_comment(String num_comment) {
        this.num_comment = num_comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
