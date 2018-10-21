package com.example.user.coalert.forRestServer;

public class getReviewModel {
    String review;
    String rating;
    String type;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{\"review\":\""+getReview()+"\",\"rating\":\""+getRating()+"\",\"type\":\""+getType()+"\"}";
    }
}
