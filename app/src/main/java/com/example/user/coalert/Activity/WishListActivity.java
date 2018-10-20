package com.example.user.coalert.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.WishListAdapter;
import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImageCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class WishListActivity extends Activity {
    ArrayList<OneImgTwoStringCardView> wishListArr;
    private RecyclerView wishRecyclerView;
    private int COLUM = 3;
    ImageView backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list);
;

        GlobalApplication info = (GlobalApplication) getApplication();
        backbtn = (ImageView) findViewById(R.id.back_btn);
        wishRecyclerView = (RecyclerView) findViewById(R.id.wish_recyclerview);
        wishRecyclerView.setHasFixedSize(true);
        wishRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUM));
        wishListArr = new ArrayList<>();
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.cardview1);


        for (int i = 0; i < info.getWishlist().size(); i++) {
            wishListArr.add(new OneImgTwoStringCardView(info.getCosphoto().get(i), info.getWishlist().get(i), info.getComplist().get(i)));
        }
//        wishRecyclerView.setAdapter(new NewProductAdapter(wishListArr));

        wishRecyclerView.setAdapter(new NewProductAdapter(getApplicationContext(), wishListArr, R.layout.wish_list));


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
