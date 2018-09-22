package com.example.user.coalert.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.coalert.Adapter.NotificationAdapter;
import com.example.user.coalert.R;

import java.util.ArrayList;

public class NotificationActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        mRecyclerView=(RecyclerView)findViewById(R.id.notification_recycler_view);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset=new ArrayList<>();
        /* ArrayList의 타입 정의는?
        mAdapter=new NotificationAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData("#InsideOut",R.drawable.cardview1));
        myDataset.add(new MyData("#InsideOut",R.drawable.cardview2));*/
    }
}
class MyData{
    private String text;
    private int img;
}