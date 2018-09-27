package com.example.user.coalert.item;

public class IngredientList {
    private String Name;
    private double number;

    public String getName() {
        return Name;
    }

    public double getNumber() {
        return number;
    }

    public IngredientList(double number, String Name){
        this.Name=Name;
        this.number=number;

    }
}
