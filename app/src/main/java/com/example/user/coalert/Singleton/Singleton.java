package com.example.user.coalert.Singleton;

import android.telecom.TelecomManager;
import android.util.Log;

public class Singleton {
    private static Singleton singleton = new Singleton();
    private String IndependenceNum;

    public String getIndependenceNum() {
        return IndependenceNum;
    }

    public void setIndependenceNum(String independenceNum) {
        IndependenceNum = independenceNum;
    }

    private Singleton(){}

    public static Singleton getInstance(){
        return singleton;
    }
}
