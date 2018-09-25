package com.example.user.coalert.item;

public class OneImgOneStringCardView {
    int image;
    String text;
    public int getImage(){
        return this.image;
    }

    public String getText() {
        return text;
    }

    public OneImgOneStringCardView(int image, String text){
        this.image=image;
        this.text=text;
    }
}
