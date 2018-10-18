package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.RecommendedCosmeticAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.Adapter.recommendCosmeticAdapter;
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
//    ListView RecommendCosmetic;
    RecyclerView CosmeticRecycler;
    ArrayList<OneImgThreeStringCardView> cosmeticArr;
    int kindCosmetic;
    boolean lastitemVisibleFlag;
    JsonArray purifyDataArray;
    RecommendedCosmeticAdapter Adapter;
//    recommendCosmeticAdapter recommendCosmeticAdapter;
    ImageView back_btn;
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastitemVisibleFlag = false;
        setContentView(R.layout.activity_recommend_cosmetic_show);
        getReceiveData = getIntent();
        String Cname = getReceiveData.getStringExtra("cname");
//        RecommendCosmetic = findViewById(R.id.showRecommendListView);
        CosmeticRecycler=findViewById(R.id.recommend_recycler);
        String recommendCosmetics = getReceiveData.getStringExtra("recommendData");
        kindCosmetic = getReceiveData.getIntExtra("kindCosmetic", 0);
//        recommendCosmeticAdapter = new recommendCosmeticAdapter();
        purifyDataArray = dataToJsonArray(recommendCosmetics);
        setData(purifyDataArray);
        back_btn = findViewById(R.id.recommend_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                    //TODO 스크롤이 바닥에 닿을 때
                    //TODO 서버에 요청할 때는 항상 스레드를 사용하시오
                    if (Adapter.getItemCount() < 29) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Call<List<getRecommendModel>> addRecommendCosmetic = ForRestSingleton.getInstance().recommendCall(0, kindCosmetic + 1, "0", Adapter.getItemCount());
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

//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
//                    // Do something
//                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    // Do something
//                } else {
//                    // Do something
//                }
            }
        });
//        RecommendCosmetic.setOnScrollListener(listViewScrollEvent);
//        RecommendCosmetic.setOnTouchListener(forRecommendListViewEvent);
//        RecommendCosmetic.setOnScrollListener(listViewScrollEvent);
//        RecommendCosmetic.setAdapter(recommendCosmeticAdapter);

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
                if (Adapter.getItemCount() < 29) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Call<List<getRecommendModel>> addRecommendCosmetic = ForRestSingleton.getInstance().recommendCall(0, kindCosmetic + 1, "0", Adapter.getItemCount());
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

//    View.OnTouchListener forRecommendListViewEvent = new View.OnTouchListener() {
////        @Override
////        public boolean onTouch(View view, MotionEvent motionEvent) {
////            scrollView.requestDisallowInterceptTouchEvent(true);
////            return false;
////        }
////    };

    public void setData(JsonArray recommendCosmeticJsonArray) {
        //TODO 어뎁터에 데이터를 추가합니다
        for (int i = 0; i < recommendCosmeticJsonArray.size(); i++) {
            Log.e("oneCyclingData", String.valueOf(i));
            JsonObject oneData = (JsonObject) recommendCosmeticJsonArray.get(i);
            cosmeticArr.add(new OneImgThreeStringCardView(R.drawable.sun1,"innisfree",oneData.get("id").toString(), Float.valueOf(oneData.get("estimate").toString())));
        }
        CosmeticRecycler.setAdapter(new RecommendedCosmeticAdapter(getApplicationContext(),cosmeticArr,R.layout.activity_recommend_cosmetic_show));
        Adapter.notifyDataSetChanged();

        //recommendCosmeticAdapter.notifyDataSetChanged();

    }

}
