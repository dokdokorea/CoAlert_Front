package com.example.user.coalert.item;

import android.graphics.Bitmap;

public class adCardViewItem {
    int bitmap;
    String explain;
    int num;
    int cosmeticType;

    public int getNum() {
        return num;
    }

    public int getCosmeticType() {
        return cosmeticType;
    }

    public void setBitmap(int bitmap) {

        this.bitmap = bitmap;
    }

    public adCardViewItem(int bitmap, String explain, int num, int cosmeticType) {

        this.bitmap = bitmap;
        this.explain = explain;
        this.num = num;
        this.cosmeticType = cosmeticType;
    }

    public adCardViewItem(int bitmap, String explain, int num){
        this.bitmap = bitmap;
        this.explain = explain;
        this.num = num;
    }

    public int getBitmap() {
        return bitmap;
    }

    public String getExplain() {
        return explain;
    }
    public int getInt(){return num;}
}
