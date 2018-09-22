package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.user.coalert.Adapter.MypageFollowerAdapter;
import com.example.user.coalert.Adapter.MypageRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.CosmeticList_mypage;

import java.util.ArrayList;
import java.util.List;

public class MyprofileActivity extends AppCompatActivity{
    List<String> list;
    Button Edit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        Edit=(Button)findViewById(R.id.edit_personal_info);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView cosmeticList=(RecyclerView)findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        int ColumNumber=3;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        cosmeticList.setHasFixedSize(true);
        cosmeticList.setLayoutManager(new GridLayoutManager(this, ColumNumber));
        cosmeticList.setNestedScrollingEnabled(false);


        List<CosmeticList_mypage> items=new ArrayList<>();
        CosmeticList_mypage[] item=new CosmeticList_mypage[5];
        item[0]=new CosmeticList_mypage(R.drawable.cardview1,"슬기1");
        item[1]=new CosmeticList_mypage(R.drawable.cardview2,"슬기2");
        item[2]=new CosmeticList_mypage(R.drawable.cardview3,"슬기3");
        item[3]=new CosmeticList_mypage(R.drawable.cardview4,"슬기4");
        item[4]=new CosmeticList_mypage(R.drawable.cardview5,"슬기5");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new MypageFollowerAdapter(getApplicationContext(),items,R.layout.activity_mypage));

        List<CosmeticList_mypage> cositems=new ArrayList<>();
        CosmeticList_mypage[] cositem=new CosmeticList_mypage[5];
        item[0]=new CosmeticList_mypage(R.drawable.cardview1,"슬기1");
        item[1]=new CosmeticList_mypage(R.drawable.cardview2,"슬기2");
        item[2]=new CosmeticList_mypage(R.drawable.cardview3,"슬기3");
        item[3]=new CosmeticList_mypage(R.drawable.cardview4,"슬기4");
        item[4]=new CosmeticList_mypage(R.drawable.cardview5,"슬기5");

        for(int i=0;i<5;i++) cositems.add(cositem[i]);
        cosmeticList.setAdapter(new MypageRecyclerViewAdapter(getApplicationContext(),items,R.layout.activity_mypage));

    }

}


