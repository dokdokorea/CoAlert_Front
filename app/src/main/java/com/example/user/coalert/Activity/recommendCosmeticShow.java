package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.Adapter.recommendCosmeticAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReceiveData;
    TextView showRecommendTextView;
    ListView RecommendCosmetic;
    ScrollView scrollView;
    boolean lastitemVisibleFlag;
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastitemVisibleFlag = false;
        setContentView(R.layout.activity_recommend_cosmetic_show);
        showRecommendTextView = findViewById(R.id.showRecommendTextView);
        scrollView = findViewById(R.id.recommendScrollView);
        getReceiveData = getIntent();
        String Cname = getReceiveData.getStringExtra("cname");
        showRecommendTextView.setText("추천 받을 화장품: " + Cname);
        String recommendCosmetics = getReceiveData.getStringExtra("recommendData");
        recommendCosmeticAdapter recommendCosmeticAdapter = new recommendCosmeticAdapter();
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(recommendCosmetics);
        JsonArray array = element.getAsJsonArray();
        setData(array, recommendCosmeticAdapter);
        RecommendCosmetic.setOnTouchListener(forRecommendListViewEvent);
        RecommendCosmetic.setOnScrollListener(listViewScrollEvent);
    }

   AbsListView.OnScrollListener listViewScrollEvent = new AbsListView.OnScrollListener() {
       @Override
       public void onScrollStateChanged(AbsListView absListView, int scrollState) {
           if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
               //TODO 스크롤이 바닥에 닿을 때

           }
       }

       @Override
       public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
           lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
       }
   };

    View.OnTouchListener forRecommendListViewEvent =  new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            scrollView.requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };
    public void setData(JsonArray recommendCosmeticJsonArray, recommendCosmeticAdapter adapter) {
        for (int i = 0; i < recommendCosmeticJsonArray.size(); i++) {
            JsonObject oneData = (JsonObject) recommendCosmeticJsonArray.get(i);
            adapter.addItem(oneData.get("id"), oneData.get("estimate"));
        }
        RecommendCosmetic = findViewById(R.id.showRecommendListView);
        RecommendCosmetic.setAdapter(adapter);
    }

}
