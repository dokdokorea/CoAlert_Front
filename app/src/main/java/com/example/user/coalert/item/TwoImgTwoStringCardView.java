package com.example.user.coalert.item;

public class TwoImgTwoStringCardView {
    int img1;
    int img2;
    String text1;
    String text2;


    public int getImg1() {
        return img1;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public int getImg2() {
        return img2;
    }
    public TwoImgTwoStringCardView(int img1, int img2, String t1, String t2){
        this.img1=img1;
        this.img2=img2;
        this.text1=t1;
        this.text2=t2;
    }
}
