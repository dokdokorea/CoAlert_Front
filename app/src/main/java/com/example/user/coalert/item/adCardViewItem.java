package com.example.user.coalert.item;

import android.graphics.Bitmap;

public class adCardViewItem {
    Bitmap bitmap;
    String explain;
    public adCardViewItem(Bitmap bitmap, String explain){
        this.bitmap = bitmap;
        this.explain = explain;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getExplain() {
        return explain;
    }
}
