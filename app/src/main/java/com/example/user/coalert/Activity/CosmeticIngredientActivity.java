package com.example.user.coalert.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.example.user.coalert.Adapter.TabIngredListAdapter.DrySkinAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.OilySkinAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.SensitiveSkinAdapter;
import com.example.user.coalert.Adapter.TabIngredListAdapter.TabIngredientListAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

public class CosmeticIngredientActivity extends AppCompatActivity {
    RecyclerView oilySkinRecyclerView,drySkinRecyclerView, sensitiveRecyclerView;
    ArrayList<OneImgOneStringCardView> oilArr, dryArr, sensitiveArr;
    ViewGroup transitionsContainer;
    boolean visibleO=true,visibleD=true,visibleS=true;
    ImageButton backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_skintype);

        transitionsContainer = (ViewGroup) findViewById(R.id.container);
        backbtn=(ImageButton)findViewById(R.id.back_btn);

        oilySkinRecyclerView = (RecyclerView) findViewById(R.id.oily_skin_recycler_view);
        drySkinRecyclerView = (RecyclerView) findViewById(R.id.dry_skin_recycler_view);
        sensitiveRecyclerView = (RecyclerView) findViewById(R.id.sentitive_skin_recycler_view);

        oilySkinRecyclerView.setHasFixedSize(true);
        drySkinRecyclerView.setHasFixedSize(true);
        sensitiveRecyclerView.setHasFixedSize(true);

        oilySkinRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        drySkinRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        sensitiveRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        oilArr = new ArrayList<>();
        dryArr = new ArrayList<>();
        sensitiveArr = new ArrayList<>();


        oilArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "당신에게 행복을"));
        oilySkinRecyclerView.setAdapter(new OilySkinAdapter(oilArr));

        dryArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "당신에게 행복을"));
        drySkinRecyclerView.setAdapter(new DrySkinAdapter(dryArr));

        sensitiveArr.add(new OneImgOneStringCardView(R.drawable.cardview2, "당신에게 행복을"));
        sensitiveRecyclerView.setAdapter(new SensitiveSkinAdapter(sensitiveArr));


        backbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void viewOil(View v) {
        TransitionManager.beginDelayedTransition(transitionsContainer);
        visibleO = !visibleO;
        oilySkinRecyclerView.setVisibility(visibleO ? View.VISIBLE : View.GONE);
    }

    public void viewDry(View v) {
        TransitionManager.beginDelayedTransition(transitionsContainer);
        visibleD = !visibleD;
        drySkinRecyclerView.setVisibility(visibleD ? View.VISIBLE : View.GONE);
    }

    public void viewSensitive(View v) {
        TransitionManager.beginDelayedTransition(transitionsContainer);
        visibleS = !visibleS;
        sensitiveRecyclerView.setVisibility(visibleS ? View.VISIBLE : View.GONE);
    }


}
