//package com.example.user.coalert.Fragment;
//
//import android.annotation.SuppressLint;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.BottomNavigationView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.example.user.coalert.MainActivity;
//import com.example.user.coalert.OnSwipeTouchListener;
//import com.example.user.coalert.R;
//
//import java.util.Objects;
//
//public class HomeFragment extends Fragment {
//    Fragment fragment;
//    BottomNavigationView bottomNavigationView;
//    private MenuItem prevBottomNavigation;
//    public HomeFragment(){
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        view.setOnTouchListener(new OnSwipeTouchListener(getContext()){
//            public void onSwipeRight() {
//                fragment = new SearchFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_fragment_layout, fragment);
//                ft.commit();
//                bottomNavigationView = (BottomNavigationView)getView().findViewById(R.id.bottom_navigation);
//                prevBottomNavigation = bottomNavigationView.getMenu().getItem(MainActivity.HOMEID);
//                prevBottomNavigation.setChecked(false);
//                prevBottomNavigation = bottomNavigationView.getMenu().getItem(MainActivity.SERACHID);
//                prevBottomNavigation.setChecked(true);
//            }
//            public void onSwipeLeft() {
//
//               fragment = new TimeLineFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_fragment_layout, fragment);
//                ft.commit();
//                bottomNavigationView = (BottomNavigationView) getView().findViewById(R.id.bottom_navigation);
//                prevBottomNavigation = bottomNavigationView.getMenu().getItem(MainActivity.TIMELINEID);
//                prevBottomNavigation.setChecked(true);
//            }
//
//        });
//        return view;
//    }
//}
