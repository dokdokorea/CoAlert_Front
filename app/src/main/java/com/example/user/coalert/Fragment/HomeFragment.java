package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

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

        youtuberArr.add(new OneImgOneStringCardView(R.drawable.url1, "심즈 캐릭터같은 메이크업!"));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.url2, "숨막히는 메이크업 with 엠제이 mj kim [다린살롱]"));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.url3, "barbie make up"));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.url4, "횡설수설 속눈썹 강좌"));
        youtuberArr.add(new OneImgOneStringCardView(R.drawable.url5, "Nicki Minaj make up / 니키미나즈 메이크업"));

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
                //TODO 서버에 요청을 보내주세요 리스트 형식으로 받은 후 toString 해서 다음 액티비티로 넘겨주세요
                Intent intent = new Intent(getActivity(), ExplanationBadElementActivity.class);
//                Call<List<BadElementModel>> call = ForRestSingleton.getInstance().badElement();
//                List<BadElement> result =  call.execute().body();
//                intent.putExtra("badElement", result.toString());
                startActivity(intent);
            }
        });

        return v;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}