package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewPreviewAdapter;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.SimpleReviewAdapter;
import com.example.user.coalert.Adapter.MyReviewListAdapter.MyDetailReviewAdapter;
import com.example.user.coalert.Adapter.MyReviewListAdapter.MySimpleReviewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.OneImgTwoStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

@SuppressLint("Registered")
public class MyReviewListActivity extends AppCompatActivity {

    //    private Button btn1;
//    private Button btn2;
//
//    private View view1;
//    private View view2;
    RecyclerView simple, detail;
    ArrayList<OneImgTwoStringOneNumberCardView> SimpleArr;
    ArrayList<TwoImgFourStringCardView> DetailPreviewArr;
    ViewGroup transitionsContainer;
    TextView SimpleIcon,DetailIcon;
    boolean visibleS=false,visibleD=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_reviews);


        transitionsContainer = (ViewGroup) findViewById(R.id.container);
        SimpleIcon=(TextView)findViewById(R.id.simple_list_icon);
        DetailIcon=(TextView)findViewById(R.id.detail_list_icon);

        simple = (RecyclerView) findViewById(R.id.view_simple_review);
        simple.setHasFixedSize(true);
        simple.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SimpleArr = new ArrayList<>();
        SimpleArr.add(new OneImgTwoStringOneNumberCardView(R.drawable.sun1, "쫀쪼니 썬크림","괜찮았어요!", 4));
        SimpleArr.add(new OneImgTwoStringOneNumberCardView(R.drawable.sun1, "말랑말랑 썬크림","음...뭐 그럭저럭?", 3));
        SimpleArr.add(new OneImgTwoStringOneNumberCardView(R.drawable.sun1, "청량한 썬크림","완죤강추 인생템이예요ㅠㅠㅠ", 5));
        SimpleArr.add(new OneImgTwoStringOneNumberCardView(R.drawable.sun1, "찍찍한 썬크림","별로임", 2));
        simple.setAdapter(new MySimpleReviewAdapter(SimpleArr));

        detail=(RecyclerView)findViewById(R.id.view_detail_review);
        detail.setHasFixedSize(true);
        detail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DetailPreviewArr=new ArrayList<>();
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.sun1,R.drawable.irinpink,"립밤","300","내 핑크는 우 월 해","당당히 넌 고개를 들고 나를 봐 역시 Rookie rookie my super rookie rookie boy 좋아 볼 때마다 진짜 넌 내 Type 역시 Rookie rookie my super rookie rookie boy ha!"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.sun1,R.drawable.irinblack,"림스틱","10000","내 블랙은 도 도 해","이토록 거센 존재감 난 이미 혹시나 하는 작은 의심조차 못 해 즐기는 척 하하 난 웃어 봐 애써 말투까지 네 앞에선 마치 Ice같지"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.sun1,R.drawable.irinyellow,"완죠니 쫀쫀한 썬크림","100","내 옐로우는 느낌이 달 라!!! 오우엥에네네에에에","호로롤ㄹㄹ롤로로"));

        detail.setAdapter(new MyDetailReviewAdapter(DetailPreviewArr));

    }

    public void viewSimple(View v) {
        TransitionManager.beginDelayedTransition(transitionsContainer);
        visibleS = !visibleS;
        simple.setVisibility(visibleS ? View.VISIBLE : View.GONE);
        SimpleIcon.setText(visibleS ? "▼":"▶");
        //view2.setVisibility(View.GONE);

    }

    public void viewDetail(View v) {
        TransitionManager.beginDelayedTransition(transitionsContainer);
        visibleD = !visibleD;
        detail.setVisibility(visibleD ? View.VISIBLE : View.GONE);
        DetailIcon.setText(visibleD ? "▼":"▶");
        //view2.setVisibility(View.GONE);

    }

//
//    View.OnClickListener btn2Listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            view1.setVisibility(View.GONE);
//            view2.setVisibility(View.VISIBLE);
//        }
//    };
}