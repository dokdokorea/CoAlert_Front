package com.example.user.coalert.item;

public class SimpleReviewList {
    private int one,two,three,four,five;
    private String creater;
    private String context;
    private int number;

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }

    public int getThree() {
        return three;
    }

    public int getFour() {
        return four;
    }

    public int getFive() {
        return five;
    }

    public String getCreater() {
        return creater;
    }

    public String getContext() {
        return context;
    }

    public SimpleReviewList(int number,String creater,String context){
        this.number=number;
        this.creater=creater;
        this.context=context;
    }
}
