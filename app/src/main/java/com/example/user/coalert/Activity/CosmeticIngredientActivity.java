package com.example.user.coalert.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;

import com.example.user.coalert.Adapter.TabIngredientListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TabIngredientListCardView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CosmeticIngredientActivity extends AppCompatActivity {
    RecyclerView tabIngredientListRecyclerView;
    ArrayList<TabIngredientListCardView> tabIngArr;
    TabHost tabHost1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_ingredient);

        tabHost1=(TabHost)findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1=tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성분");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2=tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("피부타입");
        tabHost1.addTab(ts2);

        tabIngredientListRecyclerView=(RecyclerView)findViewById(R.id.tab_ingredient_list_recyclerview);
        tabIngredientListRecyclerView.setHasFixedSize(true);
        tabIngredientListRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        tabIngArr=new ArrayList<>();
        tabIngArr.add(new TabIngredientListCardView(R.drawable.cardview1,"모두에게 행복을"));
        tabIngredientListRecyclerView.setAdapter(new TabIngredientListAdapter(tabIngArr));

    }
}
