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
        SunArr.add(new OneImgTwoStringCardView(R.drawable.premiumsun,"프리미엄 선 프로텍션 크림 SPF50+ PA+++","A24,1"));
        SunArr.add(new OneImgTwoStringCardView(R.drawable.reliefsun,"UV 릴리프 모이스처라이저 SPF50+ PA++++","비레머디스,1"));
        SunArr.add(new OneImgTwoStringCardView(R.drawable.uvdefense,"유브이 디펜스 미 블루 레이 선 플루이드 SPF50+ PA++++","메이크프렘,1"));
        SunRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),SunArr,R.layout.activity_new_product_list));

        LipArr = new ArrayList<>();
            LipArr.add(new OneImgTwoStringCardView(R.drawable.tint1,"라스트 벨벳 립 틴트 4","삐아,4"));
        LipRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),LipArr,R.layout.activity_new_product_list));

        ShadowArr = new ArrayList<>();
            ShadowArr.add(new OneImgTwoStringCardView(R.drawable.eyecolor,"매트 아이 컬러","로라 메르시에,2"));
        ShadowRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),ShadowArr,R.layout.activity_new_product_list));

        PdArr = new ArrayList<>();

            PdArr.add(new OneImgTwoStringCardView(R.drawable.poundation1,"뗑 이돌 롱라스팅 파운데이션 SPF38 PA++","랑콤,3"));
        PdArr.add(new OneImgTwoStringCardView(R.drawable.poundation2,"아쿠아 글로우 쿠션 파운데이션 SPF23 PA++","나스,3"));
        PdArr.add(new OneImgTwoStringCardView(R.drawable.poundation3,"원피엘 파운데이저 SPF20 PA++","메이크힐,3"));
        PdArr.add(new OneImgTwoStringCardView(R.drawable.poundation4,"퓨처리스트 아쿠아 브릴리언스 리퀴드 파운데이션 SPF15 PA++","에스티로더,3"));
        PdRecycler.setAdapter(new NewProductAdapter(getApplicationContext(),PdArr,R.layout.activity_new_product_list));

        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
