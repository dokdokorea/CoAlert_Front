package com.example.user.coalert.Singleton;

import com.example.user.coalert.forRestServer.CoAlertService;

public class ForRestSingleton {
    private static CoAlertService coAlertService = CoAlertService.retrofit.create(CoAlertService.class);
    private ForRestSingleton(){}
    public static CoAlertService getInstance(){
        return coAlertService;
    }
}
