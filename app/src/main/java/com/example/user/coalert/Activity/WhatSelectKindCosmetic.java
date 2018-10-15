package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.coalert.Adapter.kindCosmeticAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.Singleton.UUFiSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;

import java.io.IOException;


import java.util.List;

import retrofit2.Call;


public class WhatSelectKindCosmetic extends AppCompatActivity {
    kindCosmeticAdapter kindCosmeticAdapter;
    ListView kindCosmeticListView;
    String[] data = {"선블락", "아이쉐도우", "파운데이션", "립틴트"};
    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_what_select_kind_cosmetic);
        kindCosmeticAdapter = new kindCosmeticAdapter();
        kindCosmeticListView = findViewById(R.id.selectKindCosmetic);
        ArrayAdapter forAdapter = new ArrayAdapter<>(
                this,
                R.layout.activity_what_select_kind_cosmetic_item,
                data);
        backButton = findViewById(R.id.kind_cosmetic_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        kindCosmeticListView.setAdapter(forAdapter);
        kindCosmeticListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Call<List<getRecommendModel>> call = ForRestSingleton.getInstance().recommendCall(0, pos + 1, "0", 0);
                            List<getRecommendModel> result = call.execute().body();
                            String moveRecommendCosmetic = result.toString();
                            Intent recommendPage = new Intent(getBaseContext(), recommendCosmeticShow.class);
                            recommendPage.putExtra("kindCosmetic",pos);
                            recommendPage.putExtra("cname", data[pos]);
                            recommendPage.putExtra("recommendData", moveRecommendCosmetic);
                            Log.e("first Result", moveRecommendCosmetic);
                            startActivity(recommendPage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
