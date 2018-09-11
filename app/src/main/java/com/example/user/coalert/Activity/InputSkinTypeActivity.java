package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.user.coalert.R;

public class InputSkinTypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_skin_type);

        //SkinType Spinner
        Spinner skinTypeSpinner=(Spinner)findViewById(R.id.spinner_skin_type);
        ArrayAdapter skinTypeAdapter=ArrayAdapter.createFromResource(this,
                R.array.skin_type,android.R.layout.simple_spinner_item);
        skinTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skinTypeSpinner.setAdapter(skinTypeAdapter);
    }
}
