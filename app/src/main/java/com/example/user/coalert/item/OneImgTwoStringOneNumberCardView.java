package com.example.user.coalert.item;

public class OneImgTwoStringOneNumberCardView {
    int image, number;
    String text1,text2;
    public int getImage(){
        return this.image;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public int getNumber(){
        return this.number;
    }

    public OneImgTwoStringOneNumberCardView(int image, String text1,String text2, int number){
        this.image=image;
        this.text1=text1;
        this.text2=text2;
        this.number=number;
    }
}
