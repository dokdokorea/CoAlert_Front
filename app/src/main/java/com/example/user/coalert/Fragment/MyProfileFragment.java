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
import android.widget.TextView;

import com.example.user.coalert.Activity.EditprofileActivity;
import com.example.user.coalert.Activity.ExplanationBadElementActivity;
import com.example.user.coalert.Activity.FollowerListActivity;
import com.example.user.coalert.Activity.FollowingListActivity;
import com.example.user.coalert.Activity.WishListActivity;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileRecyclerViewAdapter;
import com.example.user.coalert.Autehntification.GlobalApplication;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.GetBadIngredientModel;
import com.example.user.coalert.item.OneImgOneStringCardView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MyProfileFragment extends Fragment {
    List<String> list;
    Button Edit;
    FrameLayout wishBtn;
    FrameLayout toxicListBtn;
    FrameLayout goFollowing, goFollower;
    ImageView myImage;
    Intent intent;
    TextView name,email;
    int REQUEST_ALBUM = 100;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myprofile, container, false);
        Edit = (Button) v.findViewById(R.id.edit_personal_info);
        wishBtn = v.findViewById(R.id.wish_button);
        toxicListBtn = v.findViewById(R.id.toxicList);
        goFollowing = v.findViewById(R.id.following);
        goFollower = v.findViewById(R.id.follower);
        myImage = v.findViewById(R.id.profile_pic);
        name=v.findViewById(R.id.Name);
        email=v.findViewById(R.id.email);
        GlobalApplication info=(GlobalApplication) getActivity().getApplication();

        if(info.getProfile()!=null){
            myImage.setImageDrawable(info.getProfile());
        }else
            myImage.setImageResource(R.drawable.user_gray);

        name.setText(info.getId());
        email.setText(info.getEmail());

        RecyclerView cosmeticList = (RecyclerView) v.findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        int ColumNumber = 3;


        cosmeticList.setHasFixedSize(true);
        cosmeticList.setLayoutManager(new GridLayoutManager(getContext(), ColumNumber));
        cosmeticList.setNestedScrollingEnabled(false);


//        List<OneImgOneStringCardView> items = new ArrayList<>();
//        OneImgOneStringCardView[] item = new OneImgOneStringCardView[5];
//        item[0] = new OneImgOneStringCardView(R.drawable.cardview1, "아쿠아 글로우 쿠션 파운데아션 SPF23 PA++");
//        item[1] = new OneImgOneStringCardView(R.drawable.cardview2, "보탈필루이드 블루밍 쿠션 SPF50+ PA+++");
//        item[2] = new OneImgOneStringCardView(R.drawable.cardview3, "슬기3");
//        item[3] = new OneImgOneStringCardView(R.drawable.cardview4, "슬기4");
//        item[4] = new OneImgOneStringCardView(R.drawable.cardview5, "슬기5");

//        for (int i = 0; i < 5; i++) items.add(item[i]);

        List<OneImgOneStringCardView> cositems = new ArrayList<>();
        OneImgOneStringCardView[] cositem = new OneImgOneStringCardView[6];

        cositem[0] = new OneImgOneStringCardView(R.drawable.used_cosmetic1, "나스_아쿠아 글로우 쿠션 파운데이션 SPF23 PA++",3);
        cositem[1] = new OneImgOneStringCardView(R.drawable.used_cosmetic2, "웰라쥬_보탈필루이드 블루밍 쿠션 SPF50+ PA+++", 3);
        cositem[2] = new OneImgOneStringCardView(R.drawable.used_cosmetic3, "삐아_라스트 벨벳 립 틴트 4", 4);
        cositem[3] = new OneImgOneStringCardView(R.drawable.used_cosmetic4, "맥_버시컬러 바니쉬 크림 립 스테인", 4);
        cositem[4] = new OneImgOneStringCardView(R.drawable.used_cosmetic5, "홀리카_피스 매칭 섀도우 쉬머", 2);
        cositem[5] = new OneImgOneStringCardView(R.drawable.used_cosmetic6, "에뛰드하우스_룩 앳 마이 아이즈 카페", 2);




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
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Log.e("hihi", "asd");
                            //TODO 서버에 요청을 보내주세요 리스트 형식으로 받은 후 toString 해서 다음 액티비티로 넘겨주세요
                            final Intent intent = new Intent(getActivity(), ExplanationBadElementActivity.class);
                            Call<List<GetBadIngredientModel>> call = ForRestSingleton.getInstance().getBadIngredient("0", "지성");
                            Thread.sleep(5000);
                            List<GetBadIngredientModel> result = call.execute().body();
                            intent.putExtra("badElement", result.toString());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
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
            String imgPath = _getRealPathFromURI(getContext(), url);
            Bitmap image = BitmapFactory.decodeFile(imgPath);
            try {
                ExifInterface exifInterface = new ExifInterface(imgPath);
                int exifOrientation = exifInterface.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                int exifDegree = exifOrientationToDegrees(exifOrientation);
                image = getRotatedBitmap(image, exifDegree);
                myImage.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)

        {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)

        {
            return 270;
        }
        return 0;
    }

    public Bitmap getRotatedBitmap(Bitmap bitmap, int degrees) throws Exception {
        if(bitmap == null) return null;
        if (degrees == 0) return bitmap;

        Matrix m = new Matrix();
        m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    }

    private String _getRealPathFromURI(Context context, Uri Uri) {
        Cursor cursor = getActivity().getContentResolver().query(Uri, null, null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex("_data"));
        return path;
    }
}
