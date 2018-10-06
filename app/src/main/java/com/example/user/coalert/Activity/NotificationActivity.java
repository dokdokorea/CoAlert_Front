package com.example.user.coalert.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.user.coalert.Adapter.NotificationAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class NotificationActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<OneImgTwoStringCardView> myDataset;
    private ImageButton backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        backBtn = findViewById(R.id.notification_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.notification_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();

        mAdapter = new NotificationAdapter(getBaseContext(),myDataset,R.layout.notification);

        myDataset.add(new OneImgTwoStringCardView(R.drawable.cardview1, "#InsideOut","어디에어디에"));
        myDataset.add(new OneImgTwoStringCardView(R.drawable.cardview2, "Catch me if you can","나는야나는야"));
        mRecyclerView.setAdapter(mAdapter);
    }
}