package com.example.user.coalert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Autehntification.SessionCallback;
import com.example.user.coalert.R;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommonSignUpActivity extends AppCompatActivity{
    TextView tv1,tv2;
    ImageView profile;
    Bitmap bitmap;
    SessionCallback callback;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_sign_up);
        tv1=(TextView)findViewById(R.id.name);
        tv2=(TextView)findViewById(R.id.id);
        profile=(ImageView)findViewById(R.id.image);
        tv1.setText(" JAVA 32bit");
        Intent info=getIntent();
        final String name=info.getExtras().getString("name");
        final String id=info.getExtras().getString("id");
        final String image=info.getExtras().getString("image");
        tv1.setText(name);
        tv2.setText(id);

        Thread mThread=new Thread(){
            @Override
            public void run() {
                try{
                    URL url=new URL(image);

                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is=conn.getInputStream();
                    bitmap= BitmapFactory.decodeStream(is);

                }catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };

        mThread.start();
        try{
            mThread.join();
            profile.setImageBitmap(bitmap);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        //callback = new SessionCallback();
        //Session.getCurrentSession().addCallback(callback);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }


            String resultMsg = data.getStringExtra("name");
            tv1.setText(resultMsg);

            Toast.makeText(this, "결과 : " + resultMsg, Toast.LENGTH_SHORT).show();

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

//    public void requestProfile() {
//        KakaoTalkService.getInstance().requestProfile(new KakaoTalkResponseCallback<KakaoTalkProfile>() {
//            @Override
//            public void onSuccess(KakaoTalkProfile talkProfile) {
//                final String nickName = talkProfile.getNickName();
//                final String profileImageURL = talkProfile.getProfileImageURL();
//                final String thumbnailURL = talkProfile.getThumbnailURL();
//                final String countryISO = talkProfile.getCountryISO();
//            }
//        });
//    }
}
