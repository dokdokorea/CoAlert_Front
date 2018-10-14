package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.coalert.Adapter.TimelineRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgFourStringCardView;

import java.util.ArrayList;
import java.util.List;

public class TimeLineFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.timeline_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<TwoImgFourStringCardView> list = new ArrayList<>();
        TwoImgFourStringCardView timelineItem[] = new TwoImgFourStringCardView[5];
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        //프로필 이미지, 프리뷰 이미지, 아이디, 제목, 좋아요 갯수, 댓굴 순
        timelineItem[0] = new TwoImgFourStringCardView(R.drawable.cardview1, R.drawable.iu1,"아이유는 아이가 아니예유","레드벨벳 슬기의 화장법 집중 탐구", "100","너무이쁘다ㅇ");
        timelineItem[1] = new TwoImgFourStringCardView(R.drawable.cardview2, R.drawable.iu2,"dlwlrma","티나지 않는 자연스러운 화장 비법", "365","댓글ㄹㄹ");
        timelineItem[2] = new TwoImgFourStringCardView(R.drawable.cardview3, R.drawable.hyoshin5,"hyoshin","눈이 2배 커지는 화장 비법", "30","한번 넣어준다..");
        timelineItem[3] = new TwoImgFourStringCardView(R.drawable.cardview4, R.drawable.hyoshin6,"park","화장 초짜들은 봐라 어딜가도 평타치는 화장법", "700","흩어져~~~날~~~아~~~~");
        timelineItem[4] = new TwoImgFourStringCardView(R.drawable.cardview5, R.drawable.irinblack,"irinLove","내 블랙은 도 도 해","999999", "아이린 존예에");
        for (int i = 0; i<5;i++) list.add(timelineItem[i]);
        recyclerView.setAdapter(new TimelineRecyclerViewAdapter(getContext(), list, R.layout.fragment_timeline));
        return v;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
