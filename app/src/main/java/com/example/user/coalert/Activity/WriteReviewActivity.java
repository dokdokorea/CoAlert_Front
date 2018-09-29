package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import com.example.user.coalert.R;

public class WriteReviewActivity extends AppCompatActivity {
    private final int MaxLengthOfOneLineContent=100;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
   EditText editText=(EditText)findViewById(R.id.one_line);
   editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MaxLengthOfOneLineContent)});

    }
}
