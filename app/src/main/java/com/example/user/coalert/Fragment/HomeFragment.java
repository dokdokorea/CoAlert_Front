package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.example.user.coalert.Activity.HotYoutubeListActivity;
import com.example.user.coalert.Activity.NotificationActivity;
import com.example.user.coalert.Activity.WhatSelectKindCosmetic;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.BestReviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.HotYoutuberAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.Adapter.FragmentHomeLinkList.YoutubeListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class HomeFragment extends Fragment {
    ArrayList<OneImgTwoStringCardView> youtuberArr;
    ArrayList<OneImgTwoStringCardView> bestReviewArr;
    ArrayList<OneImgTwoStringCardView> newProduArr;
    ArrayList<TwoImgTwoStringCardView> bestPreviewArr;
    SearchFragment searchFragment;
    FragmentManager fragmentManager;
    FrameLayout detailElementBtn;
    FrameLayout suggestCosmetic;
    ImageButton notification_icon, youtubelnk;

    public HomeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        detailElementBtn = v.findViewById(R.id.detail_element_info_btn);
        suggestCosmetic = v.findViewById(R.id.suggest_cosmetics);
        notification_icon=v.findViewById(R.id.home_notification_icon_btn);
        youtubelnk=v.findViewById(R.id.main_youtube_next_btn);

        youtubelnk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HotYoutubeListActivity.class);
                startActivity(intent);
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        Drawable alpha=notification_icon.getBackground();
        alpha.setAlpha(50);
        Drawable youtubeBtn=(v.findViewById(R.id.main_youtube_next_btn)).getBackground();
        youtubeBtn.setAlpha(50);

        RecyclerView youtuberRecyclerView = (RecyclerView) v.findViewById(R.id.hot_youtuber_recyclerview);
        RecyclerView bestReviewRecyclerView = (RecyclerView) v.findViewById(R.id.best_review_recyclerview);
        RecyclerView newProductRecyclerView = (RecyclerView) v.findViewById(R.id.new_product_recyclerview);
        RecyclerView bestPreviewRecyclerView=(RecyclerView) v.findViewById(R.id.best_review_preview_recycler);

        youtuberRecyclerView.setHasFixedSize(true);
        bestReviewRecyclerView.setHasFixedSize(true);
        newProductRecyclerView.setHasFixedSize(true);
        bestPreviewRecyclerView.setHasFixedSize(true);
        bestPreviewRecyclerView.setFocusable(false);

        youtuberRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bestReviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bestPreviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        youtuberArr = new ArrayList<>();
        bestReviewArr = new ArrayList<>();
        newProduArr = new ArrayList<>();
        bestPreviewArr=new ArrayList<>();

        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "심즈 캐릭터같은 메이크업!","aiHSVQy9xN8"));
        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "눈이 2배 커지는 화장 비법","RDaiHSVQy9xN8"));
        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview4, "화자 초짜들은 봐라 어딜가도 평타치는 화장법","glXgSSOKlls"));
        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview5, "미국 화장법 집중 탐구","J0h8-OTC38I"));

        youtuberRecyclerView.setAdapter(new HotYoutuberAdapter(getContext(),youtuberArr,R.layout.fragment_home));

        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "uniqueBest", "내가 짱인디.."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "pleaseee", "슬기님..."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "badbot", "사랑해요"));
        bestReviewRecyclerView.setAdapter(new BestReviewAdapter(getContext(),bestReviewArr,R.layout.fragment_home));

        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "신상품", "내회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "신상품", "니회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "신상품", "우리회사"));
        newProductRecyclerView.setAdapter(new NewProductAdapter(getContext(),newProduArr,R.layout.fragment_home));

        bestPreviewArr.add(new TwoImgTwoStringCardView(R.drawable.cardview1,R.drawable.irinblack,"슬기","아이린의 화장법"));
        bestPreviewArr.add(new TwoImgTwoStringCardView(R.drawable.irin,R.drawable.irinpink,"아이린","남다른 핑크"));
        bestPreviewArr.add(new TwoImgTwoStringCardView(R.drawable.iu1,R.drawable.irinyellow,"아이린","색다른 옐로우"));

        bestPreviewRecyclerView.setAdapter(new DetailReviewAdapter(getContext(),bestPreviewArr,R.layout.fragment_home));


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