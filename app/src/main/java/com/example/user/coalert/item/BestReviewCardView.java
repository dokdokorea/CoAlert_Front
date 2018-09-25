package com.example.user.coalert.item;

public class BestReviewCardView {
    int image;
    String id;
    String content;
    public int getImage(){return this.image;}
    public String getId(){return this.id;}
    public String getContent(){return this.content;}
    public BestReviewCardView(int image,String id,String content){
        this.image=image;
        this.id=id;
        this.content=content;
    }
}
