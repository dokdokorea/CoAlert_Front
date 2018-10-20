package com.example.user.coalert.GlobalInformation;
import android.app.Application;
import java.util.ArrayList;

public class GlobalClass extends Application
{
    private String Id;
    private ArrayList<String> wishlist;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}