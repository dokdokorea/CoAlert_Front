package com.example.user.coalert.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.WishListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImageCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class WishListActivity extends Activity {
    ArrayList<OneImgTwoStringCardView> wishListArr;
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
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.cardview1);
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));
        wishListArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));

//        wishRecyclerView.setAdapter(new NewProductAdapter(wishListArr));
        wishRecyclerView.setAdapter(new NewProductAdapter(getApplicationContext(),wishListArr,R.layout.wish_list));


    }
}
