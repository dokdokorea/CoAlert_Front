package com.example.user.coalert.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.user.coalert.Adapter.cosmetic_information_tab.TabPageAdapter;
import com.example.user.coalert.R;

public class CosmeticIngredientActivity extends AppCompatActivity {
    TabHost tabHost1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_ingredient_acitivty);
        //"Tab Spec" 태그(Tag)를 가진 TapSpec 객체 생성.
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


    }
}
