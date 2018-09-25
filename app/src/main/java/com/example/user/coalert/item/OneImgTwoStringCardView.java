package com.example.user.coalert.item;

public class OneImgTwoStringCardView {
    int image;
    String text1;
    String text2;
    public int getImage(){return this.image;}

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public OneImgTwoStringCardView(int image, String text1, String text2){
        this.image=image;
        this.text1=text1;
        this.text2=text2;
    }
}
