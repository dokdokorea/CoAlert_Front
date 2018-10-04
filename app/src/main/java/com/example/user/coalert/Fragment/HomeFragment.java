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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.coalert.Activity.ExplanationBadElementActivity;
import com.example.user.coalert.Activity.WhatSelectKindCosmetic;
import com.example.user.coalert.Adapter.CategoryAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.BestReviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.HotYoutuberAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImageCardView;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;
import com.example.user.coalert.Adapter.kindCosmeticAdapter;
import com.example.user.coalert.item.OneIntImageCardView;

import java.util.ArrayList;

import retrofit2.http.HEAD;

public class HomeFragment extends Fragment {
    kindCosmeticAdapter kindCosmeticAdapter;
    ArrayList<OneIntImageCardView> arrList;
    ArrayList<OneImgOneStringCardView> youtuberArr;
    ArrayList<OneImgTwoStringCardView> bestReviewArr;
    ArrayList<OneImgTwoStringCardView> newProduArr;
    SearchFragment searchFragment;
    FragmentManager fragmentManager;
    private TextView detailElementBtn;
    private ImageView searchIcon;


    public HomeFragment() {
    }

    Button suggestCosmetic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        kindCosmeticAdapter = new kindCosmeticAdapter();
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        arrList = new ArrayList<>();
        detailElementBtn = (TextView) v.findViewById(R.id.detail_element_info_btn);
        searchIcon = (ImageView) v.findViewById(R.id.fragment_home_search_btn);

        searchIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchFragment.class);
                startActivity(intent);
                detailElementBtn = v.findViewById(R.id.detail_element_info_btn);
                searchIcon = v.findViewById(R.id.fragment_home_search_btn);
                searchFragment = new SearchFragment();
                fragmentManager = getFragmentManager();
                searchIcon.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_fragment_layout, searchFragment);
                        fragmentTransaction.commit();
                    }
                });
            }
        });

        RecyclerView categoryRecyclerView = (RecyclerView) v.findViewById(R.id.home_category_recyclerview);
        suggestCosmetic = v.findViewById(R.id.suggest_cosmetics);
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

        arrList.add(new OneIntImageCardView(R.drawable.iu1));
        arrList.add(new OneIntImageCardView(R.drawable.iu2));
        arrList.add(new OneIntImageCardView(R.drawable.iu3jpg));
        arrList.add(new OneIntImageCardView(R.drawable.iu4));
        categoryRecyclerView.setAdapter(new CategoryAdapter(arrList));

        youtuberArr.add(new OneImgOneStringCardView(R.drawable.cardview1, "슬기짱!"));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "슬기님..."));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.cardview3, "사랑해요"));
        youtuberRecyclerView.setAdapter(new HotYoutuberAdapter(youtuberArr));

        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "uniqueBest", "내가 짱인디.."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "pleaseee", "슬기님..."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "badbot", "사랑해요"));
        bestReviewRecyclerView.setAdapter(new BestReviewAdapter(bestReviewArr));

        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "신상품", "내회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "신상품", "니회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "신상품", "우리회사"));
        newProductRecyclerView.setAdapter(new NewProductAdapter(newProduArr));

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
