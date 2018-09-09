package com.example.user.coalert.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.user.coalert.MainActivity;
import com.example.user.coalert.OnSwipeTouchListener;
import com.example.user.coalert.R;

import java.util.Objects;

public class HomeFragment extends Fragment {
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    private MenuItem prevBottomNavigation;
    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
