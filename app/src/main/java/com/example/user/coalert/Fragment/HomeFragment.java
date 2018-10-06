package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Activity.ExplanationBadElementActivity;
import com.example.user.coalert.Activity.NotificationActivity;
import com.example.user.coalert.Activity.WhatSelectKindCosmetic;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.BestReviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.HotYoutuberAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<OneImgOneStringCardView> youtuberArr;
    ArrayList<OneImgTwoStringCardView> bestReviewArr;
    ArrayList<OneImgTwoStringCardView> newProduArr;
    SearchFragment searchFragment;
    FragmentManager fragmentManager;
    FrameLayout detailElementBtn;
    FrameLayout suggestCosmetic;
    ImageButton notification_icon;

    public HomeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        detailElementBtn = v.findViewById(R.id.detail_element_info_btn);
        suggestCosmetic = v.findViewById(R.id.suggest_cosmetics);
        notification_icon=v.findViewById(R.id.home_notification_icon_btn);

        notification_icon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView youtuberRecyclerView = (RecyclerView) v.findViewById(R.id.hot_youtuber_recyclerview);
        RecyclerView bestReviewRecyclerView = (RecyclerView) v.findViewById(R.id.best_review_recyclerview);
        RecyclerView newProductRecyclerView = (RecyclerView) v.findViewById(R.id.new_product_recyclerview);

        youtuberRecyclerView.setHasFixedSize(true);
        bestReviewRecyclerView.setHasFixedSize(true);
        newProductRecyclerView.setHasFixedSize(true);

        youtuberRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bestReviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        youtuberArr = new ArrayList<>();
        bestReviewArr = new ArrayList<>();
        newProduArr = new ArrayList<>();

        youtuberArr.add(new OneImgOneStringCardView(R.drawable.cardview1, "슬기짱!"));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "슬기님..."));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.cardview3, "사랑해요"));
        youtuberRecyclerView.setAdapter(new HotYoutuberAdapter(getContext(),youtuberArr,R.layout.fragment_home));

        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "uniqueBest", "내가 짱인디.."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "pleaseee", "슬기님..."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "badbot", "사랑해요"));
        bestReviewRecyclerView.setAdapter(new BestReviewAdapter(getContext(),bestReviewArr,R.layout.fragment_home));

        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "신상품", "내회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "신상품", "니회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "신상품", "우리회사"));
        newProductRecyclerView.setAdapter(new NewProductAdapter(getContext(),newProduArr,R.layout.fragment_home));

        suggestCosmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kindSelectCosmeticActivity = new Intent(getActivity(), WhatSelectKindCosmetic.class);
                startActivity(kindSelectCosmeticActivity);
            }
        });

        detailElementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExplanationBadElementActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}