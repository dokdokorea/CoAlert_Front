package com.example.user.coalert.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.app.FragmentTransaction;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.user.coalert.Fragment.HomeFragment;
import com.example.user.coalert.Fragment.MyProfileFragment;
import com.example.user.coalert.Fragment.SearchFragment;
import com.example.user.coalert.Fragment.TimeLineFragment;
import com.example.user.coalert.R;


public class MainActivity extends AppCompatActivity {
    public static final int SERACHID = 0;
    public static final int HOMEID = 1;
    public static final int TIMELINEID = 2;
    public static final int MYPROFILEID=3;
    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;
    @SuppressLint({"ResourceAsColor", "CommitTransaction"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionCheck();
        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);


        AHBottomNavigationItem searchItem = new AHBottomNavigationItem(R.string.search, android.R.drawable.ic_menu_search, R.color.whiteBackground);
        AHBottomNavigationItem homeItem = new AHBottomNavigationItem(R.string.home, R.drawable.home, R.color.whiteBackground);
        AHBottomNavigationItem timeLineItem = new AHBottomNavigationItem(R.string.timeline, android.R.drawable.ic_menu_upload_you_tube, R.color.whiteBackground);
        AHBottomNavigationItem myprofileItem=new AHBottomNavigationItem(R.string.myprofile,R.drawable.myprofile_icon,R.color.whiteBackground);

        bottomNavigation.addItem(searchItem);
        bottomNavigation.addItem(homeItem);
        bottomNavigation.addItem(timeLineItem);
        bottomNavigation.addItem(myprofileItem);

        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setAccentColor(Color.parseColor("#51032d"));
        bottomNavigation.setInactiveColor(R.color.bottom_navigation_select);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_fragment_layout, new HomeFragment());
        fragmentTransaction.commit();
        bottomNavigation.setNotification("3", 2);
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
                } else if (position == 3){
                    fragmentTransaction.replace(R.id.content_fragment_layout, new MyProfileFragment());
                }
                fragmentTransaction.commit();
                return true;
            }
        });

    }

    void permissionCheck() {
        int ReadStoragetPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int ReadAudioPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int WriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ReadAudioPermmission != PackageManager.PERMISSION_GRANTED && ReadStoragetPermmission != PackageManager.PERMISSION_GRANTED &&
                WriteStorage != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                        1000);
            }
        }
    }
}
