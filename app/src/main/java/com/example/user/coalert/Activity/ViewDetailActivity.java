package com.example.user.coalert.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.SelectedDetailReviewAdapter.DetailReviewSliderAdapter;
import com.example.user.coalert.Adapter.SelectedDetailReviewAdapter.DetaillReviewCommentAdapter;
import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ViewDetailActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] ImageList = {R.drawable.iu1, R.drawable.iu2, R.drawable.iu3jpg, R.drawable.iu4};
    private static final Integer[] circleList = {R.drawable.sunreview1, R.drawable.sunreview2, R.drawable.sunreview3, R.drawable.sunreview4};
    private static final Integer[] irinlist ={R.drawable.irinblack,R.drawable.irin,R.drawable.irin3,R.drawable.irin4,R.drawable.irin2};
    private static final Integer[] colorlist ={R.drawable.irinpink,R.drawable.irinblack,R.drawable.irin3,R.drawable.irinyellow};
    private static final Integer[] hyoshinlist ={R.drawable.hyoshin1,R.drawable.hyoshin2,R.drawable.hyoshin3,R.drawable.hyoshin4};
    private static final Integer[] seulgilist={R.drawable.used_cosmetic1,R.drawable.seul,R.drawable.seulgi1,R.drawable.seulgi2,R.drawable.seul3};
    private static final Integer[] iulist = {R.drawable.used_cosmetic2,R.drawable.iu1, R.drawable.iu2, R.drawable.iu3jpg, R.drawable.iu4};
    private static final Integer[] iirinlist = {R.drawable.used_cosmetic3,R.drawable.irin,R.drawable.irin3,R.drawable.irin4,R.drawable.irin2};


    private ArrayList<Integer> ImageArr = new ArrayList<Integer>();
    TextView GoodBtn,WriteReveiw;
    ArrayList<OneImgTwoStringCardView> CommentArr;
    RecyclerView comment;
    Button topbtn;
    ImageView LikeIcon;
    TextView LikeCount;
    ScrollView scroll;
    EditText writeContext;
    Button commentConfirm;
    int count=0;
    int j=0;

    String title;

    DetailReviewSliderAdapter adapter;
    ViewPager viewPager;
    TextView PostContext, CreatorId, PostTitle;
    ImageView CreatorPicture;
    ImageButton selected_detail_backBtn;
    Button moreinfo;

    String context = "context";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_detail_review);
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
        GoodBtn=(TextView)findViewById(R.id.good_btn);
        WriteReveiw=(TextView)findViewById(R.id.writeComment);



        LikeCount.setText("3000");
        CreatorPicture.setImageResource(R.drawable.iu4);
        CreatorId.setText("dlwlrma");
        PostTitle.setText("Twenty Three");

        count=Integer.parseInt(LikeCount.getText().toString());

        Intent intent = new Intent(this.getIntent());
        title = intent.getStringExtra("title");
        count=Integer.parseInt(intent.getStringExtra("like"));
        PostTitle.setText(title);
        LikeCount.setText(intent.getStringExtra("like"));
        CreatorId.setText(intent.getStringExtra("id"));
        CreatorPicture.setImageResource(intent.getExtras().getInt("profile"));


        init(title);

        PostContext.setText(context);


        if (j == 0) {
            LikeIcon.setImageResource(R.drawable.black_empty_heart);
            GoodBtn.setTextColor(getResources().getColor(R.color.whiteBackground));
        }else {
            LikeIcon.setImageResource(R.drawable.pink_heart);
            GoodBtn.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        comment.setHasFixedSize(true);
        comment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CommentArr=new ArrayList<>();
        CommentArr.add(new OneImgTwoStringCardView(R.drawable.iu1,"dlwlrma","우와 뭐야 이 후기 정말 굉장한걸?"));
        CommentArr.add(new OneImgTwoStringCardView(R.drawable.irin,"irin","리뷰를 보다보니 저랑 딱 비슷한 타입이신거같아서 많은도움 받고가요~~"));
        comment.setAdapter(new DetaillReviewCommentAdapter(CommentArr));
        ViewCompat.setNestedScrollingEnabled(comment, false);
        comment.setFocusable(false);




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
                    GoodBtn.setTextColor(getResources().getColor(R.color.whiteBackground));
                    Toast.makeText(ViewDetailActivity.this, "좋아요 취소", Toast.LENGTH_SHORT).show();
                } else {
                    LikeIcon.setImageResource(R.drawable.pink_heart);
                    LikeCount.setText(String.valueOf(++count));
                    GoodBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(ViewDetailActivity.this, "좋아요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        GoodBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                j = 1 - j;

                if (j == 0) {
                    LikeIcon.setImageResource(R.drawable.black_empty_heart);
                    LikeCount.setText(String.valueOf(--count));
                    GoodBtn.setTextColor(getResources().getColor(R.color.whiteBackground));
                    Toast.makeText(ViewDetailActivity.this, "좋아요 취소", Toast.LENGTH_SHORT).show();
                } else {
                    LikeIcon.setImageResource(R.drawable.pink_heart);
                    LikeCount.setText(String.valueOf(++count));
                    GoodBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(ViewDetailActivity.this, "좋아요", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void init(String title) {
        if(title.equals("아이린의 화장법")) {
            context="아이린이 무대 나올 때 마다 하는 메이크업에서\n 아이셰도우 색상이 참 예뻐서 찾아보다 마침 저랑 매칭도가 높아서 \n구입해서 사용하게 되었어요. 생각보다 \n 발색력이 좋고 지속력이 오래가서 지성분들에게도 강추합니다!";
            for (int i = 0; i < irinlist.length; i++)
                ImageArr.add(irinlist[i]);
        }
        else if(title.equals("남다른 핑크매력 발산법")) {
            context="팽크빛 화장을 하기위해서는 퍼스널 컬러를 파악하는것이 중요합니다!";
            for (int i = 0; i < colorlist.length; i++)
                ImageArr.add(colorlist[i]);
        }
        else if(title.equals("아이유 메이크업")) {
            context="코스모폴리탄 잡지에 드라마 기대작인 달의 연인-보보경심 려의 출연진들의 화보가 실렸는데요. \n화보에 실린 드라마의 주인공 아이유 양이 참으로 눈에  띄죠!\n" +
                    "\n" +
                    "본격적으로 아이유 메이크업에 돌입해보도록 할게요!\n\n곳곳에 아이유 얼굴의 포인트를 살린 아이유 메이크업 완성!\n" +
                    "단아한 스타일링에 어울릴 듯한 메이크업이네요. 참고해보세요 :D";
            for (int i = 0; i < ImageList.length; i++)
                ImageArr.add(ImageList[i]);
        }
        else if(title.equals("민감성 피부여 일어나라!!")){
            context="일어나라 일어나라!!";
            for (int i = 0; i < circleList.length; i++)
                ImageArr.add(circleList[i]);

        }else if(title.equals("레드벨벨 슬기의 화장품 엿보기")){
            context="슬기누나 짱짱 이뻐요\n완전 짱짱 이쁘다니깐요!";
            for (int i = 0; i < seulgilist.length; i++)
                ImageArr.add(seulgilist[i]);
        }else if(title.equals("한 듯 안 한 듯한 화장법 실현 중..")){
            context="안녕하세요♬ 새해에 좋은일 가득하신가요 !! *_* 전 뭐... 씁쓸하네요 ^^;;;;;; 오늘은 대세 아이유메이크업을 가져와봤어요. 아이유 해피투게더 메이크업인데 요즘 이 화장법 많이 하더라구요!\n베이스는 이니스프리 미네랄 멜팅 파운데이션을 사용할거에요. 3호로 일반적인 21호정도 사용하시는분들께 잘 맞는 색상이에요. 전 이것보다 밝으면 너무 둥둥 뜨더라구요!";
            for (int i = 0; i < iulist.length; i++)
                ImageArr.add(iulist[i]);
        }else if(title.equals("눈이 2배 커지는 화장 비법 도전하기")){
            context="아이린이니까 가능한 메이크업?\n" +
                    "\n" +
                    "NO!\n" +
                    "잘만 하면\n" +
                    "데일리 메이크업으로도\n" +
                    "과하지 않은 아이린 메이크업";
            for (int i = 0; i < iirinlist.length; i++)
                ImageArr.add(iirinlist[i]);
        }
        else{
            context="박효신의 잘생긴 메이크업";
            for (int i = 0; i < hyoshinlist.length; i++)
                ImageArr.add(hyoshinlist[i]);
        }
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
       intent.putExtra("id",CreatorId.getText());
//       intent.putExtra("profile",CreatorPicture.getDrawable());
        startActivity(intent);
    }

    public void onShowPopup(View v){

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the custom popup layout
        final View inflatedView = layoutInflater.inflate(R.layout.popup_write_comment, null,false);

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
//        mDeviceHeight = size.y;
//        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
////        int width = displayMetrics.widthPixels;
////        int height = displayMetrics.heightPixels;

        Point displaySize = new Point();
        this.getWindowManager().getDefaultDisplay().getRealSize(displaySize);

        Rect windowSize = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(windowSize);

        int width = displaySize.x - Math.abs(windowSize.width());
        int height = displaySize.y - Math.abs(windowSize.height());


        // fill the data to the list items
//        setSimpleList(listView);

        PopupWindow popWindow = new PopupWindow(inflatedView,width,height, true);

        // set height depends on the device size
//        popWindow = new PopupWindow(inflatedView, width,height-50, true );
        // set a background drawable with rounders corners

        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        popWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popWindow.setAnimationStyle(R.style.PopupAnimation);

        // show the popup at bottom of the screen and set some margin at bottom ie,
        popWindow.showAtLocation(v, Gravity.BOTTOM, 0,100);
        writeContext=(EditText)inflatedView.findViewById(R.id.commentContext);
        commentConfirm=(Button)inflatedView.findViewById(R.id.confirmbtn);

        commentConfirm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalApplication info=(GlobalApplication) getApplication();

                Toast.makeText(ViewDetailActivity.this, "댓글이 등록되었습니다", Toast.LENGTH_SHORT).show();
                CommentArr.add(new OneImgTwoStringCardView(R.drawable.iu1,info.getId(),writeContext.getText().toString()));
            }
        });
    }


}
