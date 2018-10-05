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
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import retrofit2.Call;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReceiveData;
    TextView showRecommendTextView;
    ListView RecommendCosmetic;
    ScrollView scrollView;
    int kindCosmetic;
    boolean lastitemVisibleFlag;
    JsonArray purifyDataArray;
    recommendCosmeticAdapter recommendCosmeticAdapter;

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
        kindCosmetic = getReceiveData.getIntExtra("kindCosmetic", 0);
        recommendCosmeticAdapter = new recommendCosmeticAdapter();
        purifyDataArray = dataToJsonArray(recommendCosmetics);
        setData(purifyDataArray);
        RecommendCosmetic.setOnTouchListener(forRecommendListViewEvent);
        RecommendCosmetic.setOnScrollListener(listViewScrollEvent);
    }

    public JsonArray dataToJsonArray(String data) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(data);
        return element.getAsJsonArray();
    }

    AbsListView.OnScrollListener listViewScrollEvent = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                //TODO 스크롤이 바닥에 닿을 때
                //TODO 서버에 요청할 때는 항상 스레드를 사용하시오
                if (recommendCosmeticAdapter.getCount() < 29) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Call<List<getRecommendModel>> addRecommendCosmetic = ForRestSingleton.getInstance().recommendCall(0, kindCosmetic + 1, "0", recommendCosmeticAdapter.getCount());
                                List<getRecommendModel> addRecommendCosmeticData = addRecommendCosmetic.execute().body();
                                final String addRecommendCosmeticDataString = addRecommendCosmeticData.toString();
                                Log.e("asfsdafsadf", addRecommendCosmeticDataString);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        JsonArray addRecommendCosmeticDataJsonArray = dataToJsonArray(addRecommendCosmeticDataString);
                                        setData(addRecommendCosmeticDataJsonArray);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                }
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
        }
    };

    View.OnTouchListener forRecommendListViewEvent = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            scrollView.requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };

    public void setData(JsonArray recommendCosmeticJsonArray) {
        //TODO 어뎁터에 데이터를 추가합니다
        for (int i = 0; i < recommendCosmeticJsonArray.size(); i++) {
            Log.e("oneCyclingData", String.valueOf(i));
            JsonObject oneData = (JsonObject) recommendCosmeticJsonArray.get(i);
            recommendCosmeticAdapter.addItem(oneData.get("id"), oneData.get("estimate"));
        }
        RecommendCosmetic = findViewById(R.id.showRecommendListView);
        RecommendCosmetic.setAdapter(recommendCosmeticAdapter);
    }

}
