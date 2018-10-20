package com.example.user.coalert.item;

import android.graphics.Bitmap;

public class adCardViewItem {
    Bitmap bitmap;
    String explain;
    int num;
    public adCardViewItem(Bitmap bitmap, String explain, int num){
        this.bitmap = bitmap;
        this.explain = explain;
        this.num = num;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getExplain() {
        return explain;
    }
    public int getInt(){return num;}
}
