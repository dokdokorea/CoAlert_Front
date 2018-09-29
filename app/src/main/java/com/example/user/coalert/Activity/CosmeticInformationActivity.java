package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;

import com.example.user.coalert.Adapter.TabIngredListAdapter.TabIngredientListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.ArrayList;

public class CosmeticInformationActivity extends AppCompatActivity{
    TabHost tabHost1;
    RecyclerView ingredient;
    ArrayList<OneImgOneStringCardView> IngArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosmetic_information);

        tabHost1=(TabHost)findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1=tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성분");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2=tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("simple review");
        tabHost1.addTab(ts2);

        TabHost.TabSpec ts3=tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("Detail review");
        tabHost1.addTab(ts3);

        ingredient=(RecyclerView)findViewById(R.id.tab_ingredient_list_recyclerview);
        ingredient.setHasFixedSize(true);
        ingredient.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        IngArr=new ArrayList<>();
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic1"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic2"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic3"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic4"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic5"));
        ingredient.setAdapter(new TabIngredientListAdapter(IngArr));
    }
}
