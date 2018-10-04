package com.example.user.coalert.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Loading.Loading1Activity;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.Singleton.UUFiSingleton;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class LoginActivity extends AppCompatActivity {
    Button login_button;
    ImageView fakeFacebook;
    LoginButton facebookLoginBtn;
    com.kakao.usermgmt.LoginButton kakaoLoginBtn;
    ImageButton fakekakao;
    SessionCallback callback;
    ImageButton emailSignUpBtn;
    Intent itent;
    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.email_login);
        login_button.setOnClickListener(loginClickListener);
        emailSignUpBtn = findViewById(R.id.signup);
        // kakao = findViewById(R.id.kakao_login_button);


        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        permissionCheck();

        /*kakaotalk button syncrhonize with real kakao button*/
        fakekakao = findViewById(R.id.kakao_login_button);
        kakaoLoginBtn = findViewById(R.id.loginButton);
        fakekakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kakaoLoginBtn.performClick();
            }
        });

        /*facebook button synchronize with real fb button*/
        fakeFacebook = (ImageView) findViewById(R.id.fake_facebook);
        facebookLoginBtn = (LoginButton) findViewById(R.id.fb_login_button);
        fakeFacebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                facebookLoginBtn.performClick();
            }
        });

//        kakao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, Loading1Activity.class);
//                //getAppKeyHash();
//                getHashKey();
//                startActivity(intent);
//                finish();
//            }
//        });

        emailSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, EmailSignUpActivity.class);
                startActivity(intent);
            }
        });
    }


    Button.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Intent accessActivity = new Intent(LoginActivity.this, AccessAuthorizationActivity.class);
            startActivity(accessActivity);
            finish();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EditText loginEmail = findViewById(R.id.login_email);
                        EditText loginPassword = findViewById(R.id.login_password);
                        String email = loginEmail.getText().toString();
                        String password = loginPassword.getText().toString();
                        password = testSHA256(password);
                        getUUID(getBaseContext());
                        Log.e("asdasd", email);
                        Call call = ForRestSingleton.getInstance().loginCall(email, password);
                        Object result = call.execute().body();
                        Log.e("result: ", result.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };

    void getUUID(Context mContext) {
        TelephonyManager mgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint({"HardwareIds", "MissingPermission"}) String idByTelephonyManager = mgr.getDeviceId();
        Log.e("UUID: ", idByTelephonyManager);
        UUFiSingleton.getInstance().setIndependenceNum(idByTelephonyManager);
    }

    public String testSHA256(String str) {

        String SHA = "";

        try {

            MessageDigest sh = MessageDigest.getInstance("SHA-256");

            sh.update(str.getBytes());

            byte byteData[] = sh.digest();

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++) {

                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            }

            SHA = sb.toString();


        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

            SHA = null;

        }

        return SHA;

    }

    void permissionCheck() {
        int ReadStoragetPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int ReadAudioPermmission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int WriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (ReadAudioPermmission != PackageManager.PERMISSION_GRANTED && ReadStoragetPermmission != PackageManager.PERMISSION_GRANTED &&
                WriteStorage != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                        1000);
            }
        }
    }
    /*카카오톡 연동하기(키해
    /*카카오톡 연동하기(키해시 받아오기) 안돼*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }


    private void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.user.coalert", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("key_hash=", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        //에러로 인한 로그인 실패
//                        finish();
                    } else {
                        //redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.

                    //Log.e("UserProfile", userProfile.toString());
                    // Log.e("UserProfile", userProfile.getId() + "");

                    //long number = userProfile.getId();
                    //Log.e("UserProfile", number + "");

                    Toast.makeText(LoginActivity.this, String.valueOf(userProfile.getId()), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, Loading1Activity.class);
                    intent.putExtra("name", userProfile.getNickname().toString());
                    intent.putExtra("id", String.valueOf(userProfile.getId()));
                    intent.putExtra("image", userProfile.getProfileImagePath());

                    startActivity(intent);
                    finish();


                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            // 세션 연결이 실패했을 시 로그인화면 다시 불러옴
            if (exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.activity_login);

        }
    }


}



