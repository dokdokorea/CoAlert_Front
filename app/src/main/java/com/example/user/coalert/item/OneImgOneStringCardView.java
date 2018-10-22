package com.example.user.coalert.item;

public class OneImgOneStringCardView {
    int image;
    String text;
    int whatKind;

    public OneImgOneStringCardView(int image, String text, int whatKind) {
        this.image = image;
        this.text = text;
        this.whatKind = whatKind;
    }
    public int getWhatKind(){return this.whatKind;}
    public int getImage(){
        return this.image;
    }

    public String getText() {
        return this.text;
    }

    public OneImgOneStringCardView(int image, String text){
        this.image=image;
        this.text=text;
    }
}
