package com.example.user.coalert;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.coalert.Fragment.HomeFragment;
import com.example.user.coalert.Fragment.TimeLineFragment;
import com.example.user.coalert.Fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private MenuItem prevBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = new SearchFragment();
                Log.e("add ", String.valueOf(id));
                    if(id == R.id.action_one) {
                        Toast.makeText(MainActivity.this, "Action first Clicked", Toast.LENGTH_LONG).show();
                        fragment = new SearchFragment();
                    }
                   else if (id == R.id.action_two) {
                        Toast.makeText(MainActivity.this, "Action second Clicked", Toast.LENGTH_LONG).show();
                        fragment = new HomeFragment();
                    }
                    else if( R.id.action_three == id) {
                        Toast.makeText(MainActivity.this, "Action third Clicked", Toast.LENGTH_LONG).show();
                        fragment = new TimeLineFragment();
                    }

                    @SuppressLint("CommitTransaction") FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_fragment_layout, fragment);
                    ft.commit();

                return true;
            }
        });
    }


}
