package com.example.user.coalert.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.emailRedundancyCheckModel;
import com.google.gson.JsonObject;

import java.util.regex.Pattern;

import retrofit2.Call;

public class EmailSignUpActivity extends AppCompatActivity {
    Button emailRedundancyCheckBtn;
    EditText inputEmail;
    EditText password;
    EditText confirmPassword;
    EditText name;
    Button lastButton;
    Intent nextPageIntent;
    ImageView passwordCheckImg;
    static final String TRUE = "True";
    static final String FALSE = "False";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_sign_up);
        passwordCheckImg = findViewById(R.id.passwordCheck);
        emailRedundancyCheckBtn = (Button) findViewById(R.id.email_redundancy_check_btn);
        confirmPassword = findViewById(R.id.password_certification);
        password = findViewById(R.id.input_password);
        name = findViewById(R.id.emailSignUpInputname);
        inputEmail = findViewById(R.id.input_email);
        lastButton = findViewById(R.id.lastSignUpButton);
        lastButton.setClickable(false);
        final Context context = this;

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String confirmedPassword = String.valueOf(confirmPassword.getText());
                String inputPassword= String.valueOf(password.getText());
                if(confirmedPassword.equals(inputPassword)){
                    passwordCheckImg.setVisibility(View.VISIBLE);
                    passwordCheckImg.setImageResource(R.drawable.password_check);
                }else{
                    passwordCheckImg.setVisibility(View.INVISIBLE);
                }

            }
        });

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
                                lastButton.setClickable(true);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "사용 가능한 아이디입니다.", Toast.LENGTH_LONG).show();
                                    }
                                });
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
        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!password.getText().equals("") && !name.getText().equals("") && !confirmPassword.getText().equals("") && isEmail(String.valueOf(inputEmail .getText()))) {
                    if (String.valueOf(password.getText()).equals(String.valueOf(confirmPassword.getText()))) {
                        nextPageIntent = new Intent(getApplicationContext(), CommonSignUpActivity.class);
                        nextPageIntent.putExtra("name", String.valueOf(name.getText()));
                        nextPageIntent.putExtra("email", String.valueOf(inputEmail.getText()));
                        nextPageIntent.putExtra("password", String.valueOf(password.getText()));
                        startActivity(nextPageIntent);
                    }else{
                        Log.e("lastButton ClickEvent4", "false");
                        Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호, 이름을 확인해주세요", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.e("lastButton ClickEvent4", "false");
                    Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호, 이름을 확인해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public static boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches(
                "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",
                email.trim());
        return b;
    }
}
