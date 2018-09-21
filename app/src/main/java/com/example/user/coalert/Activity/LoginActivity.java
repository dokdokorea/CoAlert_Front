package com.example.user.coalert.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.user.coalert.Loading.Loading1Activity;
import com.example.user.coalert.R;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    Button login_button;
    ImageView fakeFacebook;
    LoginButton facebookLoginBtn;
    ImageButton kakao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.email_login);
        login_button.setOnClickListener(loginClickListener);
        kakao=findViewById(R.id.kakao_login_button);
        permissonCheck();

        /*facebook button synchronize with real fb button*/
        fakeFacebook = (ImageView) findViewById(R.id.fake_facebook);
        facebookLoginBtn = (LoginButton) findViewById(R.id.fb_login_button);
        fakeFacebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                facebookLoginBtn.performClick();
            }
        });

        kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Loading1Activity.class);
                startActivity(intent);
                finish();
            }
        });

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
    /*카카오톡 연동하기(키해시 받아오기) 안돼*/
}
