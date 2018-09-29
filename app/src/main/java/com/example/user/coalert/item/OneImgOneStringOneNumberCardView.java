package com.example.user.coalert.item;

public class OneImgOneStringOneNumberCardView {
    int image, number;
    String text;
    public int getImage(){
        return this.image;
    }

    public String getText() {
        return text;
    }

    public int getNumber(){
        return this.number;
    }

    public OneImgOneStringOneNumberCardView(int image, String text,int number){
        this.image=image;
        this.text=text;
        this.number=number;
    }
}
