package com.example.user.coalert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.user.coalert.Adapter.AdImgAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.adCardViewItem;

import java.util.ArrayList;

public class AdActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    AdImgAdapter adImgAdapter;
    Intent previousIntent;
    String cosmeticCompanyName;
    AppCompatImageView adLogoImage;
    TextView cosmeticCompanyExplain;
    final String INISFREE = "이니스프리", MAC = "맥", CHANEL = "샤넬", YSL = "입생로랑";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inisfree_ad);
        previousIntent = getIntent();
        recyclerView = findViewById(R.id.inisfree_ad_recyclerView);
        cosmeticCompanyExplain = findViewById(R.id.cosmeticCompanyExplain);
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
                adLogoImage.setBackgroundResource(R.color.whiteBackground);
                cosmeticCompanyExplain.setText(R.string.MacExplain);
                setImage();
                break;
            case CHANEL:
                adLogoImage.setImageResource(R.drawable.ad_chanel_logo);
                adLogoImage.setBackgroundResource(R.color.whiteBackground);
                cosmeticCompanyExplain.setText(R.string.ChannelExplain);
                setImage();
                break;
            case YSL:
                adLogoImage.setImageResource(R.drawable.ad_ysl_logo);
                adLogoImage.setBackgroundResource(R.color.whiteBackground);
                cosmeticCompanyExplain.setText(R.string.YslExplain);
                setImage();
                break;
            default:
                setImage();
                break;
        }
    }

    public void setLogo(){

    }

    public void setText(){

    }

    public void setImage(){
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
