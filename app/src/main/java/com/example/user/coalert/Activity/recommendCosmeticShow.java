package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.RecommendedCosmeticAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.example.user.coalert.item.OneImgThreeStringCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReceiveData;
    TextView showRecommendTextView;
    RecyclerView CosmeticRecycler;
    ArrayList<OneImgThreeStringCardView> cosmeticArr;
    int kindCosmetic;
    boolean lastitemVisibleFlag;
    JsonArray purifyDataArray;
    ImageView back_btn;
    RecommendedCosmeticAdapter recommendedCosmeticAdapter;
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastitemVisibleFlag = false;
        setContentView(R.layout.activity_recommend_cosmetic_show);
        getReceiveData = getIntent();
        cosmeticArr = new ArrayList<>();
        CosmeticRecycler=findViewById(R.id.recommend_recycler);

        final String recommendCosmetics = getReceiveData.getStringExtra("recommendData");
        kindCosmetic = getReceiveData.getIntExtra("kindCosmetic", 0);
        purifyDataArray = dataToJsonArray(recommendCosmetics);
        setData(purifyDataArray);
        recommendedCosmeticAdapter = new RecommendedCosmeticAdapter(getApplicationContext(),cosmeticArr,R.layout.activity_recommend_cosmetic_show);
        back_btn = findViewById(R.id.recommend_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CosmeticRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        CosmeticRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                } else {
                    // Scrolling down
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Log.e("바닥이야", String.valueOf(cosmeticArr.size()));
                    //TODO 스크롤이 바닥에 닿을 때
                    //TODO 서버에 요청할 때는 항상 스레드를 사용하시오
                    if (cosmeticArr.size() < 29) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Call<List<getRecommendModel>> addRecommendCosmetic = ForRestSingleton.getInstance().recommendCall(0, kindCosmetic + 1, "0", cosmeticArr.size());
                                    List<getRecommendModel> addRecommendCosmeticData = addRecommendCosmetic.execute().body();
                                    final String addRecommendCosmeticDataString = addRecommendCosmeticData.toString();
                                    Log.e("asfsdafsadf", addRecommendCosmeticDataString);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            JsonArray addRecommendCosmeticDataJsonArray = dataToJsonArray(addRecommendCosmeticDataString);
                                            setData(addRecommendCosmeticDataJsonArray);
                                            recommendedCosmeticAdapter.notifyDataSetChanged();
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
        });
        CosmeticRecycler.setAdapter(recommendedCosmeticAdapter);


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
                if (recommendedCosmeticAdapter.getItemCount() < 29) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Call<List<getRecommendModel>> addRecommendCosmetic = ForRestSingleton.getInstance().recommendCall(0, kindCosmetic + 1, "0", recommendedCosmeticAdapter.getItemCount());
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



    public void setData(JsonArray recommendCosmeticJsonArray) {
        //TODO 어뎁터에 데이터를 추가합니다
        for (int i = 0; i < recommendCosmeticJsonArray.size(); i++) {
            JsonObject oneData = (JsonObject) recommendCosmeticJsonArray.get(i);
            cosmeticArr.add(new OneImgThreeStringCardView(R.drawable.sun1,"innisfree",oneData.get("id").toString(), Float.valueOf(oneData.get("estimate").toString())));
        }
    }

}
