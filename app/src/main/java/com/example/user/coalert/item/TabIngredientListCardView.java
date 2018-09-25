package com.example.user.coalert.item;

import android.widget.ImageView;
import android.widget.TextView;

public class TabIngredientListCardView {
    int img;
    String text;

    public int getImg() {
        return this.img;
    }

    public String getText() {
        return this.text;
    }
    public TabIngredientListCardView(int image,String text){
        this.img=image;
        this.text=text;
    }
}
