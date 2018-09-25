package com.example.user.coalert.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.coalert.Adapter.MyprofileAdapter.ExplanationBadElementAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.ExplanationBadElementCardView;

import java.util.ArrayList;

public class ExplanationBadElementActivity extends Activity {
    ArrayList<ExplanationBadElementCardView> badElementArr;
    RecyclerView badRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explanation_bad_element);
        badRecyclerView = (RecyclerView) findViewById(R.id.expl_bad_element_recyclerview);
        badRecyclerView.setHasFixedSize(true);
        badRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        badElementArr = new ArrayList<>();
        badElementArr.add(new ExplanationBadElementCardView("1", "내 사약 같은 맛"));
        badRecyclerView.setAdapter(new ExplanationBadElementAdapter(badElementArr));
    }
}
