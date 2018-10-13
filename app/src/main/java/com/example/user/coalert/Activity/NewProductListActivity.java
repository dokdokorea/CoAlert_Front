package com.example.user.coalert.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.user.coalert.Adapter.FragmentHomeElementAdapter.NewProductAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class NewProductListActivity extends Activity {
    ArrayList<OneImgTwoStringCardView> NewProductArr;
    private RecyclerView newproductRecyclerView;
    private int COLUM = 3;
    ImageView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product_list);

        backBtn=(ImageView)findViewById(R.id.back_btn);
        newproductRecyclerView = (RecyclerView) findViewById(R.id.new_product_recyclerview);
        newproductRecyclerView.setHasFixedSize(true);
        newproductRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUM));
        NewProductArr = new ArrayList<>();
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.cardview1);
        for(int i=0;i<10;i++)
        NewProductArr.add(new OneImgTwoStringCardView(R.drawable.cardview1,"cosmetic","company"));


//        wishRecyclerView.setAdapter(new NewProductAdapter(wishListArr));
        newproductRecyclerView.setAdapter(new NewProductAdapter(getApplicationContext(),NewProductArr,R.layout.wish_list));

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
