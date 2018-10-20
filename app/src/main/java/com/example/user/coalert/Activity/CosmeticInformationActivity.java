package com.example.user.coalert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewAdapter;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewPreviewAdapter;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.SimpleReviewAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.TabIngredientListAdapter;
import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;
import com.example.user.coalert.item.TwoStringCardView;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;


public class CosmeticInformationActivity extends AppCompatActivity{
    TabHost tabHost1;
    RecyclerView ingredient, simple,detail;
    int percent=50,j=0;
    TextView matching, company, ProductName;
    ImageView ProductImg;
    NestedScrollView scroll;
    ImageButton wishbtn, shareBtn;
    Button WriteReview, MoreToxicByType;
    int DetailProfileImg,DetailCosmeticImg;
    String DetailUserId,DetailTitle,DetailContext,DetailLikeCount;
    Parcelable state;



    ArrayList<TwoStringCardView> IngArr;
    ArrayList<OneImgOneStringOneNumberCardView> SimpleArr;
    ArrayList<TwoImgTwoStringCardView> DetailArr;
    ArrayList<TwoImgFourStringCardView> DetailPreviewArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_information);
        shareBtn = findViewById(R.id.share_btn);
        wishbtn=(ImageButton)findViewById(R.id.wish_btn);
        matching=(TextView)findViewById(R.id.matching_percent);
        ProductImg=(ImageView)findViewById(R.id.cosmetic_photo);
        ProductName=(TextView)findViewById(R.id.cosmetic_prod_name);
        company=(TextView)findViewById(R.id.cosmetic_comp_name);
        WriteReview=(Button)findViewById(R.id.cosmetic_info_write_review);
        MoreToxicByType=(Button)findViewById(R.id.by_type_ingredient);
        scroll=(NestedScrollView)findViewById(R.id.scroll);
        scroll.fullScroll(NestedScrollView.FOCUS_UP);

        ProductImg.setImageResource(R.drawable.sun1);
        ProductName.setText("말랑말랑썬크림");
        company.setText("이니스프리");
        Drawable alpha = WriteReview.getBackground();
        alpha.setAlpha(50);
        matching.setText(percent+"%");
        if(percent<20){
            matching.setTextColor(Color.RED);
        }else if(percent<40){
            matching.setTextColor(Color.YELLOW);
        }else if(percent<60){
            matching.setTextColor(Color.BLUE);
        }else
            matching.setTextColor(Color.GREEN);
        GlobalApplication infor=(GlobalApplication) getApplication();

        for(int i=0;i<infor.getWishlist().size();i++){
            if(infor.getWishlist().get(i).equals(ProductName.getText().toString())){
                j=1;
            }
        }

        if (j == 0) {
            wishbtn.setImageResource(R.drawable.emptyheart);
        }else {
            wishbtn.setImageResource(R.drawable.fullheart);
        }
        wishbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                j = 1 - j;
                GlobalApplication info=(GlobalApplication) getApplication();
                ArrayList<String> wishlist =new ArrayList<>();

                if (j == 0) {
                    wishbtn.setImageResource(R.drawable.emptyheart);
                    Toast.makeText(CosmeticInformationActivity.this, "찜하기 취소", Toast.LENGTH_SHORT).show();
                    info.removeWishlist(ProductName.getText().toString());
                    info.removeCompany(company.getText().toString());
                    info.removeCosphoto((Integer)ProductImg.getTag());

                } else {
                    wishbtn.setImageResource(R.drawable.fullheart);
                    wishlist=info.getWishlist();
                    wishlist.add(ProductName.getText().toString());
                    info.setWishlist(wishlist);
                    info.addCompany(company.getText().toString());
                    Integer resource = (Integer)ProductImg.getTag();
                    info.addcosphoto(resource);

                    Toast.makeText(CosmeticInformationActivity.this, "찜!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent msg = new Intent (Intent.ACTION_SEND);
                msg.addCategory(Intent.CATEGORY_DEFAULT);
                msg.putExtra(Intent.EXTRA_SUBJECT, "이상훈님의 말랑말랑썬크림(이니스프리)의 적합성을 확인하세요");
                msg.putExtra(Intent.EXTRA_TEXT, "모든 화장품을 개인에게 맞추다 CoAlert");
                msg.setType("text/plain");
                startActivity(Intent.createChooser(msg, "공유"));
            }
        });

        WriteReview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CosmeticInformationActivity.this,WriteReviewActivity.class);
                startActivity(intent);
            }
        });

        MoreToxicByType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CosmeticInformationActivity.this,CosmeticIngredientActivity.class);
                startActivity(intent);
            }
        });

        tabHost1=(TabHost)findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1=tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("성분");
        tabHost1.addTab(ts1);

        TabWidget tw=tabHost1.findViewById(android.R.id.tabs);
        View tabView1=tw.getChildTabViewAt(0);
        TextView tv=tabView1.findViewById(android.R.id.title);
        tv.setTextSize(20);

        TabHost.TabSpec ts2=tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("한줄리뷰");
        tabHost1.addTab(ts2);

        View tabView2=tw.getChildTabViewAt(1);
        TextView tv2=tabView2.findViewById(android.R.id.title);
        tv2.setTextSize(20);

        TabHost.TabSpec ts3=tabHost1.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("자세한리뷰");
        tabHost1.addTab(ts3);
        View tabView3=tw.getChildTabViewAt(2);
        TextView tv3=tabView3.findViewById(android.R.id.title);
        tv3.setTextSize(20);

        ingredient=(RecyclerView)findViewById(R.id.tab_ingredient_list_recyclerview);
        ingredient.setHasFixedSize(true);
        ingredient.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        IngArr=new ArrayList<>();
        IngArr.add(new TwoStringCardView("toxic1","1"));
        IngArr.add(new TwoStringCardView("toxic2","3"));
        IngArr.add(new TwoStringCardView("toxic3","5"));
        IngArr.add(new TwoStringCardView("toxic4","6"));
        IngArr.add(new TwoStringCardView("toxic5","7"));
        IngArr.add(new TwoStringCardView("toxic6","9"));

        ingredient.setAdapter(new TabIngredientListAdapter(IngArr));

        ViewCompat.setNestedScrollingEnabled(ingredient, false);
        ingredient.setFocusable(false);
        findViewById(R.id.linearLayout).requestFocus();

        simple=(RecyclerView)findViewById(R.id.tab_simple_review_recycler);
        simple.setHasFixedSize(true);
        simple.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        SimpleArr=new ArrayList<>();
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin,"괜찮았어요!",4));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.face1,"음...뭐 그럭저럭?",3));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin,"완죤강추 인생템이예요ㅠㅠㅠ",5));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.face1,"별로임",2));
        simple.setAdapter(new SimpleReviewAdapter(SimpleArr));
        ViewCompat.setNestedScrollingEnabled(simple, false);


