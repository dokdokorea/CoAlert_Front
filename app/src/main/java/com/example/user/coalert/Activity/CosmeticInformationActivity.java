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
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.getReviewModel;
import com.example.user.coalert.forRestServer.oneCosmeticRecommend;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;
import com.example.user.coalert.item.TwoStringCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

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
    String badIngredient;
    ArrayList<TwoStringCardView> IngArr;
    ArrayList<OneImgOneStringOneNumberCardView> SimpleArr;
    ArrayList<TwoImgTwoStringCardView> DetailArr;
    ArrayList<TwoImgFourStringCardView> DetailPreviewArr;
    TextView typeText;
    TextView backHome;
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
        typeText = findViewById(R.id.cosmeticType);
        scroll=(NestedScrollView)findViewById(R.id.scroll);
        scroll.fullScroll(NestedScrollView.FOCUS_UP);
        final Intent intent = new Intent(this.getIntent());
        int number = intent.getExtras().getInt("check");
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

//        Intent intent = new Intent(this.getIntent());
//        ProductImg.setImageResource(intent.getExtras().getInt("picture"));
//        ProductImg.setTag(intent.getExtras().getInt("picture"));
//        ProductName.setText(intent.getStringExtra("cname"));
//        company.setText(intent.getStringExtra("company"));
        final int kind = intent.getExtras().getInt("kind");
        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        if (number == 0) {
            Drawable drawable = getResources().getDrawable((Integer) intent.getExtras().get("image"));
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

            ProductImg.setImageBitmap(bitmap);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Call<oneCosmeticRecommend> call = ForRestSingleton.getInstance().oneRecommendCosmetic(kind, intent.getStringExtra("cname"), 0);
                        final oneCosmeticRecommend result = call.execute().body();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                matching.setText(String.valueOf(result.getRating()*20));
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();}
                }
            }.start();
        }else{
            Log.e("asdads", String.valueOf(intent.getExtras().get("rating")));
            ProductImg.setImageBitmap((Bitmap) intent.getExtras().get("image"));
            matching.setText(String.valueOf(intent.getExtras().get("rating")));
        }

        ProductName.setText(intent.getStringExtra("cname"));
        company.setText(intent.getStringExtra("company"));

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
                GlobalApplication info=(GlobalApplication) getApplication();
                Intent msg = new Intent (Intent.ACTION_SEND);
                msg.addCategory(Intent.CATEGORY_DEFAULT);
                msg.putExtra(Intent.EXTRA_SUBJECT, info.getId()+"님의 "+ProductName.getText()+"("+company.getText()+")의 적합성은 "+matching.getText()+"%입니다 당신의 적합도도 확인하러오세요!");
                msg.putExtra(Intent.EXTRA_TEXT, "모든 화장품을 개인에게 맞추다 CoAlert");
                msg.setType("text/plain");
                startActivity(Intent.createChooser(msg, "공유"));
            }
        });

        WriteReview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CosmeticInformationActivity.this,WriteReviewActivity.class);
                Intent hi = new Intent(getIntent());
                int number = hi.getExtras().getInt("check");
                if(number == 0){
                    intent.putExtra("image", (Integer) hi.getExtras().get("image"));
                    intent.putExtra("check", 0);
                }else if(number == 1){
                    intent.putExtra("image", (Bitmap) hi.getExtras().get("image"));
                    intent.putExtra("check", 1);
                }
                intent.putExtra("rating", matching.getText());
                intent.putExtra("kind", kind);
                intent.putExtra("cname", hi.getStringExtra("cname"));
                intent.putExtra("company",hi.getStringExtra("company"));
                startActivity(intent);
                finish();
            }
        });

        MoreToxicByType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CosmeticInformationActivity.this, CosmeticIngredientActivity.class);
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
        badIngredient = intent.getExtras().getString("ingredient");

        IngArr=new ArrayList<>();

        JsonArray badElementJsonArray = dataToJsonArray(badIngredient);
        setData(badElementJsonArray);

        ViewCompat.setNestedScrollingEnabled(ingredient, false);
        ingredient.setFocusable(false);
        findViewById(R.id.linearLayout).requestFocus();

        simple=(RecyclerView)findViewById(R.id.tab_simple_review_recycler);
        simple.setHasFixedSize(true);
        simple.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Call<List<getReviewModel>> call = ForRestSingleton.getInstance().getReview(kind, intent.getStringExtra("cname"));
                    List<getReviewModel> data = call.execute().body();
                    assert data != null;
                    Log.e("asdasd", data.toString());
                    JsonArray review = dataToJsonArray(data.toString());
                    SimpleArr = new ArrayList<>();
                    for (int i = 0; i< review.size();i++) {
                        JsonObject jsonObject = (JsonObject) review.get(i);
                        int rating = Integer.parseInt(jsonObject.get("rating").toString().replaceAll("\"",""));
                        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin, rating, jsonObject.get("review").toString().replaceAll("\"", " ").replace("\\n", ""),jsonObject.get("type").toString().replace("\"","")));
                        }
                    simple.setAdapter(new SimpleReviewAdapter(SimpleArr));
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        ViewCompat.setNestedScrollingEnabled(simple, false);

        detail=(RecyclerView)findViewById(R.id.tab_detail_review_preview_recycler);
        detail.setHasFixedSize(true);
        detail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DetailPreviewArr=new ArrayList<>();
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.irin,R.drawable.sun2,"dlwlrma","123","리얼 썬크림 후기!","안녕하세요 여러분 오늘은 쫀쫀하기로 소문난 A24 프리미엄 썬 프로텍션 크림 SPF 50+/PA+++ 선크림을 사용해보았어요"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.iu7,R.drawable.sunreview2,"아이유","1096","A24썬크림 써봤습니다~","좋았어요~~~"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.nayeon1,R.drawable.sunreview1,"duddn14758","739","A24 썬크림의 A부터 Z까지!","자외선 차단 지수 빵빵하다.\n" +
                "워터프루프 선크림.\n천연원료와 유기농원료를 뽐내주는 전성분.\n" +
                "피부주름개선과 피부미백에 도움을 준다고 한다.\n50시간 수분지속!\n" +
                "민감피부 및 모든 피부를 위한 선크림으로 건성이나 수분부족형 지성 피부가 쓰기 좋다.\n" +
                "미백 선크림으로 피부 톤을 한 층 밝혀준다.\n선크림 중에는 간혹 독해서 눈 주위에 바르면 눈이 시리고 따끔한 경우가 있는데 그렇지는 않았다.\n" +
                "순한 선크림으로 오일프리선크림이기 때문에 확실히 촉촉한데 유분이 겉도는 느낌은 들지 않았다.\n" +
                "단, 미백 효과를 준다는 점은 좋지만 하얗게 된다는 점 때문에 몸에 바르기에는 꺼려질 것 같다."));
        detail.setAdapter(new DetailReviewPreviewAdapter(getApplicationContext(), DetailPreviewArr, R.layout.activity_cosmetic_information));
        ViewCompat.setNestedScrollingEnabled(detail, false);
    }
    public void setData(JsonArray jsonArray){
        for (int i = 0; i<jsonArray.size(); i++) {

            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            IngArr.add(new TwoStringCardView(jsonObject.get("ingredientName").toString(), jsonObject.get("warningRate").toString().replaceAll("\"", "")));
        }
        ingredient.setAdapter(new TabIngredientListAdapter(IngArr));
    }
    public void backbtn(View v) {
       finish();
    }
    public JsonArray dataToJsonArray(String data) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(data);
        return element.getAsJsonArray();
    }
}
