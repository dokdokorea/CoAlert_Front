package com.example.user.coalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.SelectedDetailReviewAdapter.DetailReviewSliderAdapter;
import com.example.user.coalert.Adapter.SelectedDetailReviewAdapter.DetaillReviewCommentAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ViewDetailActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] ImageList = {R.drawable.iu1, R.drawable.iu2, R.drawable.iu7, R.drawable.iu3jpg, R.drawable.iu4, R.drawable.iu5};
    private ArrayList<Integer> ImageArr = new ArrayList<Integer>();
    ArrayList<OneImgTwoStringCardView> CommentArr;
    RecyclerView comment;
    Button topbtn;
    ImageView LikeIcon;
    TextView LikeCount;
    ScrollView scroll;
    int count=0;
    int j=0;

    String title;

    DetailReviewSliderAdapter adapter;
    ViewPager viewPager;
    TextView PostContext, CreatorId, PostTitle;
    ImageView CreatorPicture;
    ImageButton selected_detail_backBtn;
    Button moreinfo;

    String Context = "I'm twenty three \n" +
            "난 수수께끼 (Question)\n" +
            "뭐게요 맞혀봐요\n" +
            "I'm twenty three\n" +
            "틀리지 말기 Because \n" +
            "난 몹시 예민해요\n" +
            "맞춰봐\n" +
            "\n" +
            "한 떨기 스물셋 좀 \n" +
            "아가씨 태가 나네\n" +
            "다 큰 척해도 적당히 믿어줘요\n" +
            "\n" +
            "얄미운 스물셋\n" +
            "아직 한참 멀었다 얘 \n" +
            "덜 자란 척해도\n" +
            "대충 속아줘요\n" +
            "\n" +
            "난, 그래 확실히 지금이 좋아요 \n" +
            "아냐, 아냐 사실은 때려 치고 싶어요\n" +
            "아 알겠어요 난 사랑이 하고 싶어\n" +
            "아니 돈이나 많이 벌래\n" +
            "맞춰봐\n" +
            "\n" +
            "어느 쪽이게?\n" +
            "얼굴만 보면 몰라\n" +
            "속마음과 다른 표정을 짓는 일\n" +
            "아주 간단하거든\n" +
            "어느 쪽이게?\n" +
            "사실은 나도 몰라\n" +
            "애초에 나는 단 한 줄의 \n" +
            "거짓말도 쓴 적이 없거든\n" +
            "\n" +
            "여우인 척, 하는 곰인 척, 하는 여우 아니면\n" +
            "아예 다른 거\n" +
            "\n" +
            "어느 쪽이게?\n" +
            "뭐든 한 쪽을 골라\n" +
            "색안경 안에 비춰지는 거 뭐 이제 익숙하거든\n" +
            "Check it out\n" +
            "\n" +
            "겁나는 게 없어요\n" +
            "엉망으로 굴어도 \n" +
            "사람들은 내게 매일 친절해요\n" +
            "\n" +
            "인사하는 저 여자\n" +
            "모퉁이를 돌고도 아직 웃고 있을까\n" +
            "늘 불안해요\n" +
            "\n" +
            "난, 영원히 아이로 남고 싶어요\n" +
            "아니, 아니 물기 있는 여자가 될래요\n" +
            "아 정했어요 난 죽은 듯이 살래요\n" +
            "아냐, 다 뒤집어 볼래\n" +
            "맞춰봐\n" +
            "\n" +
            "어느 쪽이게?\n" +
            "얼굴만 보면 몰라\n" +
            "속마음과 다른 표정을 짓는 일\n" +
            "아주 간단하거든\n" +
            "어느 쪽이게?\n" +
            "사실은 나도 몰라\n" +
            "애초에 나는 단 한 줄의 \n" +
            "거짓말도 쓴 적이 없거든\n" +
            "\n" +
            "여우인 척, 하는 곰인 척, 하는 여우 아니면\n" +
            "아예 다른 거\n" +
            "\n" +
            "어느 쪽이게?\n" +
            "뭐든 한 쪽을 골라\n" +
            "색안경 안에 비춰지는 거 뭐 이제 익숙하거든\n" +
            "\n" +
            "난 당신 맘에 들고 싶어요\n" +
            "아주 살짝만 얄밉게 해도 돼요?\n" +
            "난 당신 맘에 들고 싶어요\n" +
            "자기 머리 꼭대기 위에서 놀아도 돼요?\n" +
            "맞춰봐\n" +
            "\n" +
            "어느 쪽이게?\n" +
            "얼굴만 보면 몰라\n" +
            "속마음과 다른 표정을 짓는 일\n" +
            "아주 간단하거든\n" +
            "어느 쪽이게?\n" +
            "사실은 나도 몰라\n" +
            "애초에 나는 단 한 줄의 \n" +
            "거짓말도 쓴 적이 없거든\n" +
            "\n" +
            "여우인 척, 하는 곰인 척, 하는 여우 아니면\n" +
            "아예 다른 거\n" +
            "\n" +
            "어느 쪽이게?\n" +
            "뭐든 한 쪽을 골라\n" +
            "색안경 안에 비춰지는 거 뭐 이제 익숙하거든";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_detail_review);
        init();
        selected_detail_backBtn = findViewById(R.id.selected_detail_review_back_btn);
        moreinfo=findViewById(R.id.more_review);
        selected_detail_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PostContext = (TextView) findViewById(R.id.detail_context);
        CreatorPicture = (ImageView) findViewById(R.id.user_profile);
        CreatorId = (TextView) findViewById(R.id.user_id);
        PostTitle = (TextView) findViewById(R.id.post_title);
        LikeIcon=(ImageView)findViewById(R.id.like_icon);
        LikeCount=(TextView) findViewById(R.id.like_count);
        topbtn=(Button)findViewById(R.id.toTopButton);
        scroll=(ScrollView)findViewById(R.id.scroll);
        comment=(RecyclerView)findViewById(R.id.commentRecycler);

        LikeCount.setText("3000");
        CreatorPicture.setImageResource(R.drawable.iu4);
        CreatorId.setText("dlwlrma");
        PostTitle.setText("Twenty Three");
        PostContext.setText(Context);
        count=Integer.parseInt(LikeCount.getText().toString());
        if (j == 0)
            LikeIcon.setImageResource(R.drawable.black_empty_heart);
        else
            LikeIcon.setImageResource(R.drawable.pink_heart);

        comment.setHasFixedSize(true);
        comment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CommentArr=new ArrayList<>();
        CommentArr.add(new OneImgTwoStringCardView(R.drawable.iu1,"dlwlrma","hello my name is IU and 26years old"));
        CommentArr.add(new OneImgTwoStringCardView(R.drawable.irin,"irin","Go! Go! 에어플레인! 번개처럼 날아라\n" +
                "카우아이 파도 속 나를 던져 버리게\n" +
                "이예이예 이예이예이예이예 \n" +
                "Let’s power up! 까맣게 다 타버릴 거예요"));
        comment.setAdapter(new DetaillReviewCommentAdapter(CommentArr));



        topbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        moreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewDetailActivity.this,CosmeticInformationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        LikeIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                j = 1 - j;

                if (j == 0) {
                    LikeIcon.setImageResource(R.drawable.black_empty_heart);
                    LikeCount.setText(String.valueOf(--count));

                    Toast.makeText(ViewDetailActivity.this, "좋아요", Toast.LENGTH_SHORT).show();
                } else {
                    LikeIcon.setImageResource(R.drawable.pink_heart);
                    LikeCount.setText(String.valueOf(++count));
                    Toast.makeText(ViewDetailActivity.this, "좋아요 취소", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Intent intent = new Intent(this.getIntent());
        title = intent.getStringExtra("title");
        PostTitle.setText(title);
    }

    private void init() {
        for (int i = 0; i < ImageList.length; i++)
            ImageArr.add(ImageList[i]);

        mPager = (ViewPager) findViewById(R.id.view);
        mPager.setAdapter(new DetailReviewSliderAdapter(ViewDetailActivity.this, ImageArr));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == ImageList.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 4000);
    }

    public void goAnotheruser(View v) {
       Intent intent=new Intent(ViewDetailActivity.this,AnotherprofileActivity.class);
       startActivity(intent);
    }
}
