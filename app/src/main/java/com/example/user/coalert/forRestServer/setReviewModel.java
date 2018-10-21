package com.example.user.coalert.forRestServer;

public class setReviewModel {
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{\"result\":\""+ getResult()+"\"}";
    }
}
