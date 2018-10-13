package com.example.user.coalert.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.FragmentHomeLinkList.YoutubeListAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;
import java.util.List;

public class HotYoutubeListActivity extends AppCompatActivity {
   ArrayList<TwoImgFourStringCardView> YoutubeArr;
    Button follow;
    ImageButton Toxic;
    ImageButton following;
    ImageView backBtn;
    int j = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_youtube_list);

        backBtn=(ImageView)findViewById(R.id.back_btn);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hot_youtuber_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        YoutubeArr = new ArrayList<>();

        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube1, R.drawable.face1, "숨막히는 메이크업", "엠제이","21k","AeKeOPxfc4M"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube2, R.drawable.iu1,"심즈 캐릭터같은 메이크업", "아이디","30k","lQh6bqJgxYI"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube3,R.drawable.iu2, "barbie makeup", "계획대로되고있어","5k","ET8vUaQxzYg"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube4, R.drawable.iu4,"횡설수설 속눈썹 강좌", "OK계획대로되고있어","30","TU8eTSERiSY"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube5, R.drawable.hyoshin1,"Nicki Minaj makeup", "아이디","5","0EndSryRh-s"));


        recyclerView.setAdapter(new YoutubeListAdapter(getApplicationContext(),YoutubeArr,R.layout.item_hot_youtube_list));




        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


