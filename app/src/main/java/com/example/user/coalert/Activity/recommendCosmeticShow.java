package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.user.coalert.R;
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
        JsonParser jsonParser = new JsonParser();
        String[] beforeToJson = recommendCosmeticsBuilder.toString().split("@");
        for (int i = 0; i< 10; i++){
            String[] splitJson = beforeToJson[i].split(",");
            String cosmeticName = splitJson[0].substring(4);
            String estimate = splitJson[1].substring(10, splitJson[1].length()-1);
            Log.e("asdasd", cosmeticName+estimate);
        }

//        jsonParser.parse(recommendCosmetics);
//        Log.e("1등", recommendCosmetics);111

    }
}
