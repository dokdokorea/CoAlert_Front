package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.user.coalert.R;

public class EmailSignUpActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_sign_up);

        //YearSpinner
        Spinner yearSpinner=(Spinner)findViewById(R.id.spinner_year);
        ArrayAdapter yearAdapter=ArrayAdapter.createFromResource(this,
                R.array.date_year,android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        //MonthSpinner
        Spinner monthSpinner=(Spinner)findViewById(R.id.spinner_month);
        ArrayAdapter monthAdapter=ArrayAdapter.createFromResource(this,
                R.array.date_month,android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        //DaySpinner
        Spinner daySpinner=(Spinner)findViewById(R.id.spinner_day);
        ArrayAdapter dayAdapter=ArrayAdapter.createFromResource(this,
                R.array.date_day,android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
    }
}
