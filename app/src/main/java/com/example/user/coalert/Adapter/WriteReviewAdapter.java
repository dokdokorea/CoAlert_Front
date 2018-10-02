package com.example.user.coalert.Adapter;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.coalert.Activity.WriteReviewActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImageCardView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WriteReviewAdapter extends RecyclerView.Adapter<WriteReviewAdapter.ViewHolder> {
    private ArrayList<OneImageCardView> arrayList;
    Uri allUri;
    private static final int CAMERA_REQUEST = 100;
    private static final int ALBUM_REQUEST = 1000;
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_personal_prod_pic_imageview);

        }
    }

    public WriteReviewAdapter(ArrayList<OneImageCardView> myDataset) {
        arrayList = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.write_review, null);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

//        OneImageCardView item = arrayList.get(position);
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(v.getContext())
//                        .setTitle("업로드할 이미지 선택")
//                        .setPositiveButton("사진 촬영", cameraListener)
//                        .setNeutralButton("앨범선택", albumListener)
//                        .setNegativeButton("취소", cancelListener)
//                        .show();
//
//            }
//
//            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    cameraView();
//                }
//            };
//
//            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                        startActivityForResult(intent, ALBUM_REQUEST);
//                        Log.e("asdasd", "asdasd");
//                        intent.setType("image/*");
//                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), ALBUM_REQUEST);
//
//                }
//            };
//
//            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            };
//        });
//        holder.imageView.setImageResource(arrayList.get(position).getImg());
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case ALBUM_REQUEST:
//            {
//                Log.e("requestCode", String.valueOf(requestCode));
//                Uri url = data.getData();
//                ClipData clipData = data.getClipData();
//                if (clipData != null) {
//                    for (int i = 0; i < 3; i++) {
//                        Uri urione = clipData.getItemAt(i).getUri();
//                        Log.e("path: ", urione.toString());
//                    }
//                } else {
//                    String path = _getRealPathFromURI(this, url);
//                    Bitmap img = BitmapFactory.decodeFile(path);
//                    Log.e("path: ", path);
//                    imageView.setImageBitmap(img);
//                }
//                break;
//            }
//            case CAMERA_REQUEST:
//            {
//                try {
//                    Bitmap img = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), allUri);
//                    imageView.setImageBitmap(img);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
//    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

//    public void cameraView() {
//        Uri uri = FileProvider.getUriForFile(getBaseContext(), "com.bignerdranch.android.test.fileprovider", new File(Environment.getExternalStorageDirectory(), "tmp_contact_" + System.currentTimeMillis() + ".jpg"));
//        allUri = uri;
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(intent, CAMERA_REQUEST);
//    }
}
