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
                setImage(1);
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
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.irin);
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품", num));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품", num));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품", num));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품", num));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품", num));
        list.add(new adCardViewItem(bitmap, "CoAlert 화장품", num));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adImgAdapter = new AdImgAdapter(this.getApplicationContext(), list);
        recyclerView.setAdapter(adImgAdapter);
    }
}
