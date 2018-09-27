package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.user.coalert.Adapter.CosmeticInformationAdapter.ingredientAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.IngredientList;
import com.example.user.coalert.item.SimpleReviewList;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CosmeticInformationActivity extends AppCompatActivity{
    TabHost tabHost1;
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosmetic_information);
        tv=findViewById(R.id.text);

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

        tv.setText("HEllo");
        ListView inglist=(ListView)findViewById(R.id.ingredient_list);
        ArrayList<IngredientList> data=new ArrayList<>();
        data.add(new IngredientList(3,"toxic1"));
        data.add(new IngredientList(5.2,"toxic2"));
        data.add(new IngredientList(4.3,"toxic3"));
        inglist.setAdapter(new ingredientAdapter(this,R.layout.item_cosmetic_information_ingredients,data));
    }
}
