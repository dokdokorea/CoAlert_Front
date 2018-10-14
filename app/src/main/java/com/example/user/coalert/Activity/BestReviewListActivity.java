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

        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu1, R.drawable.hyoshin1, "마미손", "이 만화에 주인공은"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu2, R.drawable.hyoshin2,"마미손2", "절대 죽지않아"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu3jpg,R.drawable.hyoshin3, "마미손3", "계획대로되고있어"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu4, R.drawable.hyoshin4,"마미손4", "OK계획대로되고있어"));
        BestReviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu5, R.drawable.hyoshin5,"마미손5", "소년점프 와다다다다다"));


        recyclerView.setAdapter(new DetailReviewAdapter(getApplicationContext(),BestReviewArr,R.layout.item_detail_review));

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


