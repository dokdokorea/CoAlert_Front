package com.example.user.coalert.Activity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.coalert.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WriteReviewActivity extends AppCompatActivity {
    private final int MaxLengthOfOneLineContent=100;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
        EditText editText=(EditText)findViewById(R.id.one_line);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MaxLengthOfOneLineContent)});
        imageView = findViewById(R.id.prod_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1000);
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
