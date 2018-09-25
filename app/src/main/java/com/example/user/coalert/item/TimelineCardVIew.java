package com.example.user.coalert.item;

public class TimelineCardVIew {
    int image;
    String title;
    String username;
    public int getImage(){
        return this.image;
    }
    public String getTitle(){
        return this.title;
    }
    public String getUsername(){
        return this.username;
    }
    public TimelineCardVIew(int image, String title, String username){
        this.image=image;
        this.title=title;
        this.username = username;
    }
}