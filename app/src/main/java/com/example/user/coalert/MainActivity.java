package com.example.user.coalert;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;


public class MainActivity extends AppCompatActivity {
    public static final int SERACHID = 0;
    public static final int HOMEID = 1;
    public static final int TIMELINEID = 2;

    @SuppressLint("ResourceAsColor")
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

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0 ){

                }else if (position == 1){

                } else if (position == 2){

                }
                return true;
            }
        });

    }
}
