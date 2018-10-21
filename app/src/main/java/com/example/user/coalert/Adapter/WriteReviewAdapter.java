package com.example.user.coalert.Adapter;

import android.app.Activity;
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
    Context mcontext;
    Intent intent;
    private static final int CAMERA_REQUEST = 100;
    private static final int ALBUM_REQUEST = 1000;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_personal_prod_pic_imageview);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    cameraView(getAdapterPosition());
                }
            };

            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.e("asdasd", String.valueOf(getAdapterPosition()));
                    ((Activity) mcontext).startActivityForResult(Intent.createChooser(intent, "Select Picture"), ALBUM_REQUEST + getAdapterPosition());
                }
            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            };
            new AlertDialog.Builder(mcontext)
                    .setTitle("업로드할 이미지 선택")
                    .setPositiveButton("사진 촬영", cameraListener)
                    .setNeutralButton("앨범선택", albumListener)
                    .setNegativeButton("취소", cancelListener)
                    .show();
        }
    }

    public WriteReviewAdapter(ArrayList<OneImageCardView> myDataset) {
        arrayList = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_prod_pic, null);

        mcontext = parent.getContext();
        intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.e("어댑터안에서 바꾼 후 ", String.valueOf(arrayList.get(position)));
        holder.imageView.setImageResource(R.drawable.clickedbluecamera);
    }

    public void cameraView(int position) {
        Uri uri = FileProvider.getUriForFile(mcontext, "com.bignerdranch.android.test.fileprovider", new File(Environment.getExternalStorageDirectory(), "tmp_contact_" + System.currentTimeMillis() + ".jpg"));
        allUri = uri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
