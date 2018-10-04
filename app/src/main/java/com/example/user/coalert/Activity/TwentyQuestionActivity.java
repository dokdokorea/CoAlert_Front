package com.example.user.coalert.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.coalert.R;

public class TwentyQuestionActivity extends AppCompatActivity {
    TextView question;
    Button No, Soso, Yes;
    int prev = 0, answer;
    LinearLayout QuestionPage;
    //final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.container);
    String QList[] = {"모공이 크지 않다", "모공이 매우 작다", "모공이 매우 크다", "모공의 크기가 적당하다", "모공의 크기가 부분적으로 다르다"
            , "눈 입 주위에 잔주름이 많다", "겨울철은 당기고 여름철은 유분이 많다", "피부가 두껍고, 피지분비가 많다", "윤기가 없고 피부도 칙칙하다",
    "얼굴이 자주 빨개진다","피부톤이 밝고 고른편이다", "환절기에 피부트러블이 생긴다","피부톤이 전체적으로 어둡다","세안 후 당기고 T존 부위는 유분이 많다",
    "얼굴이 많이 건조하다","피부에 윤기가 없다","나이에 비해 피부가 좋다","화장이 잘 지워지고 유분이 많다","T존 부위만 화장이 잘 지워진다","피곤하면 트러블이 생긴다",
    "세안 후 얼굴이 전체적으로 당긴다","화장이 오래 간다", "여드름성 트러블이 잘 일어난다", "얼굴이 부분적으로 번들거린다", "화장품이나 비누를 바꾸면 트러블이 생긴다"};
    String ResultList[]={"aaaa","bbbb","cccc","dddd","eeee"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_question);

        question = (TextView) findViewById(R.id.question);
        No = (Button) findViewById(R.id.nobtn);
        Soso = (Button) findViewById(R.id.sosobtn);
        Yes = (Button) findViewById(R.id.yesbtn);
        QuestionPage=(LinearLayout)findViewById(R.id.question_page);

        question.setText(QList[0]);

        No.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 0;
                prev=getQuestion(prev, answer);

             if(prev>24){

             }
                question.setText(QList[prev]);
            }
        });

        Soso.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 1;
                prev=getQuestion(prev, answer);
                question.setText(QList[prev]);
            }
        });

        Yes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = 2;
                prev=getQuestion(prev, answer);
                question.setText(QList[prev]);
            }
        });
    }

    private int getQuestion(int prev, int answer) {
        int next=0;
        switch(prev){

            case 0:
                switch(answer) {
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
                switch(answer){
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
                switch(answer){
                    case 0:
                        next = 3;
                        break;
                    case 1:
                        next = 8;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 3:
                switch(answer){
                    case 0:
                        next = 4;
                        break;
                    case 1:
                        next = 5;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 4:
                switch(answer){
                    case 0:
                        next = 5;
                        break;
                    case 1:
                        next = 8;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 5:
                switch(answer){
                    case 0:
                        next = 6;
                        break;
                    case 1:
                        next = 11;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 6:
                switch(answer){
                    case 0:
                        next = 7;
                        break;
                    case 1:
                        next = 12;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 7:
                switch(answer){
                    case 0:
                        next = 8;
                        break;
                    case 1:
                        next = 13;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 8:
                switch(answer){
                    case 0:
                        next = 9;
                        break;
                    case 1:
                        next = 14;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 9:
                switch(answer){
                    case 0:
                        next = 10;
                        break;
                    case 1:
                        next = 10;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 10:
                switch(answer){
                    case 0:
                        next = 11;
                        break;
                    case 1:
                        next = 17;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 11:
                switch(answer){
                    case 0:
                        next = 12;
                        break;
                    case 1:
                        next = 15;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 12:
                switch(answer){
                    case 0:
                        next = 13;
                        break;
                    case 1:
                        next = 16;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 13:
                switch(answer){
                    case 0:
                        next = 14;
                        break;
                    case 1:
                        next = 17;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 14:
                switch(answer){
                    case 0:
                        next = 16;
                        break;
                    case 1:
                        next = 18;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 15:
                switch(answer){
                    case 0:
                        next = 16;
                        break;
                    case 1:
                        next = 21;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 16:
                switch(answer){
                    case 0:
                        next = 17;
                        break;
                    case 1:
                        next = 23;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 17:
                switch(answer){
                    case 0:
                        next = 18;
                        break;
                    case 1:
                        next = 23;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 18:
                switch(answer){
                    case 0:
                        next = 19;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 19:
                switch(answer){
                    case 0:
                        next = 10;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 20:
                switch(answer){
                    case 0:
                        next = 21;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 21:
                switch(answer){
                    case 0:
                        next = 22;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 22:
                switch(answer){
                    case 0:
                        next = 23;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 23:
                switch(answer){
                    case 0:
                        next = 24;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;

            case 24:
                switch(answer){
                    case 0:
                        next = 17;
                        break;
                    case 1:
                        next = 7;
                        break;
                    case 2:
                        next = 5;
                        break;
                }
                break;


        }

        return next;
    }
}
