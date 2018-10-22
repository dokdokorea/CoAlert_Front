package com.example.user.coalert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.AdImgAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.adCardViewItem;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    AdImgAdapter adImgAdapter;
    Intent previousIntent;
    String cosmeticCompanyName;
    AppCompatImageView adLogoImage;
    CircleImageView cosmeticImg;
    ImageView backBtn;
    final String INISFREE = "이니스프리", MAC = "맥", CHANEL = "샤넬", YSL = "입생로랑";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inisfree_ad);
        previousIntent = getIntent();
        recyclerView = findViewById(R.id.inisfree_ad_recyclerView);
        cosmeticImg = findViewById(R.id.background_border_image);
        recyclerView.setHasFixedSize(true);
        cosmeticCompanyName = previousIntent.getStringExtra("whatCompany");
        adLogoImage = findViewById(R.id.adLogo);
        backBtn = findViewById(R.id.ad_cosmetic_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*이니스프리
         입생로랑
         샤넬
         맥*/
        adaptiveChange(cosmeticCompanyName);

    }

    public void adaptiveChange(String kindCosmetic){
        switch (kindCosmetic){
            case MAC:
                adLogoImage.setImageResource(R.drawable.ad_mac_logo);
                setImage(1);
                setBorder();
                break;
            case CHANEL:
                adLogoImage.setImageResource(R.drawable.ad_chanel_logo);
                setImage(1);
                setBorder();
                break;
            case YSL:
                adLogoImage.setImageResource(R.drawable.ad_ysl_logo);
                setYvessant();
                setBorder();
                break;
            default:
                setImage(0);
                break;
        }
    }

    public void setBorder(){
        adLogoImage.setBackgroundResource(R.color.whiteBackground);
    }


    public void setImage(int num){
        ArrayList<adCardViewItem> list = new ArrayList<>();
        int irin =  R.drawable.irin;
        list.add(new adCardViewItem(irin, "CoAlert 화장품", num));
        list.add(new adCardViewItem(irin, "CoAlert 화장품", num));
        list.add(new adCardViewItem(irin, "CoAlert 화장품", num));
        list.add(new adCardViewItem(irin, "CoAlert 화장품", num));
        list.add(new adCardViewItem(irin, "CoAlert 화장품", num));
        list.add(new adCardViewItem(irin, "CoAlert 화장품", num));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adImgAdapter = new AdImgAdapter(this.getApplicationContext(), list);
        recyclerView.setAdapter(adImgAdapter);
    }

    public void setYvessant(){
        ArrayList<adCardViewItem> list = new ArrayList<>();
        list.add(new adCardViewItem( R.drawable.yvessant1, "올아워 파운데이션 SPF20 PA++", 1,3));
        list.add(new adCardViewItem(R.drawable.yvessant2, "베르니 아 레브르 바이닐 크림", 1,4));
        list.add(new adCardViewItem(R.drawable.yvessant3, "따뚜아쥬 꾸뛰르", 1,4));
        list.add(new adCardViewItem(R.drawable.yvessant4, "루쥬 쀠르 꾸뛰르 베르니 아 레브르",1, 4));
        list.add(new adCardViewItem( R.drawable.yvessant5, "루쥬 쀠르 꾸뛰르 베르니 아 레브르 레블 누드",1, 4));
        list.add(new adCardViewItem(R.drawable.yvessant6, "볼립떼 틴트 인 오일",1, 4));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adImgAdapter = new AdImgAdapter(this.getApplicationContext(), list);
        recyclerView.setAdapter(adImgAdapter);
    }

    public void chanel(){       // 미완
        ArrayList<adCardViewItem> list = new ArrayList<>();
        list.add(new adCardViewItem(R.drawable.chanel1, "비타뤼미에르 루스 파우더 파운데이션 SPF15", 3));
        list.add(new adCardViewItem(R.drawable.yvessant2, "베르니 아 레브르 바이닐 크림", 4));
        list.add(new adCardViewItem(R.drawable.yvessant3, "따뚜아쥬 꾸뛰르", 4));
        list.add(new adCardViewItem(R.drawable.yvessant4, "루쥬 쀠르 꾸뛰르 베르니 아 레브르", 4));
        list.add(new adCardViewItem(R.drawable.yvessant5, "루쥬 쀠르 꾸뛰르 베르니 아 레브르 레블 누드", 4));
        list.add(new adCardViewItem(R.drawable.yvessant6, "볼립떼 틴트 인 오일", 4));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adImgAdapter = new AdImgAdapter(this.getApplicationContext(), list);
        recyclerView.setAdapter(adImgAdapter);
    }
}
