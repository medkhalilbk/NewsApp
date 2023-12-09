package com.example.newsapp;

public class News {
    private String image;
    private String title;
    private String description;
    private String type;
    private String date;

    public News(String image, String title, String description, String type,String date) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
}
