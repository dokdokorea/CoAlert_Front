package com.example.user.coalert.Activity;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.coalert.R;

import java.util.List;

public class LeftNavigationContentActivity extends AppCompatActivity{
    static final String[] COMPANY_LIST={"innisfree","아리따움","LUSH","THE FACE SHOP"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.left_navigation_content);

        ArrayAdapter adapter=new ArrayAdapter(this, R.layout.company_list_item,COMPANY_LIST);

        ListView listView=(ListView)findViewById(R.id.company_listview);
        listView.setAdapter(adapter);
    }
}


