package com.example.user.coalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.coalert.Popup.BreakPopup;
import com.example.user.coalert.R;

public class EditprofileActivity extends AppCompatActivity{
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        EditText name=findViewById(R.id.name);
        Spinner sex=findViewById(R.id.sex);
        Spinner year=findViewById(R.id.year);
        Spinner month=findViewById(R.id.month);
        Spinner day=findViewById(R.id.day);
        Button broke=findViewById(R.id.broke);
        Button accept=findViewById(R.id.editbtn);
    }

    public void FloatingPopup(View v){
        Intent intent=new Intent(this, BreakPopup.class);
        startActivity(intent);
    }

}
