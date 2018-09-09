package com.example.user.coalert;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;

import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.user.coalert.Fragment.HomeFragment;
import com.example.user.coalert.Fragment.SearchFragment;
import com.example.user.coalert.Fragment.TimeLineFragment;


public class MainActivity extends AppCompatActivity {
    public static final int SERACHID = 0;
    public static final int HOMEID = 1;
    public static final int TIMELINEID = 2;
    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;
    @SuppressLint({"ResourceAsColor", "CommitTransaction"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem searchItem = new AHBottomNavigationItem(R.string.search, android.R.drawable.ic_menu_search, R.color.whiteBackground);
        AHBottomNavigationItem homeItem = new AHBottomNavigationItem(R.string.home, R.drawable.home, R.color.whiteBackground);
        AHBottomNavigationItem timeLineItem = new AHBottomNavigationItem(R.string.timeline, android.R.drawable.ic_menu_upload_you_tube, R.color.whiteBackground);

        bottomNavigation.addItem(searchItem);
        bottomNavigation.addItem(homeItem);
        bottomNavigation.addItem(timeLineItem);


        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setAccentColor(Color.parseColor("#51032d"));
        bottomNavigation.setInactiveColor(R.color.bottom_navigation_select);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_fragment_layout, new HomeFragment());
        fragmentTransaction.commit();
        bottomNavigation.setCurrentItem(1);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                if (position == 0 ){
                    fragmentTransaction.replace(R.id.content_fragment_layout, new SearchFragment());
                }else if (position == 1){
                    fragmentTransaction.replace(R.id.content_fragment_layout, new HomeFragment());
                } else if (position == 2){
                    fragmentTransaction.replace(R.id.content_fragment_layout, new TimeLineFragment());
                }
                fragmentTransaction.commit();
                return true;
            }
        });

    }
}
