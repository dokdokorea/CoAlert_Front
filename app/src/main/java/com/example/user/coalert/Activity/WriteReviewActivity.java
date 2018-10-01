package com.example.user.coalert.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.IOException;


public class WriteReviewActivity extends AppCompatActivity {
    private final int MaxLengthOfOneLineContent = 100;
    ImageView imageView;
    TextView wordsNum;
    EditText editText;
    int previousLength = 0;
    Button letsDetailReview;
    SmileRating smileRating;
    Uri allUri;
    private static final int CAMERA_REQUEST = 100;
    private static final int ALBUM_REQUEST = 1000;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
        editText = (EditText) findViewById(R.id.one_line);
        wordsNum = findViewById(R.id.wordsNumber);
        smileRating = findViewById(R.id.smile_rating);
        imageView = findViewById(R.id.prod_image);
        letsDetailReview = findViewById(R.id.write_review_lets_detail_write_btn);
        //처음에는 한줄작성
        letsDetailReview.setText("자세히 작성");
        wordsNum.setText(editText.getText().length() + "/" + MaxLengthOfOneLineContent);
        final Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(WriteReviewActivity.this)
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("사진 촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
            }

            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cameraView();
                }
            };

            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (letsDetailReview.getText() == "자세히 작성") {
                        startActivityForResult(intent, ALBUM_REQUEST);
                    } else {
                        Log.e("asdasd", "asdasd");
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), ALBUM_REQUEST);

                    }
                }
            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            };
        });


        letsDetailReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (letsDetailReview.getText() == "자세히 작성") {
                    letsDetailReview.setText("되돌리기");
                    //자세히 작성
                    wordsNum.setText("제한없음");
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1000000000)});
                } else {
                    letsDetailReview.setText("자세히 작성");
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MaxLengthOfOneLineContent)});
                    //한줄평 작성 되면서 텍스트변화
                    wordsNum.setText(editText.getText().length() + "/" + MaxLengthOfOneLineContent);
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
                        Log.i("status: ", "Bad and level" + level);
                        break;
                    case SmileRating.GOOD:
                        Log.i("status: ", "Good and level" + level);
                        break;
                    case SmileRating.GREAT:
                        Log.i("status: ", "Great and level" + level);
                        break;
                    case SmileRating.OKAY:
                        Log.i("status: ", "Okay and level" + level);
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i("status: ", "Terrible and level" + level);
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
                if (letsDetailReview.getText() == "자세히 작성") { //한줄 작성에서 글자 수 늘어나면 텍스트 조절
                    wordsNum.setText(editText.getText().length() + "/" + MaxLengthOfOneLineContent);
                }
                if (previousLength != editText.getText().length()) {
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

        switch (requestCode) {
            case ALBUM_REQUEST:
                {
                    Log.e("requestCode", String.valueOf(requestCode));
                Uri url = data.getData();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    for (int i = 0; i < 3; i++) {
                        Uri urione = clipData.getItemAt(i).getUri();
                        Log.e("path: ", urione.toString());
                    }
                } else {
                    String path = _getRealPathFromURI(this, url);
                    Bitmap img = BitmapFactory.decodeFile(path);
                    Log.e("path: ", path);
                    imageView.setImageBitmap(img);
                }
                break;
            }
            case CAMERA_REQUEST:
                {
                try {
                    Bitmap img = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), allUri);
                    imageView.setImageBitmap(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private String _getRealPathFromURI(Context context, Uri Uri) {
        Cursor cursor = getContentResolver().query(Uri, null, null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex("_data"));
        return path;
    }

    public void cameraView() {
        Uri uri = FileProvider.getUriForFile(getBaseContext(), "com.bignerdranch.android.test.fileprovider", new File(Environment.getExternalStorageDirectory(), "tmp_contact_" + System.currentTimeMillis() + ".jpg"));
        allUri = uri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAMERA_REQUEST);
    }
}
