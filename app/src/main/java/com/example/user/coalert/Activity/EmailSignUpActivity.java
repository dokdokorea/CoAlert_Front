package com.example.user.coalert.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.emailRedundancyCheckModel;
import com.google.gson.JsonObject;

import retrofit2.Call;

public class EmailSignUpActivity extends AppCompatActivity {
    Button emailRedundancyCheckBtn;
    EditText inputEmail;
    Button lastButton;
    AlertDialog.Builder alertDialogBuilder;
    static final String TRUE = "True";
    static final String FALSE = "False";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_sign_up);
        emailRedundancyCheckBtn = (Button) findViewById(R.id.email_redundancy_check_btn);
        inputEmail = findViewById(R.id.input_email);
        lastButton = findViewById(R.id.lastSignUpButton);
        final Context context=this;
        emailRedundancyCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Call<emailRedundancyCheckModel> call = ForRestSingleton.getInstance().emailCheck(String.valueOf(inputEmail.getText()));
                            emailRedundancyCheckModel result = call.execute().body();
                            assert result != null;
                            if (result.isEmailCheck()) {
                                Log.e("Asdasd", "True");
                            } else {
                                lastButton.setClickable(false);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                context);

                                        // AlertDialog 셋팅
                                        alertDialogBuilder
                                                .setMessage("동일한 이메일이 존재합니다. 다른 아이디를 적어주세요.")
                                                .setCancelable(false)
                                                .setPositiveButton("확인",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(
                                                                    DialogInterface dialog, int id) {
                                                                // 프로그램을 종료한다
                                                                finish();
                                                            }
                                                        })
                                                .setNegativeButton("취소",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(
                                                                    DialogInterface dialog, int id) {
                                                                // 다이얼로그를 취소한다
                                                                dialog.cancel();
                                                            }
                                                        });

                                        // 다이얼로그 생성
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        // 다이얼로그 보여주기
                                        alertDialog.show();
                                    }
                                });


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
