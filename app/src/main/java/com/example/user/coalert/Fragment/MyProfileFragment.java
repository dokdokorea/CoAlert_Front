package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.coalert.Activity.EditprofileActivity;
import com.example.user.coalert.Activity.ExplanationBadElementActivity;
import com.example.user.coalert.Activity.FollowerListActivity;
import com.example.user.coalert.Activity.FollowingListActivity;
import com.example.user.coalert.Activity.WishListActivity;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MyProfileFragment extends Fragment {
    List<String> list;
    Button Edit;
    FrameLayout wishBtn;
    FrameLayout toxicListBtn;
    ImageButton goFollowing, goFollower;
    ImageView myImage;
    Intent intent;
    int REQUEST_ALBUM = 100;

    public MyProfileFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myprofile, container, false);
        Edit = (Button) v.findViewById(R.id.edit_personal_info);
        wishBtn = v.findViewById(R.id.wish_button);
        toxicListBtn = v.findViewById(R.id.toxicList);
        goFollowing = v.findViewById(R.id.go_following_list);
        goFollower = v.findViewById(R.id.go_follower_list);
        myImage = v.findViewById(R.id.profile_pic);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        RecyclerView cosmeticList = (RecyclerView) v.findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        int ColumNumber = 3;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        cosmeticList.setHasFixedSize(true);
        cosmeticList.setLayoutManager(new GridLayoutManager(getContext(), ColumNumber));
        cosmeticList.setNestedScrollingEnabled(false);


        List<OneImgOneStringCardView> items = new ArrayList<>();
        OneImgOneStringCardView[] item = new OneImgOneStringCardView[5];
        item[0] = new OneImgOneStringCardView(R.drawable.cardview1, "슬기1");
        item[1] = new OneImgOneStringCardView(R.drawable.cardview2, "슬기2");
        item[2] = new OneImgOneStringCardView(R.drawable.cardview3, "슬기3");
        item[3] = new OneImgOneStringCardView(R.drawable.cardview4, "슬기4");
        item[4] = new OneImgOneStringCardView(R.drawable.cardview5, "슬기5");

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new MyprofileFollowerAdapter(getApplicationContext(), items, R.layout.fragment_myprofile));

        List<OneImgOneStringCardView> cositems = new ArrayList<>();
        OneImgOneStringCardView[] cositem = new OneImgOneStringCardView[6];
        cositem[0] = new OneImgOneStringCardView(R.drawable.cardview1, "슬기1");
        cositem[1] = new OneImgOneStringCardView(R.drawable.cardview2, "슬기2");
        cositem[2] = new OneImgOneStringCardView(R.drawable.cardview3, "슬기3");
        cositem[3] = new OneImgOneStringCardView(R.drawable.cardview4, "슬기4");
        cositem[4] = new OneImgOneStringCardView(R.drawable.cardview5, "슬기5");
        cositem[5] = new OneImgOneStringCardView(R.drawable.irin, "irin");


        for (int i = 0; i < 6; i++) cositems.add(cositem[i]);
        cosmeticList.setAdapter(new MyprofileRecyclerViewAdapter(getApplicationContext(), cositems, R.layout.fragment_myprofile));

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), EditprofileActivity.class);
                startActivity(intent);
            }
        });

        wishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), WishListActivity.class);
                startActivity(intent);
            }
        });

        toxicListBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ExplanationBadElementActivity.class);
                startActivity(intent);
            }
        });

        goFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), FollowerListActivity.class);
                startActivity(intent);
            }
        });

        goFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), FollowingListActivity.class);
                startActivity(intent);
            }
        });

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_ALBUM);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ALBUM && data != null) {
            Uri url = data.getData();
            String path =_getRealPathFromURI(getApplicationContext(), url);
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(path);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);
                bitmap = rotateBitmap(bitmap, orientation);
                Log.e("asdasd", String.valueOf(bitmap));
                myImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String _getRealPathFromURI(Context context, Uri Uri) {
        Cursor cursor = getActivity().getContentResolver().query(Uri, null, null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex("_data"));
        return path;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

}


