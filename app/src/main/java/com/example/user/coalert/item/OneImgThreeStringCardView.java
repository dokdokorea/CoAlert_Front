package com.example.user.coalert.item;

public class OneImgThreeStringCardView {
    int img;
    String text1;
    String text2;
    String text3;

    public int getImg() {
        return img;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }
    public OneImgThreeStringCardView(int img,String t1,String t2,String t3){
        this.img=img;
        this.text1=t1;
        this.text2=t2;
        this.text3=t3;
    }
}
