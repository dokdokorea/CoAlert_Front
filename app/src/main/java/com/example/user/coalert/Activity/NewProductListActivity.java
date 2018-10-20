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
    ArrayList<OneImgTwoStringCardView> SunArr,LipArr,ShadowArr,PdArr;
    private RecyclerView SunRecycler,LipRecycler,ShadowRecycler,PdRecycler;
    private int COLUM = 3;
    ImageView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product_list);

        backBtn=(ImageView)findViewById(R.id.back_btn);
        SunRecycler = (RecyclerView) findViewById(R.id.sun_recycler);
        LipRecycler=(RecyclerView)findViewById(R.id.liptint_recycler);
        ShadowRecycler=(RecyclerView)findViewById(R.id.eyeshadow_recycler);
        PdRecycler=(RecyclerView)findViewById(R.id.poundation_recycler);

        SunRecycler.setHasFixedSize(true);
        LipRecycler.setHasFixedSize(true);
        ShadowRecycler.setHasFixedSize(true);
        PdRecycler.setHasFixedSize(true);

        SunRecycler.setLayoutManager(new GridLayoutManager(this, COLUM));
        LipRecycler.setLayoutManager(new GridLayoutManager(this, COLUM));
        ShadowRecycler.setLayoutManager(new GridLayoutManager(this, COLUM));
        PdRecycler.setLayoutManager(new GridLayoutManager(this, COLUM));

        SunArr = new ArrayList<>();
        for(int i=0;i<4;i++)
        SunArr.add(new OneImgTwoStringCardView(R.drawable.sun1,"SunBlock","inisfree"));
        SunArr.add(new OneImgTwoStringCardView(R.drawable.sun1,"SunBlockSunBlockSunBlock","inisfreeinisfreeinisfree"));
        SunRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),SunArr,R.layout.activity_new_product_list));


        LipArr = new ArrayList<>();
        for(int i=0;i<1;i++)
            LipArr.add(new OneImgTwoStringCardView(R.drawable.sun1,"cosmetic","company"));
        LipRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),LipArr,R.layout.activity_new_product_list));

        ShadowArr = new ArrayList<>();
        for(int i=0;i<3;i++)
            ShadowArr.add(new OneImgTwoStringCardView(R.drawable.sun1,"cosmetic","company"));
        ShadowRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),ShadowArr,R.layout.activity_new_product_list));

        PdArr = new ArrayList<>();
        for(int i=0;i<7;i++)
            PdArr.add(new OneImgTwoStringCardView(R.drawable.sun1,"cosmetic","company"));
        PdRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),PdArr,R.layout.activity_new_product_list));



        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
