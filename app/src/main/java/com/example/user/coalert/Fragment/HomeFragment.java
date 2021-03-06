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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.coalert.Activity.BestReviewListActivity;
import com.example.user.coalert.Activity.ExplanationBadElementActivity;
import com.example.user.coalert.Activity.HotYoutubeListActivity;
import com.example.user.coalert.Activity.NewProductListActivity;
import com.example.user.coalert.Activity.NotificationActivity;
import com.example.user.coalert.Activity.WhatSelectKindCosmetic;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewAdapter;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewPreviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.BestReviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.BestReviewPreviewAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.HotYoutuberAdapter;
import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.Adapter.FragmentHomeLinkList.YoutubeListAdapter;
import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.GetBadIngredientModel;
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
    ArrayList<OneImgOneStringCardView> bestPreviewArr;
    SearchFragment searchFragment;
    FragmentManager fragmentManager;
    FrameLayout detailElementBtn;
    FrameLayout suggestCosmetic;
    ImageButton notification_icon, youtubelnk;
    ImageView toxicback,recommendback;
    RelativeLayout goYoutube,goNewProduct;
    LinearLayout goHotReview;

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
        goYoutube=v.findViewById(R.id.goYoutube);
        goNewProduct=v.findViewById(R.id.goNewProduct);
        goHotReview=v.findViewById(R.id.goHotReview);
        toxicback=v.findViewById(R.id.my_toxic_background);
        recommendback=v.findViewById(R.id.recommend_background);

        goHotReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), BestReviewListActivity.class);
                startActivity(intent);
            }
        });

        goYoutube.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HotYoutubeListActivity.class);
                startActivity(intent);
            }
        });

        goNewProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NewProductListActivity.class);
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
        //Drawable youtubeBtn=(v.findViewById(R.id.main_youtube_next_btn)).getBackground();
        //youtubeBtn.setAlpha(50);


        RecyclerView bestReviewRecyclerView = (RecyclerView) v.findViewById(R.id.best_review_recyclerview);
        RecyclerView bestPreviewRecyclerView=(RecyclerView) v.findViewById(R.id.best_review_preview_recycler);


        bestReviewRecyclerView.setHasFixedSize(true);
        bestPreviewRecyclerView.setHasFixedSize(true);
        bestPreviewRecyclerView.setFocusable(false);


        bestReviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bestPreviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        youtuberArr = new ArrayList<>();
        bestReviewArr = new ArrayList<>();
        newProduArr = new ArrayList<>();
        bestPreviewArr=new ArrayList<>();

        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "심즈 캐릭터같은 메이크업!","aiHSVQy9xN8"));
        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "눈이 2배 커지는 화장 비법","RDaiHSVQy9xN8"));
        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview4, "화자 초짜들은 봐라 어딜가도 평타치는 화장법","glXgSSOKlls"));
        youtuberArr.add(new OneImgTwoStringCardView(R.drawable.cardview5, "미국 화장법 집중 탐구","J0h8-OTC38I"));

        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "uniqueBest", "내가 짱인디.."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "pleaseee", "슬기님..."));
        bestReviewArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "badbot", "사랑해요"));
        bestReviewRecyclerView.setNestedScrollingEnabled(false);
        bestReviewRecyclerView.setAdapter(new BestReviewAdapter(getContext(),bestReviewArr,R.layout.fragment_home));

        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview1, "신상품", "내회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview2, "신상품", "니회사"));
        newProduArr.add(new OneImgTwoStringCardView(R.drawable.cardview3, "신상품", "우리회사"));

        bestPreviewArr.add(new OneImgOneStringCardView(R.drawable.irinblack,"아이린의 화장법"));
        bestPreviewArr.add(new OneImgOneStringCardView(R.drawable.irinpink,"남다른 핑크매력 발산법"));
        bestPreviewArr.add(new OneImgOneStringCardView(R.drawable.iu1,"아이유 메이크업"));
        bestPreviewRecyclerView.setNestedScrollingEnabled(false);
        bestPreviewRecyclerView.setAdapter(new BestReviewPreviewAdapter(getContext(),bestPreviewArr,R.layout.fragment_home));


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
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Log.e("hihi", "asd");
                            //TODO 서버에 요청을 보내주세요 리스트 형식으로 받은 후 toString 해서 다음 액티비티로 넘겨주세요
                            final Intent intent = new Intent(getActivity(), ExplanationBadElementActivity.class);
                            Call<List<GetBadIngredientModel>> call = ForRestSingleton.getInstance().getBadIngredient("0", "지성");
                            Thread.sleep(10000);
                            List<GetBadIngredientModel> result = call.execute().body();
                            intent.putExtra("badElement", result.toString());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
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