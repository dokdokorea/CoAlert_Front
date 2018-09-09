package com.example.user.coalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.login.Login;


public class LoginActivity extends AppCompatActivity {

    ImageButton kakao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kakao=findViewById(R.id.loginbtn);

        kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Loading1Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
