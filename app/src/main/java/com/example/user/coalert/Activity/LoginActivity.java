package com.example.user.coalert.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.Loading.Loading1Activity;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.Singleton.UUFiSingleton;
import com.example.user.coalert.forRestServer.loginModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
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
    Bitmap bitmap;
    Intent itent;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private CallbackManager callbackManager = CallbackManager.Factory.create();


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

        callbackManager = CallbackManager.Factory.create();

        //facebookLoginBtn.setReadPermissions("email");

        facebookLoginBtn.setReadPermissions(Arrays.asList("public_profile", "email"));


        facebookLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result",object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
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
            Toast.makeText(getApplicationContext(), "서버와의 연결이 실패했습니다.", Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @SuppressLint("ShowToast")
                @Override
                public void run() {
                    try {
                        final EditText loginEmail = findViewById(R.id.login_email);
                        EditText loginPassword = findViewById(R.id.login_password);
                        String email = loginEmail.getText().toString();
                        String password = loginPassword.getText().toString();
                        password = testSHA256(password);
                        getUUID(getBaseContext());
                        Call<loginModel> call = ForRestSingleton.getInstance().loginCall(email, password);
                        loginModel result = call.execute().body();
                        Log.e("asdads", result.toString());
                        final String canYouLogin = result.getError();
//                        if(canYouLogin.equals("null")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    GlobalApplication info=(GlobalApplication) getApplication();
                                    info.setId(loginEmail.getText().toString());
                                    Intent accessActivity = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(accessActivity);
                                    Toast.makeText(getApplicationContext(), info.getId()+"님 환영합니다!", Toast.LENGTH_LONG).show();
                                    finish();
                                    }
                            });

//                        }else{
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(getApplicationContext(), canYouLogin, Toast.LENGTH_LONG).show();
//                                }
//                            });
//                        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookLoginOnClick(View v){
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult result) {

                GraphRequest request;
                request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (response.getError() != null) {
                            Toast.makeText(LoginActivity.this,"로그인정보가 일치하지않습니다..", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.i("TAG", "user: " + user.toString());
                            Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
                            setResult(RESULT_OK);
                            GlobalApplication info=(GlobalApplication) getApplication();
                            info.setId(Profile.getCurrentProfile().getId());

//                            info.setEmail(Profile.getCurrentProfile());
                            info.setProfile(LoadImageFromWebOperations(Profile.getCurrentProfile().getProfilePictureUri(200,200).toString()));
                            Intent i = new Intent(LoginActivity.this, CommonSignUpActivity.class);
                            Toast.makeText(LoginActivity.this, Profile.getCurrentProfile()+"님 환영합니다!", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                //finish();
            }

            @Override
            public void onCancel() {
                //finish();
            }
        });
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }


//  kakao
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
//            return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

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

    private Drawable LoadImageFromWebOperations(String url)
    {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        }catch (Exception e) {
            System.out.println("Exc="+e);
            return null;
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

//                    Toast.makeText(LoginActivity.this, String.valueOf(userProfile.getId()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, userProfile.getNickname()+"님 환영합니다!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, Loading1Activity.class);
                    intent.putExtra("name", userProfile.getNickname());
                    intent.putExtra("id", String.valueOf(userProfile.getId()));

                    GlobalApplication info=(GlobalApplication) getApplication();
                    info.setId(userProfile.getNickname());
                    info.setEmail(userProfile.getProperty("kakao_acount.email"));
                    info.setEmail("example@gmail.com");
//                    info.setProfile(LoadImageFromWebOperations(Profile.getCurrentProfile().getProfilePictureUri(200,200).toString()));
//                    info.setIprofile(getResources().getIdentifier(userProfile.getProfileImagePath(), "drawable", getPackageName()));
                    intent.putExtra("image", userProfile.getProfileImagePath());
                    final String image=userProfile.getProfileImagePath();
//                    info.setProfile(getResources().getDrawable(getResources().getIdentifier(userProfile.getProfileImagePath(), "drawable", getPackageName())));
//                    info.setIprofile(Integer.parseInt(userProfile.getProfileImagePath()));

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
            info.setBitmap(bitmap);
//            profile.setImageBitmap(bitmap);
        }catch(InterruptedException e){
            e.printStackTrace();
        }



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



