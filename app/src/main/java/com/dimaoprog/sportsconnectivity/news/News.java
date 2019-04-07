package com.dimaoprog.sportsconnectivity.news;

public class News {
    private String title;
    private String shortNew;
    private String longNew;
    private int image;

    public News (String title, String shortNew, String longNew, int image) {
        this.title = title;
        this.shortNew = shortNew;
        this.longNew = longNew;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortNew() {
        return shortNew;
    }

    public void setShortNew(String shortNew) {
        this.shortNew = shortNew;
    }

    public String getLongNew() {
        return longNew;
    }

    public void setLongNew(String longNew) {
        this.longNew = longNew;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
