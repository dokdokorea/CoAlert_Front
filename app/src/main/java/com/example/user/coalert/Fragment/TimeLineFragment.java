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

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
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
        AHBottomNavigation bottomNavigation = v.findViewById(R.id.bottom_navigation);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<TwoImgFourStringCardView> list = new ArrayList<>();
        TwoImgFourStringCardView timelineItem[] = new TwoImgFourStringCardView[5];
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        //프로필 이미지, 프리뷰 이미지, 아이디, 제목, 좋아요 갯수, 댓굴 순
        timelineItem[0] = new TwoImgFourStringCardView(R.drawable.cardview1, R.drawable.used_cosmetic1,"dokdokorea","레드벨벨 슬기의 화장품 엿보기", "60","오 이 화장품이 너한테 맞나보네!");
        timelineItem[1] = new TwoImgFourStringCardView(R.drawable.user_gray, R.drawable.used_cosmetic2,"youngwoojjang","한 듯 안 한 듯한 화장법 실현 중..", "105","이거 생각외로 예쁘게 나오더라~");
        timelineItem[2] = new TwoImgFourStringCardView(R.drawable.iu1, R.drawable.used_cosmetic3,"sanghun4love","눈이 2배 커지는 화장 비법 도전하기", "30","그건 갸루상 아니냐 ㅋㅋ");
        timelineItem[3] = new TwoImgFourStringCardView(R.drawable.iu2, R.drawable.used_cosmetic4,"pukyong_love","나에게 꼭 맞는 립 발견!", "34","나한테도 이거 좋데~!!");
        timelineItem[4] = new TwoImgFourStringCardView(R.drawable.iu4, R.drawable.used_cosmetic5,"irinLove","은은한 분위기의 아이셰도우.#데이트#성공적","120", "나도 데이트갈 때 써봐야겠다 ~! \n좋은 정보 고마워");
        for (int i = 0; i<5;i++) list.add(timelineItem[i]);
        recyclerView.setAdapter(new TimelineRecyclerViewAdapter(getContext(), list, R.layout.fragment_timeline));
        return v;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
