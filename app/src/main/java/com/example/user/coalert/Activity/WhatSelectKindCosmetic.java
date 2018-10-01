package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.coalert.Adapter.kindCosmeticAdapter;
import com.example.user.coalert.R;


public class WhatSelectKindCosmetic extends AppCompatActivity {
    kindCosmeticAdapter kindCosmeticAdapter;
    ListView kindCosmeticListView;
    String[] data = {"립틴트", "선블락", "아이쉐도우", "파운데이션"};
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
        kindCosmeticListView.setAdapter(forAdapter);
        kindCosmeticListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    Log.e("Click Event: ", data[pos]);
                    
            }
        });
    }
}
