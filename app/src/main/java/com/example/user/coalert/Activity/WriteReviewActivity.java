package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;



public class WriteReviewActivity extends AppCompatActivity {
    private final int MaxLengthOfOneLineContent=100;
    ImageView imageView;
    TextView wordsNum;
    EditText editText;
    int previousLength = 0;
    Button letsDetailReview;
    SmileRating smileRating ;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
        editText = (EditText)findViewById(R.id.one_line);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MaxLengthOfOneLineContent)});
        wordsNum = findViewById(R.id.wordsNumber);
        smileRating = findViewById(R.id.smile_rating);
        imageView = findViewById(R.id.prod_image);
        letsDetailReview = findViewById(R.id.write_review_lets_detail_write_btn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1000);
            }
        });

        letsDetailReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(letsDetailReview.getText() == "자세히 작성") {
                    letsDetailReview.setText("되돌리기");
                }
                else {
                    letsDetailReview.setText("자세히 작성");
                }
            }
        });


        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                int level = smileRating.getRating();
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i("status: ", "Bad and level"+level);
                        break;
                    case SmileRating.GOOD:
                        Log.i("status: ", "Good and level"+level);
                        break;
                    case SmileRating.GREAT:
                        Log.i("status: ", "Great and level" + level);
                        break;
                    case SmileRating.OKAY:
                        Log.i("status: ", "Okay and level" + level);
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i("status: ", "Terrible and level"+ level);
                        break;
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                wordsNum.setText(editText.getText().length()+"/100");
                if (previousLength != editText.getText().length()){
                    Log.e("words Change Number", String.valueOf(editText.getText()));
                    previousLength = editText.getText().length();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data != null) {
            Uri url = data.getData();
            String path = _getRealPathFromURI(this, url);
            Bitmap img = BitmapFactory.decodeFile(path);
            imageView.setImageBitmap(img);
        }
    }
    private String _getRealPathFromURI(Context context, Uri Uri){
        Cursor cursor = getContentResolver().query(Uri, null, null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex("_data"));
        return path;
    }
}
