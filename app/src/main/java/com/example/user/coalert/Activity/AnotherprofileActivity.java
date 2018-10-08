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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.ArrayList;
import java.util.List;

public class AnotherprofileActivity extends AppCompatActivity {
    List<String> list;
    Button follow;
    ImageButton Toxic;
    ImageButton following;
    ImageButton backBtn;
    int j = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotherprofile);
        backBtn = findViewById(R.id.anotherprofile_back_btn);
        follow = (Button) findViewById(R.id.follower);
        Toxic = (ImageButton) findViewById(R.id.toxicList);
        following = (ImageButton) findViewById(R.id.following);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView cosmeticList = (RecyclerView) findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        int ColumNumber = 3;      //GridView Column


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        cosmeticList.setHasFixedSize(true);
        cosmeticList.setLayoutManager(new GridLayoutManager(this, ColumNumber));
        cosmeticList.setNestedScrollingEnabled(false);


        List<OneImgOneStringCardView> items = new ArrayList<>();
        OneImgOneStringCardView[] item = new OneImgOneStringCardView[6];
        item[0] = new OneImgOneStringCardView(R.drawable.cardview1, "슬기1");
        item[1] = new OneImgOneStringCardView(R.drawable.cardview2, "슬기2");
        item[2] = new OneImgOneStringCardView(R.drawable.cardview3, "슬기3");
        item[3] = new OneImgOneStringCardView(R.drawable.cardview4, "슬기4");
        item[4] = new OneImgOneStringCardView(R.drawable.cardview5, "슬기5");
        item[5] = new OneImgOneStringCardView(R.drawable.irin, "아이린");


        for (int i = 0; i < 6; i++) items.add(item[i]);

        recyclerView.setAdapter(new MyprofileFollowerAdapter(getApplicationContext(), items, R.layout.activity_anotherprofile));

        List<OneImgOneStringCardView> cositems = new ArrayList<>();
        OneImgOneStringCardView[] cositem = new OneImgOneStringCardView[5];
        cositem[0] = new OneImgOneStringCardView(R.drawable.cardview1, "슬기1");
        cositem[1] = new OneImgOneStringCardView(R.drawable.cardview2, "슬기2");
        cositem[2] = new OneImgOneStringCardView(R.drawable.cardview3, "슬기3");
        cositem[3] = new OneImgOneStringCardView(R.drawable.cardview4, "슬기4");
        cositem[4] = new OneImgOneStringCardView(R.drawable.cardview5, "슬기5");

        for (int i = 0; i < 5; i++) cositems.add(cositem[i]);
        cosmeticList.setAdapter(new MyprofileRecyclerViewAdapter(getApplicationContext(), cositems, R.layout.activity_anotherprofile));


        if (j == 0)
            following.setImageResource(R.drawable.emptyheart);
        else
            following.setImageResource(R.drawable.fullheart);

        following.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                j = 1 - j;

                if (j == 0) {
                    following.setImageResource(R.drawable.emptyheart);
                    Toast.makeText(AnotherprofileActivity.this, "팔로우 취소하셨습니다", Toast.LENGTH_SHORT).show();
                } else {
                    following.setImageResource(R.drawable.fullheart);
                    Toast.makeText(AnotherprofileActivity.this, "팔로우하셨습니다", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


