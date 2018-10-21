package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.kindCosmeticAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.Singleton.UUFiSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class WhatSelectKindCosmetic extends AppCompatActivity {
    kindCosmeticAdapter kindCosmeticAdapter;
    RecyclerView kindCosmeticListView;
    ImageView backButton;
    TextView backHome;
    ArrayList<OneImgOneStringCardView> oneCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_select_kind_cosmetic);

        oneCardView = new ArrayList<>();
        oneCardView = setImage(oneCardView);
        kindCosmeticAdapter = new kindCosmeticAdapter(oneCardView, getApplicationContext());
        kindCosmeticListView = findViewById(R.id.selectKindCosmetic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        kindCosmeticListView.setHasFixedSize(true);
        kindCosmeticListView.setLayoutManager(layoutManager);
        backButton = findViewById(R.id.what_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        kindCosmeticListView.setAdapter(kindCosmeticAdapter);
        backHome = findViewById(R.id.kind_back_home);
        backHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Log.e("finish", "1");
                finish();
            }
        });
    }

    public ArrayList<OneImgOneStringCardView> setImage(ArrayList<OneImgOneStringCardView> arrayList){
        arrayList.add(new OneImgOneStringCardView(R.drawable.kind_suncream, "선블락"));
        arrayList.add(new OneImgOneStringCardView(R.drawable.eyeshadow_background, "아이쉐도우"));
        arrayList.add(new OneImgOneStringCardView(R.drawable.foundation_background, "파운데이션"));
        arrayList.add(new OneImgOneStringCardView(R.drawable.libtint_background, "립틴트"));
        return arrayList;
    }

}
