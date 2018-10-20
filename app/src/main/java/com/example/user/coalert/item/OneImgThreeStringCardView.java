package com.example.user.coalert.item;

import android.graphics.Bitmap;

public class OneImgThreeStringCardView {
    Bitmap image;
    String text1,text2;
    float number;

    public Bitmap getImage(){
        return this.image;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public float getNumber(){
        return this.number;
    }

    public OneImgThreeStringCardView(Bitmap image, String text1, String text2, float number){
        this.image=image;
        this.text1=text1;
        this.text2=text2;
        this.number=number;
    }
}
