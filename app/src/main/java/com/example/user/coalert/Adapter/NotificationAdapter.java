package com.example.user.coalert.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<MyData> mDataSet;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView) mImageView.findViewById(R.id.notification_card_image);
            mTextView=(TextView) mTextView.findViewById(R.id.notification_card_text);
        }
    }

    public NotificationAdapter(ArrayList<MyData> myDataset){
        mDataSet=myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position).text);
        holder.mImageView.setImageResource(mDataSet.get(position).img);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

class MyData{
    public String text;
    public int img;
}