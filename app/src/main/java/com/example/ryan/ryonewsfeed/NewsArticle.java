package com.example.ryan.ryonewsfeed;

public class NewsArticle {
    private String aURL;
    private String aTitle;
    private String aAuthor;
    private String aDate;

    //Add thumbnail later.

    public NewsArticle(){

    }

    public NewsArticle(String URL,String Title, String Author, String Date) {
        this.aURL = URL;
        this.aTitle = Title;
        this.aAuthor = Author;
        this.aDate = Date;

    }

    public String getaURL() {
        return aURL;
    }


    public String getaTitle() {
        return aTitle;
    }

    public String getaAuthor() {
        return aAuthor;
    }

    public String getaDate() {
        return aDate;
    }
}
