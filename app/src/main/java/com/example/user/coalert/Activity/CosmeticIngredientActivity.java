package com.example.user.coalert.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.user.coalert.Adapter.TabIngredListAdapter.DrySkinAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.OilySkinAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.SensitiveSkinAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.TabIngredientListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.ArrayList;

public class CosmeticIngredientActivity extends AppCompatActivity {
    RecyclerView tabIngredientListRecyclerView, oilySkinRecyclerView,drySkinRecyclerView, sensitiveRecyclerView;
    ArrayList<OneImgOneStringCardView> tabIngArr, oilArr, dryArr, sensitiveArr;
    TabHost tabHost1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_ingredient);

        tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성분");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("피부타입별");
        tabHost1.addTab(ts2);

        tabIngredientListRecyclerView = (RecyclerView) findViewById(R.id.tab_ingredient_list_recyclerview);
        oilySkinRecyclerView = (RecyclerView) findViewById(R.id.oily_skin_recycler_view);
        drySkinRecyclerView = (RecyclerView) findViewById(R.id.dry_skin_recycler_view);
        sensitiveRecyclerView = (RecyclerView) findViewById(R.id.sentitive_skin_recycler_view);

        tabIngredientListRecyclerView.setHasFixedSize(true);
        oilySkinRecyclerView.setHasFixedSize(true);
        drySkinRecyclerView.setHasFixedSize(true);
        sensitiveRecyclerView.setHasFixedSize(true);

        tabIngredientListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        oilySkinRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        drySkinRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        sensitiveRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        tabIngArr = new ArrayList<>();
        oilArr = new ArrayList<>();
        dryArr = new ArrayList<>();
        sensitiveArr = new ArrayList<>();


        tabIngArr.add(new OneImgOneStringCardView(R.drawable.cardview1, "모두에게 행복을 주시길 바랍니다. 아아메엔 할렐루야아아아"));
        tabIngredientListRecyclerView.setAdapter(new TabIngredientListAdapter(tabIngArr));

        oilArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "당신에게 행복을"));
        oilySkinRecyclerView.setAdapter(new OilySkinAdapter(oilArr));

        dryArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "당신에게 행복을"));
        drySkinRecyclerView.setAdapter(new DrySkinAdapter(dryArr));

        sensitiveArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "당신에게 행복을"));
        sensitiveRecyclerView.setAdapter(new SensitiveSkinAdapter(sensitiveArr));
    }
}
