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

        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube1, R.drawable.face1, "Glowy Coral Makeup (With Subs) 촉촉 코랄 메이크업", "PONY Syndrome","51.7k","DAu_jF-9AXs"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube2, R.drawable.iu1,"[Eng] 왕초보 기본템 데일리 메이크업! l 이사배(Risabae Makeup)", "RISABAE","5.4k","TSjVsxsQTes"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube3,R.drawable.iu2, "BOLD RED LIP MAKEUP\uD83D\uDC8B(With subs) 볼드 레드 립 메이크업", "PONY Syndrome","19.7k","p7BXc7GPsYA"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube4, R.drawable.iu4,"최애템으로 인생 데일리 메이크업! 같이 준비해요 -Get Ready With Me | 다또아", "다또아Daddoa","59k","MrRWpADHifw"));
        YoutubeArr.add(new TwoImgFourStringCardView(R.drawable.youtube5, R.drawable.hyoshin1,"이 영상을 보면 '베이스 천재'가 될 수 있습니다. #유형별 베이스법 #피부가 좋아보이는 베이스 표현법 | LAMUQE", "lamuqe","12.8k","vMx7ww59Le4-s"));


        recyclerView.setAdapter(new YoutubeListAdapter(getApplicationContext(),YoutubeArr,R.layout.item_hot_youtube_list));




        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


