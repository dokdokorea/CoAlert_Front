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
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReceiveData;
    TextView showRecommendTextView;
    ListView RecommendCosmetic;

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
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(recommendCosmetics);
        JsonArray array = element.getAsJsonArray();
        setData(array, recommendCosmeticAdapter);

    }

    public void setData(JsonArray recommendCosmeticJsonArray, recommendCosmeticAdapter adapter) {
        for (int i = 0; i < recommendCosmeticJsonArray.size(); i++) {
            JsonObject oneData = (JsonObject) recommendCosmeticJsonArray.get(i);
            adapter.addItem(oneData.get("id"), oneData.get("estimate"));
        }
        RecommendCosmetic = findViewById(R.id.showRecommendListView);
        RecommendCosmetic.setAdapter(adapter);
    }

}
