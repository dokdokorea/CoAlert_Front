package com.example.user.coalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.util.helper.log.Logger;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CommonSignUpActivity extends AppCompatActivity{
    TextView tv1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_sign_up);
        tv1=(TextView)findViewById(R.id.text);
        tv1.setText(" JAVA 32bit");
    }

//    private void requestMe() {
//        List<String> keys = new ArrayList<>();
//        keys.add("properties.nickname");
//        keys.add("properties.profile_image");
//        keys.add("kakao_account.email");
//
//        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
//            @Override
//            public void onFailure(ErrorResult errorResult) {
//                String message = "failed to get user info. msg=" + errorResult;
//                Logger.d(message);
//            }
//
//            @Override
//            public void onSessionClosed(ErrorResult errorResult) {
//                redirectLoginActivity();
//            }
//
//            @Override
//            public void onSuccess(MeV2Response response) {
//                Logger.d("user id : " + response.getId());
//                Logger.d("email: " + response.getKakaoAccount().getEmail());
//                Logger.d("profile image: " + response.getKakaoAccount()
//                        .getProfileImagePath());
//                redirectMainActivity();
//            }
//
//            @Override
//            public void onNotSignedUp() {
//                showSignup();
//            }
//        });
//    }

    protected void showSignup(){
        redirectLoginActivity();
    }
    private void redirectMainActivity(){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
    protected void redirectLoginActivity(){
        final Intent intent=new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
