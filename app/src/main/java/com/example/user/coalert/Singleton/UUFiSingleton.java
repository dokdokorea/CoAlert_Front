package com.example.user.coalert.Singleton;

public class UUFiSingleton {
    private static UUFiSingleton UUFiSingleton = new UUFiSingleton();
    private String IndependenceNum;

    public String getIndependenceNum() {
        return IndependenceNum;
    }

    public void setIndependenceNum(String independenceNum) {
        IndependenceNum = independenceNum;
    }

    private UUFiSingleton(){}

    public static UUFiSingleton getInstance(){
        return UUFiSingleton;
    }
}
