package com.example.user.coalert.Activity;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.coalert.Adapter.MypageRecyclerViewAdapter;
import com.example.user.coalert.Adapter.TimelineRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.CosmeticList_mypage;
import com.example.user.coalert.item.TimelineCardVIew;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity{
    List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<CosmeticList_mypage> items=new ArrayList<>();
        CosmeticList_mypage[] item=new CosmeticList_mypage[5];
        item[0]=new CosmeticList_mypage(R.drawable.cardview1,"슬기1");
        item[1]=new CosmeticList_mypage(R.drawable.cardview2,"슬기2");
        item[2]=new CosmeticList_mypage(R.drawable.cardview3,"슬기3");
        item[3]=new CosmeticList_mypage(R.drawable.cardview4,"슬기4");
        item[4]=new CosmeticList_mypage(R.drawable.cardview5,"슬기5");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new MypageRecyclerViewAdapter(getApplicationContext(),items,R.layout.activity_mypage));


    }
}


