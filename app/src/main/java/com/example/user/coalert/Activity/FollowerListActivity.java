package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.FollowerListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class FollowerListActivity extends AppCompatActivity {
    ArrayList<TwoImgTwoStringCardView> follwerListArr;
ImageView profilepic;
ImageView followerBtn;
TextView userName;
TextView userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.follower_list);

        profilepic=(ImageView)findViewById(R.id.item_follower_list_circleimageview);
        followerBtn=(ImageView)findViewById(R.id.item_follower_list_follower_btn);
        userName=(TextView)findViewById(R.id.user_name);
        userId=(TextView)findViewById(R.id.item_follower_email);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.follower_list_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        follwerListArr=new ArrayList<>();
        follwerListArr.add(new TwoImgTwoStringCardView(R.drawable.face1,R.drawable.follow_btn,"이슬기","nunasarang@naver.com"));
        follwerListArr.add(new TwoImgTwoStringCardView(R.drawable.face1,R.drawable.follow_btn,"이슬기","nunasarang@naver.com"));
        follwerListArr.add(new TwoImgTwoStringCardView(R.drawable.face1,R.drawable.follow_btn,"이슬기","nunasarang@naver.com"));
        follwerListArr.add(new TwoImgTwoStringCardView(R.drawable.face1,R.drawable.follow_btn,"이슬기","nunasarang@naver.com"));
        follwerListArr.add(new TwoImgTwoStringCardView(R.drawable.face1,R.drawable.follow_btn,"이슬기","nunasarang@naver.com"));

        recyclerView.setAdapter(new FollowerListAdapter(follwerListArr));

    }
}
