package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.coalert.Adapter.TimelineRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TimelineCardVIew;

import java.util.ArrayList;
import java.util.List;

public class TimeLineFragment extends Fragment {
    List<String> list;
    public TimeLineFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.timeline_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<TimelineCardVIew> list = new ArrayList<>();
        TimelineCardVIew timelineItem[] = new TimelineCardVIew[5];
        timelineItem[0] = new TimelineCardVIew(R.drawable.cardview1, "레드벨벳 슬기의 화장법 집중 탐구", "슬기");
        timelineItem[1] = new TimelineCardVIew(R.drawable.cardview2, "티나지 않는 자연스러운 화장 비법", "슬기");
        timelineItem[2] = new TimelineCardVIew(R.drawable.cardview3, "눈이 2배 커지는 화장 비법", "슬기");
        timelineItem[3] = new TimelineCardVIew(R.drawable.cardview4, "화자 초짜들은 봐라 어딜가도 평타치는 화장법", "슬기");
        timelineItem[4] = new TimelineCardVIew(R.drawable.cardview5, "미국 화장법 집중 탐구", "슬기");
        for (int i = 0; i<5;i++) list.add(timelineItem[i]);
        recyclerView.setAdapter(new TimelineRecyclerViewAdapter(getContext(), list, R.layout.fragment_timeline));
        return v;
    }

}
