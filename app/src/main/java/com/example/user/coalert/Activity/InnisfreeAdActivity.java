package com.example.user.coalert.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.coalert.Adapter.AdImgAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.adCardViewItem;

import java.util.ArrayList;

public class InnisfreeAdActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    AdImgAdapter adImgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inisfree_ad);
        recyclerView = findViewById(R.id.inisfree_ad_recyclerView);
        recyclerView.setHasFixedSize(true);

        ArrayList<adCardViewItem> list = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.irin);
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품"));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품"));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품"));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품"));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품"));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품"));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adImgAdapter = new AdImgAdapter(this.getApplicationContext(), list);
        recyclerView.setAdapter(adImgAdapter);
    }
}
