package com.example.user.coalert.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.coalert.Adapter.MyprofileAdapter.ExplanationBadElementAdapter;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explanation_bad_element);
        //서버와 연동할때 주석을 푸시오.
//        getReceiveData = getIntent();
//        String badData = getReceiveData.getStringExtra("badElement");
//        JsonArray badElementJsonArray = dataToJsonArray(badData);
        badRecyclerView = findViewById(R.id.expl_bad_element_recyclerview);
        badRecyclerView.setHasFixedSize(true);
        badRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        badElementArr = new ArrayList<>();
        badElementArr.add(new TwoStringCardView("1", "내 사약 같은 맛"));
        badRecyclerView.setAdapter(new ExplanationBadElementAdapter(badElementArr));
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
//        }
//    }

}
