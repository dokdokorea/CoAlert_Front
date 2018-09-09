package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.coalert.OnSwipeTouchListener;
import com.example.user.coalert.R;

public class HomeFragment extends Fragment {
    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.setOnTouchListener(new OnSwipeTouchListener(getContext()){
            public void onSwipeTop() {
                Log.e("swipe: ", "top");
            }
            public void onSwipeRight() {
                Log.e("swipe: ", "Right");
            }
            public void onSwipeLeft() {
                Log.e("swipe: ", "Left");
            }
            public void onSwipeBottom() {
                Log.e("swipe: ","Bottom");
            }
        });
        return view;
    }
}
