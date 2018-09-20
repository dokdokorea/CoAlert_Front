package com.example.user.coalert.item;

public class CosmeticList_mypage {
    int image;
    String name;
    public int getImage(){
        return this.image;
    }
    public String getTitle(){
        return this.name;
    }
    public CosmeticList_mypage(int image, String name){
        this.image=image;
        this.name=name;
    }
}
