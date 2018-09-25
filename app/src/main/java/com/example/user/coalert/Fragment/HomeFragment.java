package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.coalert.Adapter.BestReviewAdapter;
import com.example.user.coalert.Adapter.HotYoutuberAdapter;
import com.example.user.coalert.Adapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.NewProductAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.BestReviewCardView;
import com.example.user.coalert.item.HotYoutuberCardView;
import com.example.user.coalert.item.NewProductCardView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {
    ArrayList<HotYoutuberCardView> youtuberArr;
    ArrayList<BestReviewCardView> bestReviewArr;
    ArrayList<NewProductCardView> newProduArr;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

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

        youtuberRecyclerView.setAdapter(new HotYoutuberAdapter(new ArrayList<HotYoutuberCardView>()));
        youtuberArr.add(new HotYoutuberCardView(R.drawable.cardview1, "슬기짱!"));
        youtuberArr.add(new HotYoutuberCardView(R.drawable.cardview2, "슬기님..."));
        youtuberArr.add(new HotYoutuberCardView(R.drawable.cardview3, "사랑해요"));

        bestReviewRecyclerView.setAdapter(new BestReviewAdapter(new ArrayList<BestReviewCardView>()));
        bestReviewArr.add(new BestReviewCardView(R.drawable.cardview1, "uniqueBest", "내가 짱인디.."));
        bestReviewArr.add(new BestReviewCardView(R.drawable.cardview2, "pleaseee", "슬기님..."));
        bestReviewArr.add(new BestReviewCardView(R.drawable.cardview3, "badbot", "사랑해요"));

        newProductRecyclerView.setAdapter(new NewProductAdapter(new ArrayList<NewProductCardView>()));
        newProduArr.add(new NewProductCardView(R.drawable.cardview1, "신상품", "내회사"));
        newProduArr.add(new NewProductCardView(R.drawable.cardview2, "신상품", "니회사"));
        newProduArr.add(new NewProductCardView(R.drawable.cardview3, "신상품", "우리회사"));

        return v;
    }
}
