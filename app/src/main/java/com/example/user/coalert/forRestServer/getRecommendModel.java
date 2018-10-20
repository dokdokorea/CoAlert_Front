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

    String pixel;

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }
    String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "{\"id\":\""+getCosmeticname()+"\", " +
                "\"estimate\":"+getEstimate()+",\"pixel\": \""+getPixel()+"\", \"company\":"+getCompany()+"}";
    }
}
