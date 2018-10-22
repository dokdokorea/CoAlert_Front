package com.example.user.coalert.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.ArrayList;
import java.util.List;

public class AnotherprofileActivity extends AppCompatActivity {
    List<String> list;
    TextView follow;
    TextView email;
    ImageButton Toxic;
    ImageButton following;
    ImageButton backBtn;
    ImageView profile;
    TextView id;
    int count;
    int j = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotherprofile);
        backBtn = findViewById(R.id.anotherprofile_back_btn);
        follow = (TextView) findViewById(R.id.follower);
        Toxic = (ImageButton) findViewById(R.id.toxicList);
        following = (ImageButton) findViewById(R.id.following);
        profile=(ImageView)findViewById(R.id.profile_pic);
        id=(TextView)findViewById(R.id.Name);
        email=(TextView)findViewById(R.id.email);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView cosmeticList = (RecyclerView) findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        int ColumNumber = 3;      //GridView Column
        Drawable alpha = backBtn.getBackground();
        alpha.setAlpha(50);
        count=73;

        Intent intent=getIntent();
        profile.setImageResource(intent.getExtras().getInt("profile"));
        id.setText(intent.getStringExtra("id"));
        if(id.getText().equals("irinlove")){
            count=72;
            email.setText("irinlove@naver.com");
        }else if(id.getText().equals("twice_nayeon")){
            count=123;
            email.setText("tdungtdug@naver.com");
        }else if(id.getText().equals("pink_gondyu")){
            count=321;
            email.setText("vldzmrhdwb@google.com");
        }else if(id.getText().equals("velvet_SG")){
            count=51;
            email.setText("dnflsnsk123@naver.com");
        }else if(id.getText().equals("dokdokorea")){
            count=33;
            email.setText("dokdokorea@naver.com");
        }else{
            count=12;
            email.setText("helloworld@naver.com");
        }
        follow.setText(String.valueOf(count));



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        cosmeticList.setHasFixedSize(true);
        cosmeticList.setLayoutManager(new GridLayoutManager(this, ColumNumber));
        cosmeticList.setNestedScrollingEnabled(false);


        List<OneImgOneStringCardView> items = new ArrayList<>();
        OneImgOneStringCardView[] item = new OneImgOneStringCardView[6];
        item[0] = new OneImgOneStringCardView(R.drawable.cardview1, "seul");
        item[1] = new OneImgOneStringCardView(R.drawable.nayeon1, "twice_nayeon");
        item[2] = new OneImgOneStringCardView(R.drawable.hyoshin5, "god_hs");
        item[3] = new OneImgOneStringCardView(R.drawable.irin3, "irinlove");
        item[4] = new OneImgOneStringCardView(R.drawable.hyoshin8, "hs_park");
        item[5] = new OneImgOneStringCardView(R.drawable.irin, "iheartrin");


        for (int i = 0; i < 6; i++) items.add(item[i]);

        recyclerView.setAdapter(new MyprofileFollowerAdapter(getApplicationContext(), items, R.layout.activity_anotherprofile));

        List<OneImgOneStringCardView> cositems = new ArrayList<>();
        OneImgOneStringCardView[] cositem = new OneImgOneStringCardView[5];
        cositem[0] = new OneImgOneStringCardView(R.drawable.premiumsun, "프리미엄 선 프로텍션 크림 SPF50+ PA+++");
        cositem[1] = new OneImgOneStringCardView(R.drawable.tint1, "라스트 벨벳 립 틴트 4");
        cositem[2] = new OneImgOneStringCardView(R.drawable.eyecolor, "매트 아이 컬러");
        cositem[3] = new OneImgOneStringCardView(R.drawable.poundation1, "뗑 이돌 롱라스팅 파운데이션 SPF38 PA++");

        for (int i = 0; i < 4; i++) cositems.add(cositem[i]);
        cosmeticList.setAdapter(new MyprofileRecyclerViewAdapter(getApplicationContext(), cositems, R.layout.activity_anotherprofile));


        if (j == 0)
            following.setImageResource(R.drawable.black_empty_heart);
        else
            following.setImageResource(R.drawable.pink_heart);

        following.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                j = 1 - j;

                if (j == 0) {
                    following.setImageResource(R.drawable.black_empty_heart);
                    follow.setText(String.valueOf(--count));
                    Toast.makeText(AnotherprofileActivity.this, "팔로우 취소하셨습니다", Toast.LENGTH_SHORT).show();
                } else {
                    following.setImageResource(R.drawable.pink_heart);
                    follow.setText(String.valueOf(++count));
                    Toast.makeText(AnotherprofileActivity.this, "팔로우하셨습니다", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Toxic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(AnotherprofileActivity.this,ExplanationBadElementActivity.class);
               startActivity(intent);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


