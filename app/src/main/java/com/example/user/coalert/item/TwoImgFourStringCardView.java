package com.example.user.coalert.item;

public class TwoImgFourStringCardView {
    int img1,img2;
    String t1,t2,t3,t4;


    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public String getT3() {
        return t3;
    }

    public String getT4() {
        return t4;
    }

    public int getImg1() {
        return img1;
    }

    public int getImg2() {
        return img2;
    }

    public TwoImgFourStringCardView(int img1, int img2, String t1, String t2,String t3,String t4){
        this.img1=img1;
        this.img2=img2;
        this.t1=t1;
        this.t2=t2;
        this.t3=t3;
        this.t4=t4;
    }
}
