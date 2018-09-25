package com.example.user.coalert.item;

public class NewProductCardView {
    int image;
    String title;
    String company;
    public int getImage(){return this.image;}
    public String getTitle(){return this.title;}
    public String getCompany(){return this.company;}
    public NewProductCardView(int image,String title,String company){
        this.image=image;
        this.title=title;
        this.company=company;
    }
}
