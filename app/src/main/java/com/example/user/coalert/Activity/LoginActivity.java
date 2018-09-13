package com.example.user.coalert.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.coalert.R;

public class LoginActivity extends AppCompatActivity {
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.email_login);
        login_button.setOnClickListener(loginClickListener);
        permissonCheck();
    }

    Button.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent accessActivity = new Intent(LoginActivity.this, AccessAuthorizationActivity.class);
            startActivity(accessActivity);
            finish();
        }
    };


    void permissonCheck() {
        int ReadStoragetPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int ReadAudioPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int WriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ReadAudioPermmission != PackageManager.PERMISSION_GRANTED && ReadStoragetPermmission != PackageManager.PERMISSION_GRANTED &&
                WriteStorage != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        }
    }
}
