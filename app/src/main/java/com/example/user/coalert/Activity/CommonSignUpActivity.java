package com.example.user.coalert.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.Autehntification.SessionCallback;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.signUpModel;


import java.io.IOException;

import retrofit2.Call;

public class CommonSignUpActivity extends AppCompatActivity {
    TextView tv2;
    ImageView profile;
    Bitmap bitmap;
    SessionCallback callback;
    Button lastButton, goQuestionButton;
    String completeYear, completeMonth, completeDay, selectedSkinType;
    Integer selectedDay, selectedMonth, selectedSex;
    String email, name, password;
    Intent beforePageInfo;
    RadioGroup sexRadioGroup;
    RadioButton anyone;
    ImageView skinImg;
    LinearLayout showQuestion;
    int strNum=0;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_sign_up);
        tv2 = findViewById(R.id.id);
        profile = findViewById(R.id.image);
        lastButton = findViewById(R.id.common_sign_up_confirmation_btn);
        sexRadioGroup = findViewById(R.id.sexRadioGroup);
        skinImg = findViewById(R.id.skin_type_image);
        goQuestionButton=findViewById(R.id.goTwenty);
        showQuestion=findViewById(R.id.showGobtn);
        beforePageInfo = getIntent();
        name = beforePageInfo.getStringExtra("name");
        email = beforePageInfo.getStringExtra("email");
        password = beforePageInfo.getStringExtra("password");

        Intent intent=getIntent();
        String result=intent.getExtras().getString("type","");
        if(result!=null) {
            setImgbySpinner(result);
        }

        goQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CommonSignUpActivity.this,TwentyQuestionActivity.class);
                startActivity(intent);
            }
        });


        //에러나서 주석처리 했어요~
  /*      Log.e("email", email);
        Log.e("password", password);
        Log.e("name", name);*/

//        final String name=info.getExtras().getString("name");
//        final String id=info.getExtras().getString("id");
//        final String image=info.getExtras().getString("image");
//        tv1.setText(name);
//        tv2.setText(id);
//
//        Thread mThread=new Thread(){
//            @Override
//            public void run() {
//                try{
//                    URL url=new URL(image);
//
//                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//                    conn.setDoInput(true);
//                    conn.connect();
//
//                    InputStream is=conn.getInputStream();
//                    bitmap= BitmapFactory.decodeStream(is);
//
//                }catch(MalformedURLException e){
//                    e.printStackTrace();
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        mThread.start();
//        try{
//            mThread.join();
//            profile.setImageBitmap(bitmap);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }


        //callback = new SessionCallback();
        //Session.getCurrentSession().addCallback(callback);

        //YearSpinner
        final Spinner yearSpinner = (Spinner) findViewById(R.id.spinner_year);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_year, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        //MonthSpinner
        final Spinner monthSpinner = (Spinner) findViewById(R.id.spinner_month);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_month, android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        //DaySpinner
        final Spinner daySpinner = (Spinner) findViewById(R.id.spinner_day);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_day, android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                completeYear = (String) yearSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMonth = Integer.parseInt(String.valueOf(monthSpinner.getSelectedItem()));
                if (selectedMonth < 10)
                    completeMonth = String.valueOf("0" + selectedMonth);
                else
                    completeMonth = String.valueOf(selectedMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedDay = Integer.parseInt(String.valueOf(daySpinner.getSelectedItem()));
                if (selectedDay < 10)
                    completeDay = String.valueOf("0" + selectedDay);
                else
                    completeMonth = String.valueOf(selectedMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final Spinner skinTypeSpinner = findViewById(R.id.spinner_skin_type);
        ArrayAdapter skinAdapter = ArrayAdapter.createFromResource(this,
                R.array.skin_type, android.R.layout.simple_spinner_dropdown_item);
        skinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skinTypeSpinner.setAdapter(skinAdapter);

        skinTypeSpinner.setSelection(0);

        skinTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSkinType = (String) skinTypeSpinner.getSelectedItem().toString();
                setImgbySpinner(selectedSkinType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        try {
//                            anyone = findViewById(sexRadioGroup.getCheckedRadioButtonId());
//                            String fullBirth = completeYear + "-" + completeMonth + "-" + completeDay;
//                            Log.e("skitType", selectedSkinType + anyone.getText());
//                            Call<signUpModel> call = ForRestSingleton.getInstance().signUpCall(email, password, name, email, selectedSkinType, fullBirth, anyone.getText(), 0);
//                            signUpModel result = call.execute().body();
//                            Log.e("signUpmodel Result: ", result.toString());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
                Toast.makeText(getApplicationContext(), "로그인 되셨습니다", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void setImgbySpinner(String selectedSkinType) {
        switch (selectedSkinType) {
            case "피부타입선택":
                Drawable img=getResources().getDrawable(R.drawable.normalskin);
                skinImg.setImageDrawable(img);
                skinImg.setVisibility(View.GONE);
                showQuestion.setVisibility(View.VISIBLE);
                strNum=0;
                break;
            case "지성":
                showQuestion.setVisibility(View.GONE);
                skinImg.setVisibility(View.VISIBLE);
                img = getResources().getDrawable(R.drawable.oilyskin);
                skinImg.setImageDrawable(img);
                strNum=1;
                break;
            case "건성":
                showQuestion.setVisibility(View.GONE);
                skinImg.setVisibility(View.VISIBLE);
                img = getResources().getDrawable(R.drawable.dryskin);
                skinImg.setImageDrawable(img);
                strNum=2;
                break;
            case "민감성":
                showQuestion.setVisibility(View.GONE);
                skinImg.setVisibility(View.VISIBLE);
                img = getResources().getDrawable(R.drawable.sensitiveskin);
                skinImg.setImageDrawable(img);
                strNum=3;
                break;
            case "불확정성":
                strNum=4;
                break;
            default:
                strNum=0;
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "결과가 성공이 아님.", Toast.LENGTH_SHORT).show();
            return;
        }


        String resultMsg = data.getStringExtra("name");
       // tv1.setText(resultMsg);

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

    protected void showSignup() {
        redirectLoginActivity();
    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
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
