package com.example.user.coalert.forRestServer;

public class getRecommendModel {
    String cosmeticname;
    int estimate;

    public String getCosmeticname() {
        return cosmeticname;
    }

    public void setCosmeticname(String cosmeticname) {
        this.cosmeticname = cosmeticname;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    @Override
    public String toString() {
        return "{id:"+getCosmeticname()+", " +
                "estimate:"+getEstimate()+"}";
    }
}
