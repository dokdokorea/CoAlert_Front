package com.example.user.coalert.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.coalert.Adapter.MyprofileAdapter.WishListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImageCardView;

import java.util.ArrayList;

public class WishListActivity extends Activity {
    ArrayList<OneImageCardView> wishListArr;
    private RecyclerView wishRecyclerView;
    private int COLUM = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list);


        wishRecyclerView = (RecyclerView) findViewById(R.id.wish_recyclerview);
        wishRecyclerView.setHasFixedSize(true);
        wishRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUM));
        wishListArr = new ArrayList<>();
        wishListArr.add(new OneImageCardView(R.drawable.cardview1));
        wishRecyclerView.setAdapter(new WishListAdapter(wishListArr));


    }
}
