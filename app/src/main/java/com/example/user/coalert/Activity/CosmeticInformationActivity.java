package com.example.user.coalert.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewAdapter;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.SimpleReviewAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.TabIngredientListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class CosmeticInformationActivity extends AppCompatActivity{
    TabHost tabHost1;
    RecyclerView ingredient, simple,detail;
    int percent=50,j=0;
    TextView matching, company, ProductName;
    ImageView ProductImg;
    ImageButton wishbtn;
    ArrayList<OneImgOneStringCardView> IngArr;
    ArrayList<OneImgOneStringOneNumberCardView> SimpleArr;
    ArrayList<TwoImgTwoStringCardView> DetailArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_information);
        wishbtn=(ImageButton)findViewById(R.id.wish_btn);
        matching=(TextView)findViewById(R.id.matching_percent);
        ProductImg=(ImageView)findViewById(R.id.cosmetic_photo);
        ProductName=(TextView)findViewById(R.id.cosmetic_prod_name);
        company=(TextView)findViewById(R.id.cosmetic_comp_name);
        ProductImg.setImageResource(R.drawable.sun1);
        ProductName.setText("말랑말랑썬크림");
        company.setText("이니스프리");
        matching.setText(percent+"%");
        if(percent<20){
            matching.setTextColor(Color.RED);
        }else if(percent<40){
            matching.setTextColor(Color.YELLOW);
        }else if(percent<60){
            matching.setTextColor(Color.BLUE);
        }else
            matching.setTextColor(Color.GREEN);

        if (j == 0)
            wishbtn.setImageResource(R.drawable.emptyheart);
        else
            wishbtn.setImageResource(R.drawable.fullheart);

        wishbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                j = 1 - j;

                if (j == 0) {
                    wishbtn.setImageResource(R.drawable.emptyheart);
                    Toast.makeText(CosmeticInformationActivity.this, "찜하기 취소", Toast.LENGTH_SHORT).show();
                } else {
                    wishbtn.setImageResource(R.drawable.fullheart);
                    Toast.makeText(CosmeticInformationActivity.this, "찜!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        tabHost1=(TabHost)findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1=tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성분");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2=tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("한줄리뷰");
        tabHost1.addTab(ts2);

        TabHost.TabSpec ts3=tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("자세한리뷰");
        tabHost1.addTab(ts3);

        ingredient=(RecyclerView)findViewById(R.id.tab_ingredient_list_recyclerview);
        ingredient.setHasFixedSize(true);
        ingredient.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        IngArr=new ArrayList<>();
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic1"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic2"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic3"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic4"));
        IngArr.add(new OneImgOneStringCardView(R.drawable.cardview1,"toxic5"));
        ingredient.setAdapter(new TabIngredientListAdapter(IngArr));

        simple=(RecyclerView)findViewById(R.id.tab_simple_review_recycler);
        simple.setHasFixedSize(true);
        simple.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        SimpleArr=new ArrayList<>();
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin,"괜찮았어요!",4));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.face1,"음...뭐 그럭저럭?",3));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin,"완죤강추 인생템이예요ㅠㅠㅠ",5));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.face1,"별로임",2));
        simple.setAdapter(new SimpleReviewAdapter(SimpleArr));

        detail=(RecyclerView)findViewById(R.id.tab_detail_review_recycler);
        detail.setHasFixedSize(true);
        detail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DetailArr=new ArrayList<>();
        DetailArr.add(new TwoImgTwoStringCardView(R.drawable.irin,R.drawable.seul,"명탐정코난","언니의 잇템!"));
        DetailArr.add(new TwoImgTwoStringCardView(R.drawable.seul,R.drawable.irin,"루루고양이","선크림 후기 이퀄리티 실화냐?!?"));
        DetailArr.add(new TwoImgTwoStringCardView(R.drawable.cardview1,R.drawable.sun1,"즐거운핫산","100% 리얼한 선크림후기!"));
        detail.setAdapter(new DetailReviewAdapter(DetailArr));

    }
}
