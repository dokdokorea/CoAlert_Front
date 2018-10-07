package com.example.user.coalert.Singleton;

import com.example.user.coalert.Background;

public class ForBackgroundSingleton {
    private static Background Background;

    public static Background getInstance(){
        if(Background == null){
            Background = new Background();
            return Background;
        }
        else{
            return Background;
        }
    }
}