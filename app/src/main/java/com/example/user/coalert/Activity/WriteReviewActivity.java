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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.coalert.Adapter.WriteReviewAdapter;
import com.example.user.coalert.Fragment.HomeFragment;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.GetBadIngredientModel;
import com.example.user.coalert.forRestServer.oneCosmeticRecommend;
import com.example.user.coalert.forRestServer.setReviewModel;
import com.example.user.coalert.item.OneImageCardView;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class WriteReviewActivity extends AppCompatActivity {
    private final int MaxLengthOfOneLineContent = 100;
    private RecyclerView personalPicRecyclerview;
    private ArrayList<OneImageCardView> recyclerArr;
    ImageView imageView;
    TextView wordsNum;
    EditText editText;
    TextView cname, companyName;
    RecyclerView userImageRecyclerView;
    int previousLength = 0;
    Button letsDetailReview, saveReview;
    SmileRating smileRating;
    Uri allUri;
    ImageButton backBtn;
    private static final int CAMERA_REQUEST = 100;
    private static final int ALBUM_REQUEST = 1000;

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
        cname = findViewById(R.id.cosmetic_prod_name);
        backBtn = findViewById(R.id.selected_detail_review_back_btn);
        editText = findViewById(R.id.one_line);
        wordsNum = findViewById(R.id.wordsNumber);
        companyName = findViewById(R.id.write_review_company);
        smileRating = findViewById(R.id.smile_rating);
        imageView = findViewById(R.id.prod_image);
        letsDetailReview = findViewById(R.id.write_review_lets_detail_write_btn);
        saveReview = findViewById(R.id.write_review_save_btn);
        userImageRecyclerView = findViewById(R.id.personal_prod_pic);
        userImageRecyclerView.setVisibility(View.GONE);
        final Intent getIntent = getIntent();
        //처음에는 한줄작성
        letsDetailReview.setText("자세히 작성");
        editText.setLayoutParams(new LinearLayout.LayoutParams(1100, 150));
        wordsNum.setText(editText.getText().length() + "/" + MaxLengthOfOneLineContent);
        final int number = getIntent.getExtras().getInt("check");
        if (number == 0) {
            Drawable drawable = getResources().getDrawable((Integer) getIntent.getExtras().get("image"));
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageBitmap((Bitmap) getIntent.getExtras().get("image"));
        }
        companyName.setText(getIntent.getStringExtra("company"));
        cname.setText(getIntent.getStringExtra("cname"));
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        letsDetailReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (letsDetailReview.getText() == "자세히 작성") {
                    letsDetailReview.setText("되돌리기");
                    //자세히 작성
                    userImageRecyclerView.setVisibility(View.VISIBLE);
                    wordsNum.setText("제한없음");
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1000000000)});
                    editText.setLayoutParams(new LinearLayout.LayoutParams(1100, 500));
                } else {
                    letsDetailReview.setText("자세히 작성");
                    userImageRecyclerView.setVisibility(View.GONE);
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MaxLengthOfOneLineContent)});
                    //한줄평 작성 되면서 텍스트변화
                    wordsNum.setText(editText.getText().length() + "/" + MaxLengthOfOneLineContent);
                    editText.setLayoutParams(new LinearLayout.LayoutParams(1100, 150));
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
        personalPicRecyclerview = (RecyclerView) findViewById(R.id.personal_prod_pic);
        personalPicRecyclerview.setHasFixedSize(true);
        personalPicRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerArr = new ArrayList<>();
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.camera);
        recyclerArr.add(new OneImageCardView(icon));
        recyclerArr.add(new OneImageCardView(icon));
        recyclerArr.add(new OneImageCardView(icon));
        recyclerArr.add(new OneImageCardView(icon));
        personalPicRecyclerview.setAdapter(new WriteReviewAdapter(recyclerArr));

        saveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            int level = smileRating.getRating();
                            Call call2 = ForRestSingleton.getInstance().setReview(0, getIntent.getExtras().getInt("kind"), cname.getText().toString(), level, 0, editText.getText().toString());
                            call2.execute().body();
                            Intent intent = new Intent(getApplicationContext(), CosmeticInformationActivity.class);
                            Call<List<GetBadIngredientModel>> call = ForRestSingleton.getInstance().ingredientPerCosmetic(cname.getText().toString(), String.valueOf(getIntent.getExtras().getInt("kind")));
                            List<GetBadIngredientModel> result = call.execute().body();
                            Call<oneCosmeticRecommend> call3 = ForRestSingleton.getInstance().oneRecommendCosmetic(getIntent.getExtras().getInt("kind"), cname.getText().toString(), 0);
                            oneCosmeticRecommend result3 = call3.execute().body();
                            intent.putExtra("cname", cname.getText().toString());
                            intent.putExtra("company", companyName.getText());
                            intent.putExtra("rating", (result3.getRating()*20));
                            intent.putExtra("image", (Bitmap)getIntent.getExtras().get("image"));
                            intent.putExtra("ingredient",result.toString());
                            intent.putExtra("kind", getIntent.getExtras().getInt("kind"));
                            intent.putExtra("check",1);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int position;
        Log.e("asdasd", String.valueOf(requestCode));
        if (data != null) {
            if (requestCode == 100) {
                position = requestCode - 100;
            } else {
                position = requestCode - 1000;
            }
            switch (requestCode - position) {
                case ALBUM_REQUEST: {
                    //TODO 앨범으로 요청받았을 때
                    Uri url = data.getData();
                    String path = _getRealPathFromURI(this, url);
                    Bitmap img = BitmapFactory.decodeFile(path);
                    recyclerArr.set(position, new OneImageCardView(img));
                    personalPicRecyclerview.setAdapter(new WriteReviewAdapter(recyclerArr));
                    break;
                }
                case CAMERA_REQUEST: {
                    //TODO 카메라로 요청받았을 때
                    try {
                        Log.e("img", "asdasd");
                        Bitmap img = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), allUri);
                        recyclerArr.set(position, new OneImageCardView(img));
                        personalPicRecyclerview.setAdapter(new WriteReviewAdapter(recyclerArr));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
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
