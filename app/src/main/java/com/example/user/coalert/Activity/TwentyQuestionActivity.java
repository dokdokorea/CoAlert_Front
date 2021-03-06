package com.example.user.coalert.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.transitionseverywhere.TransitionManager;

public class TwentyQuestionActivity extends AppCompatActivity {
    TextView question, Answer;
    Button No, Soso, Yes, GoType,ReTest;
    int prev = 0, answer;
    LinearLayout QuestionPage, ResultPage;
    ViewGroup transitionsContainer;
    String QList[] = {"모공이 크지 않다", "모공이 매우 작다", "모공이 매우 크다", "모공의 크기가 적당하다", "모공의 크기가\n부분적으로 다르다"
            , "눈 입 주위에\n잔주름이 많다", "겨울철은 당기고\n여름철은 유분이 많다", "피부가 두껍고,\n피지분비가 많다", "윤기가 없고 \n피부도 칙칙하다",
            "얼굴이 자주 빨개진다", "피부톤이 밝고\n고른편이다", "환절기에\n피부트러블이 생긴다", "피부톤이\n전체적으로 어둡다", "세안 후 당기고 T존 부위는\n유분이 많다",
            "얼굴이 많이 건조하다", "피부에 윤기가 없다", "나이에 비해\n피부가 좋다", "화장이 잘 지워지고\n유분이 많다", "T존 부위만 화장이\n잘 지워진다", "피곤하면 트러블이\n생긴다",
            "세안 후 얼굴이\n전체적으로 당긴다", "화장이 오래 간다", "여드름성 트러블이\n잘 일어난다", "얼굴이 부분적으로\n번들거린다", "화장품이나 비누를 바꾸면\n트러블이 생긴다"};
    String ResultList[] = {"건성피부", "일반피부", "지성피부", "복합성피부", "민감성피부"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_question);

        question = (TextView) findViewById(R.id.question);
        No = (Button) findViewById(R.id.nobtn);
        Soso = (Button) findViewById(R.id.sosobtn);
        Yes = (Button) findViewById(R.id.yesbtn);
        QuestionPage = (LinearLayout) findViewById(R.id.question_page);
        transitionsContainer = (ViewGroup) findViewById(R.id.container);
        Answer = (TextView) findViewById(R.id.skin_type_result);
        ResultPage=(LinearLayout)findViewById(R.id.result_view);
        ReTest=(Button)findViewById(R.id.retest);
        GoType=(Button)findViewById(R.id.gotype);

        question.setText(QList[0]);

        No.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 0;
                prev = getQuestion(prev, answer);

