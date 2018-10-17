package com.example.user.coalert.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.ViewHolder> {
    private ArrayList<TwoImgTwoStringCardView> mDataSet;
    private int j = 0;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView name;
        private TextView id;
        private ImageView follwerBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_follower_list_circleimageview);
            name = (TextView) itemView.findViewById(R.id.user_name);
            id = (TextView) itemView.findViewById(R.id.item_follower_email);
            follwerBtn = (ImageView) itemView.findViewById(R.id.item_follower_list_follower_btn);
            follwerBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            j = 1 - j;

            if (j == 0) {
                follwerBtn.setImageResource(R.drawable.follow_btn);
            } else {
                follwerBtn.setImageResource(R.drawable.following_btn);
            }

        }
    }

    public FollowerListAdapter(ArrayList<TwoImgTwoStringCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public FollowerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower_list, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FollowerListAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataSet.get(position).getImg1());
        holder.name.setText(mDataSet.get(position).getText1());
        holder.id.setText(mDataSet.get(position).getText2());
        holder.follwerBtn.setImageResource(mDataSet.get(position).getImg2());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
