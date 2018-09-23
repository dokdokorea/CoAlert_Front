package com.example.user.coalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.coalert.Adapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileRecyclerViewAdapter;
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

        recyclerView.setAdapter(new MyprofileFollowerAdapter(getApplicationContext(),items,R.layout.activity_myprofile));

        List<CosmeticList_mypage> cositems=new ArrayList<>();
        CosmeticList_mypage[] cositem=new CosmeticList_mypage[5];
        item[0]=new CosmeticList_mypage(R.drawable.cardview1,"슬기1");
        item[1]=new CosmeticList_mypage(R.drawable.cardview2,"슬기2");
        item[2]=new CosmeticList_mypage(R.drawable.cardview3,"슬기3");
        item[3]=new CosmeticList_mypage(R.drawable.cardview4,"슬기4");
        item[4]=new CosmeticList_mypage(R.drawable.cardview5,"슬기5");

        for(int i=0;i<5;i++) cositems.add(cositem[i]);
        cosmeticList.setAdapter(new MyprofileRecyclerViewAdapter(getApplicationContext(),items,R.layout.activity_myprofile));


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyprofileActivity.this,EditprofileActivity.class);
                startActivity(intent);
            }
        });

    }

}


