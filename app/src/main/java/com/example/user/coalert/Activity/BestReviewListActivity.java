package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeLinkList.YoutubeListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class BestReviewListActivity extends AppCompatActivity {
   ArrayList<TwoImgTwoStringCardView> BestReviewArr;
    Button follow;
    ImageButton Toxic;
    ImageButton following;
    ImageView backBtn;
    int j = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_review_list);

        backBtn=(ImageView)findViewById(R.id.back_btn);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.best_review_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        BestReviewArr = new ArrayList<>();

        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu1, R.drawable.irinblack, "dlwlrma", "아이린의 화장법"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.hyoshin2, R.drawable.irinpink,"dokdokorea", "남다른 핑크매력 발산법"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.irin,R.drawable.iu1, "irinlove", "아이유 메이크업"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.irin2, R.drawable.nayeon1,"dl57934", "민감성 피부여 일어나라!!"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.irin3, R.drawable.irin4,"kunk", "완전 어려보이는 동안메이크업"));


        recyclerView.setAdapter(new DetailReviewAdapter(getApplicationContext(),BestReviewArr,R.layout.item_detail_review));

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


