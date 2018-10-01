package com.example.user.coalert.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.coalert.R;

import java.util.ArrayList;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReciecveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_cosmetic_show);
        getReciecveData = getIntent();
        String Cname = getReciecveData.getStringExtra("cname");
        String recommendCosmetics = getReciecveData.getStringExtra("recommendData");
        Log.e("receive Data: ", recommendCosmetics);

    }
}
