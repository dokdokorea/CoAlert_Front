package com.example.user.coalert.item;

public class NotificationCard {

    public int img;
    public String text;

    public int getImage() {
        return this.img;
    }

    public String getTitle() {
        return this.text;
    }

    public NotificationCard(String text, int image) {
        this.img = image;
        this.text = text;
    }
}