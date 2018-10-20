package com.example.user.coalert.forRestServer;

public class GetBadIngredientModel {
    String ingredientName;
    String warningRate;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getWarningRate() {
        return warningRate;
    }

    public void setWarningRate(String warningRate) {
        this.warningRate = warningRate;
    }

    @Override
    public String toString() {
        return "{\"ingredientName\":\""+getIngredientName()+"\", \"warningRate\":\""+getWarningRate()+"\"}";
    }
}
