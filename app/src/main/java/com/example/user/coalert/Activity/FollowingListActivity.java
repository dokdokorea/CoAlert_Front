package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.FollowingListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class FollowingListActivity extends AppCompatActivity {
    ArrayList<TwoImgTwoStringCardView> followingArr;
    ImageView profilepic;
    ImageView followingBtn;
    TextView userName;
    TextView userId;
    ImageButton backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_list);
        backBtn = findViewById(R.id.following_list_back_btn);
        profilepic = (ImageView) findViewById(R.id.item_follower_list_circleimageview);
        followingBtn = (ImageView) findViewById(R.id.item_follower_list_follower_btn);
        userName = (TextView) findViewById(R.id.user_name);
        userId = (TextView) findViewById(R.id.item_follower_email);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.following_list_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        followingArr = new ArrayList<>();
        followingArr.add(new TwoImgTwoStringCardView(R.drawable.irin, R.drawable.follow_btn, "irinlove", "irinlove@naver.com"));
        followingArr.add(new TwoImgTwoStringCardView(R.drawable.nayeon1, R.drawable.follow_btn, "twice_nayeon", "tdungtdug@naver.com"));
        followingArr.add(new TwoImgTwoStringCardView(R.drawable.irin3, R.drawable.follow_btn, "pink_gondyu", "vldzmrhdwb@google.com"));
        followingArr.add(new TwoImgTwoStringCardView(R.drawable.hyoshin2, R.drawable.follow_btn, "dokdokorea", "dokdokorea@naver.com"));

        recyclerView.setAdapter(new FollowingListAdapter(followingArr));

    }

}
