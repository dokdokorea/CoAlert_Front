package com.example.user.coalert.forRestServer;

public class getRecommendModel {
    String cosmeticname;

    public float getEstimate() {
        return estimate;
    }

    public void setEstimate(float estimate) {
        this.estimate = estimate;
    }

    float estimate;

    public String getCosmeticname() {
        return cosmeticname;
    }

    public void setCosmeticname(String cosmeticname) {
        this.cosmeticname = cosmeticname;
    }


    @Override
    public String toString() {
        return "{\"id\":\""+getCosmeticname()+"\", " +
                "\"estimate\":"+getEstimate()+"}";
    }
}