                setView(prev);
                //setfinal();
            }
        });

        Soso.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 1;
                prev = getQuestion(prev, answer);

                setView(prev);
            }
        });

        Yes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 2;
                prev = getQuestion(prev, answer);

                setView(prev);
            }
        });

        ReTest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 0;
                prev = 0;
                TransitionManager.beginDelayedTransition(transitionsContainer);
                QuestionPage.setVisibility(View.VISIBLE);
                ResultPage.setVisibility(View.INVISIBLE);
                setView(prev);
            }
        });

        GoType.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TwentyQuestionActivity.this,CommonSignUpActivity.class);

                intent.putExtra("type",Answer.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }
    private void setView(int result){

        TransitionManager.beginDelayedTransition(transitionsContainer);
        Soso.setVisibility(View.GONE);

        if (result == 18 || result == 19) {
            TransitionManager.beginDelayedTransition(transitionsContainer);
            Soso.setVisibility(View.GONE);
        } else {
            Soso.setVisibility(View.VISIBLE);

        }

        if (result > 24) {
            TransitionManager.beginDelayedTransition(transitionsContainer);
            QuestionPage.setVisibility(View.INVISIBLE);
            ResultPage.setVisibility(View.VISIBLE);
            Answer.setText(ResultList[result-25]);
        } else
            question.setText(QList[prev]);

    }

    private void setfinal(){
        TransitionManager.beginDelayedTransition(transitionsContainer);
        QuestionPage.setVisibility(View.INVISIBLE);
        ResultPage.setVisibility(View.VISIBLE);
    }

    private int getQuestion(int prev, int answer) {
        int next = 0;
        switch (prev) {

            case 0:
                switch (answer) {
                    case 0:
                        next = 1;
                        break;
                    case 1:
                        next = 6;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 1:
                switch (answer) {
                    case 0:
                        next = 2;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 2:
                switch (answer) {
                    case 0:
                        next = 3;
                        break;
                    case 1:
                        next = 8;
                        break;
                    case 2:
                        next = 7;
                        break;
                }
                break;

            case 3:
                switch (answer) {
                    case 0:
                        next = 4;
                        break;
                    case 1:
                        next = 5;
                        break;
                    case 2:
                        next = 6;
                        break;
                }
                break;

            case 4:
                switch (answer) {
                    case 0:
                        next = 5;
                        break;
                    case 1:
                        next = 8;
                        break;
                    case 2:
                        next = 9;
                        break;
                }
                break;

            case 5:
                switch (answer) {
                    case 0:
                        next = 6;
                        break;
                    case 1:
                        next = 11;
                        break;
                    case 2:
                        next = 10;
                        break;
                }
                break;

            case 6:
                switch (answer) {
                    case 0:
                        next = 7;
                        break;
                    case 1:
                        next = 12;
                        break;
                    case 2:
                        next = 11;
                        break;
                }
                break;

            case 7:
                switch (answer) {
                    case 0:
                        next = 8;
                        break;
                    case 1:
                        next = 13;
                        break;
                    case 2:
                        next = 12;
                        break;
                }
                break;

            case 8:
                switch (answer) {
                    case 0:
                        next = 9;
                        break;
                    case 1:
                        next = 14;
                        break;
                    case 2:
                        next = 13;
                        break;
                }
                break;

            case 9:
                switch (answer) {
                    case 0:
                        next = 10;
                        break;
                    case 1:
                        next = 10;
                        break;
                    case 2:
                        next = 14;
                        break;
                }
                break;

            case 10:
                switch (answer) {
                    case 0:
                        next = 11;
                        break;
                    case 1:
                        next = 17;
                        break;
                    case 2:
                        next = 15;
                        break;
                }
                break;

            case 11:
                switch (answer) {
                    case 0:
                        next = 12;
                        break;
                    case 1:
                        next = 15;
                        break;
                    case 2:
                        next = 16;
                        break;
                }
                break;

            case 12:
                switch (answer) {
                    case 0:
                        next = 13;
                        break;
                    case 1:
                        next = 16;
                        break;
                    case 2:
                        next = 17;
                        break;
                }
                break;

            case 13:
                switch (answer) {
                    case 0:
                        next = 14;
                        break;
                    case 1:
                        next = 17;
                        break;
                    case 2:
                        next = 18;
                        break;
                }
                break;

            case 14:
                switch (answer) {
                    case 0:
                        next = 16;
                        break;
                    case 1:
                        next = 18;
                        break;
                    case 2:
                        next = 19;
                        break;
                }
                break;

            case 15:
                switch (answer) {
                    case 0:
                        next = 16;
                        break;
                    case 1:
                        next = 21;
                        break;
                    case 2:
                        next = 20;
                        break;
                }
                break;

            case 16:
                switch (answer) {
                    case 0:
                        next = 17;
                        break;
                    case 1:
                        next = 23;
                        break;
                    case 2:
                        next = 21;
                        break;
                }
                break;

            case 17:
                switch (answer) {
                    case 0:
                        next = 18;
                        break;
                    case 1:
                        next = 23;
                        break;
                    case 2:
                        next = 22;
                        break;
                }
                break;

            case 18:
                switch (answer) {
                    case 0:
                        next = 19;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 23;
                        break;
                }
                break;

            case 19:
                switch (answer) {
                    case 0:
                        next = 10;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 24;
                        break;
                }
                break;

            case 20:
                switch (answer) {
                    case 0:
                        next = 21;
                        break;
                    case 1:
                        next = 26;
                        break;
                    case 2:
                        next = 25;
                        break;
                }
                break;

            case 21:
                switch (answer) {
                    case 0:
                        next = 22;
                        break;
                    case 1:
                        next = 25;
                        break;
                    case 2:
                        next = 26;
                        break;
                }
                break;

            case 22:
                switch (answer) {
                    case 0:
                        next = 23;
                        break;
                    case 1:
                        next = 28;
                        break;
                    case 2:
                        next = 27;
                        break;
                }
                break;

            case 23:
                switch (answer) {
                    case 0:
                        next = 24;
                        break;
                    case 1:
                        next = 27;
                        break;
                    case 2:
                        next = 28;
                        break;
                }
                break;

            case 24:
                switch (answer) {
                    case 0:
                        next = 17;
                        break;
                    case 1:
                        next = 28;
                        break;
                    case 2:
                        next = 29;
                        break;
                }
                break;


        }

        return next;
    }
}