//        detail=(RecyclerView)findViewById(R.id.tab_detail_review_recycler);
//        detail.setHasFixedSize(true);
//        detail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        DetailArr=new ArrayList<>();
//        DetailArr.add(new TwoImgTwoStringCardView(R.drawable.irin,R.drawable.seul,"명탐정코난","언니의 잇템!"));
//        DetailArr.add(new TwoImgTwoStringCardView(R.drawable.seul,R.drawable.irin,"루루고양이","선크림 후기 이퀄리티 실화냐?!?"));
//        DetailArr.add(new TwoImgTwoStringCardView(R.drawable.iu1,R.drawable.sun1,"즐거운핫산","100% 리얼한 선크림후기!"));
//        detail.setAdapter(new DetailReviewAdapter(DetailArr));

        detail=(RecyclerView)findViewById(R.id.tab_detail_review_preview_recycler);
        detail.setHasFixedSize(true);
        detail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DetailPreviewArr=new ArrayList<>();
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.irin,R.drawable.sun1,"핫한핫산","100","나는똥글을 싸지를것이다!","뿌직뿌직뿌지지지지지지직"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.iu7,R.drawable.sun1,"아이유","1000","밤편지","난~~~ 파도가 머~~물~던 모래 위에 적힌 글씨처럼~~~ 그대가 멀리~~~ 사라져 버릴 것 같아~~~~~"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.nayeon1,R.drawable.sun1,"나나연","500","나는나연","일은 열심히 하셨나연? 배고프지않나연?"));
        detail.setAdapter(new DetailReviewPreviewAdapter(getApplicationContext(), DetailPreviewArr, R.layout.activity_cosmetic_information));
        ViewCompat.setNestedScrollingEnabled(detail, false);


        Intent intent = new Intent(this.getIntent());
//        byte[] arr=getIntent().getByteArrayExtra("image");
//        ProductImg.setImageBitmap(BitmapFactory.decodeByteArray(arr, 0, arr.length));
        ProductImg.setImageResource(intent.getExtras().getInt("picture"));
        ProductImg.setTag(intent.getExtras().getInt("picture"));
        ProductName.setText(intent.getStringExtra("cname"));
        company.setText(intent.getStringExtra("company"));
    }

    public void backbtn(View v) {
       finish();
    }
}
