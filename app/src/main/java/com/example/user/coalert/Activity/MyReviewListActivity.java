package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.coalert.Adapter.CosmeticInformationAdapter.DetailReviewPreviewAdapter;
import com.example.user.coalert.Adapter.CosmeticInformationAdapter.SimpleReviewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
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
    ArrayList<OneImgOneStringOneNumberCardView> SimpleArr;
    ArrayList<TwoImgFourStringCardView> DetailPreviewArr;
    ViewGroup transitionsContainer;
    boolean visible=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        transitionsContainer = (ViewGroup) findViewById(R.id.container);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_reviews);

        simple = (RecyclerView) findViewById(R.id.view_simple_review);
        simple.setHasFixedSize(true);
        simple.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        SimpleArr = new ArrayList<>();
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin, "괜찮았어요!", 4));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.face1, "음...뭐 그럭저럭?", 3));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.irin, "완죤강추 인생템이예요ㅠㅠㅠ", 5));
        SimpleArr.add(new OneImgOneStringOneNumberCardView(R.drawable.face1, "별로임", 2));
        simple.setAdapter(new SimpleReviewAdapter(SimpleArr));

        detail=(RecyclerView)findViewById(R.id.view_detail_review);
        detail.setHasFixedSize(true);
        detail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DetailPreviewArr=new ArrayList<>();
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.irin,R.drawable.sun1,"핫한핫산","100","나는똥글을 싸지를것이다!","뿌직뿌직뿌지지지지지지직"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.iu7,R.drawable.sun1,"아이유","1000","밤편지","난~~~ 파도가 머~~물~던 모래 위에 적힌 글씨처럼~~~ 그대가 멀리~~~ 사라져 버릴 것 같아~~~~~"));
        DetailPreviewArr.add(new TwoImgFourStringCardView(R.drawable.nayeon1,R.drawable.sun1,"나나연","500","나는나연","일은 열심히 하셨나연? 배고프지않나연?"));
        detail.setAdapter(new DetailReviewPreviewAdapter(DetailPreviewArr));

    }

    public void viewSimple(View v) {
        //TransitionManager.beginDelayedTransition(transitionsContainer);
        visible = !visible;
        simple.setVisibility(visible ? View.VISIBLE : View.GONE);
        //view2.setVisibility(View.GONE);

    }

    public void viewDetail(View v) {
        //TransitionManager.beginDelayedTransition(transitionsContainer);
        visible = !visible;
        detail.setVisibility(visible ? View.VISIBLE : View.GONE);
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