package com.example.user.coalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.coalert.R;

public class AccessAuthorizationActivity extends AppCompatActivity {
    Button accessBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_authorization);
        accessBtn = findViewById(R.id.authorization_confirm_button);
        accessBtn.setOnClickListener(mainClickListener);
    }

    Button.OnClickListener mainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent mainActivity = new Intent(AccessAuthorizationActivity.this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
    };
}
