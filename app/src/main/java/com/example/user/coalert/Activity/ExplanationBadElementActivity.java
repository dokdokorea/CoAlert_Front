package com.example.user.coalert.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.user.coalert.Adapter.MyprofileAdapter.ExplanationBadElementAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.TabIngredientListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoStringCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class ExplanationBadElementActivity extends Activity {
    ArrayList<TwoStringCardView> badElementArr;
    RecyclerView badRecyclerView;
    Intent getReceiveData;
    ImageView backBtn;
    int number=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explanation_bad_element);
        backBtn=findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //서버와 연동할때 주석을 푸시오.
//        getReceiveData = getIntent();
//        String badData = getReceiveData.getStringExtra("badElement");
//        JsonArray badElementJsonArray = dataToJsonArray(badData);
        badRecyclerView = findViewById(R.id.expl_bad_element_recyclerview);
        badRecyclerView.setHasFixedSize(true);
        badRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //서버와 연동할때 밑에 세줄을 지우시오
        badElementArr = new ArrayList<>();
//        badElementArr.add(new TwoStringCardView(String.valueOf(number++), "내 사약 같은 맛"));
//        badElementArr.add(new TwoStringCardView(String.valueOf(number++), "파라디클로로벤젠"));
        badElementArr.add(new TwoStringCardView("toxic1","1"));
        badElementArr.add(new TwoStringCardView("toxic2","3"));
        badElementArr.add(new TwoStringCardView("toxic3","5"));
        badElementArr.add(new TwoStringCardView("toxic4","6"));
        badElementArr.add(new TwoStringCardView("toxic5","7"));
        badElementArr.add(new TwoStringCardView("toxic6","9"));

        badRecyclerView.setAdapter(new TabIngredientListAdapter(badElementArr));
    }

    public JsonArray dataToJsonArray(String data) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(data);
        return element.getAsJsonArray();
    }
    //동적으로 서버와 데이터를 통신할때 주석을 풀어서 테스트 해보시오.... 지나가는 나그네
//
//    public void setData(JsonArray inputData) {
//        for (int i = 0; i < inputData.size(); i++) {
//            JsonObject oneData = (JsonObject) inputData.get(i);
//            badElementArr.add(new TwoStringCardView(oneData.get("id").getAsString(), oneData.get("estimate").getAsString()));
// badRecyclerView.setAdapter(new ExplanationBadElementAdapter(badElementArr));
// }
//    }

}
