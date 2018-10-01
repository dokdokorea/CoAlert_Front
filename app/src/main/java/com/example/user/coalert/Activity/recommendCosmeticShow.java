package com.example.user.coalert.Activity;

import
        android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.Adapter.recommendCosmeticAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReceiveData;
    TextView showRecommendTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_cosmetic_show);
        showRecommendTextView = findViewById(R.id.showRecommendTextView);
        getReceiveData = getIntent();
        String Cname = getReceiveData.getStringExtra("cname");
        showRecommendTextView.setText("추천 받을 화장품: " + Cname);
        String recommendCosmetics = getReceiveData.getStringExtra("recommendData");
        recommendCosmeticAdapter recommendCosmeticAdapter = new recommendCosmeticAdapter();
        String[] beforeToJson = getDataAtJson(recommendCosmetics);
        for (int i = 0; i< 10; i++){
            String[] splitJson = beforeToJson[i].split(",");
            String cosmeticName = splitJson[0].substring(4);
            String estimate = splitJson[1].substring(10, splitJson[1].length()-1);
            setData(recommendCosmeticAdapter, cosmeticName, estimate);
        }
    }
    public void setData(recommendCosmeticAdapter adapter, String Cname, String estimate){
        adapter.addItem(Cname, estimate);
        ListView RecommendCosmetic = findViewById(R.id.showRecommendListView);
        RecommendCosmetic.setAdapter(adapter);
    }
    public String[] getDataAtJson(String recommendCosmetics){

        recommendCosmetics = recommendCosmetics.substring(1, recommendCosmetics.length() - 1);
        int comma = 0;
        StringBuilder recommendCosmeticsBuilder = new StringBuilder(recommendCosmetics);
        for (int i = 0; i < recommendCosmetics.length(); i++) {
            if (recommendCosmetics.charAt(i) == ',') {
                if (comma % 2 == 1) {
                    recommendCosmeticsBuilder.setCharAt(i, '@');
                }
                comma +=1;
            }
        }
        String[] beforeToJson = recommendCosmeticsBuilder.toString().split("@");
        return beforeToJson;
    }
}
