package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.RecommendedCosmeticAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.example.user.coalert.item.OneImgThreeStringCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class recommendCosmeticShow extends AppCompatActivity {
    Intent getReceiveData;
    TextView showRecommendTextView;
    SwipyRefreshLayout swipeRefreshLayout;
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
        swipeRefreshLayout = findViewById(R.id.swipyrefreshlayout);
        swipeRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
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
                int lastVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
                                    @Override
                                    public void onRefresh(SwipyRefreshLayoutDirection direction) {
                                        Log.d("MainActivity", "Refresh triggered at "
                                                + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
                                    }
                                });
                                Call<List<getRecommendModel>> addRecommendCosmetic = ForRestSingleton.getInstance().recommendCall(0, kindCosmetic + 1, "0", cosmeticArr.size());
                                List<getRecommendModel> addRecommendCosmeticData = addRecommendCosmetic.execute().body();
                                final String addRecommendCosmeticDataString = addRecommendCosmeticData.toString();
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
        });
        CosmeticRecycler.setAdapter(recommendedCosmeticAdapter);
    }

    public JsonArray dataToJsonArray(String data) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(data);
        return element.getAsJsonArray();
    }

    public void setData(JsonArray recommendCosmeticJsonArray) {
        //TODO 어뎁터에 데이터를 추가합니다
        for (int i = 0; i < recommendCosmeticJsonArray.size(); i++) {
            JsonObject oneData = (JsonObject) recommendCosmeticJsonArray.get(i);
            Log.e("pixel", oneData.get("pixel").toString());
            cosmeticArr.add(new OneImgThreeStringCardView(StringToBitMap(oneData.get("pixel").toString().substring(2, oneData.get("pixel").toString().length())), oneData.get("company").toString().replaceAll("\"", ""),oneData.get("id").toString(), Float.valueOf(oneData.get("estimate").toString())));
        }
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            Log.e("asdasd", bitmap.toString());
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

}
