package com.example.user.coalert.Loading;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.coalert.Activity.CommonSignUpActivity;
import com.example.user.coalert.Activity.MainActivity;
import com.example.user.coalert.R;
import com.facebook.common.Common;

public class Loading1Activity extends AppCompatActivity {
    private ImageView loadingImg;
    private Animation anim;
    String resultMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading1);
        final Intent info=getIntent();
        final String name=info.getExtras().getString("name");
        final String id=info.getExtras().getString("id");
        final String image=info.getExtras().getString("image");
        initView();

        Handler handler=new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                Intent intent=new Intent(Loading1Activity.this,CommonSignUpActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("id",id);
                intent.putExtra("image",image);
                startActivity(intent);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,2000);
    }

    private void initView(){
        loadingImg = (ImageView) findViewById(R.id.icon);
        anim = AnimationUtils.loadAnimation(this, R.anim.loading);
        anim.setInterpolator(this,android.R.anim.decelerate_interpolator);
        loadingImg.setAnimation(anim);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

        resultMsg = data.getStringExtra("name");

        Toast.makeText(this, "결과 : " + resultMsg, Toast.LENGTH_SHORT).show();

    }

}