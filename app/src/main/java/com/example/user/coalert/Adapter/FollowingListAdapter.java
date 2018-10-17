package com.example.user.coalert.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Activity.AnotherprofileActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class FollowingListAdapter extends RecyclerView.Adapter<FollowingListAdapter.ViewHolder> {
    private ArrayList<TwoImgTwoStringCardView> mDataset;
    private int j = 0;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            if (j == 0)
                follwerBtn.setImageResource(R.drawable.follow_btn);
            else
                /*DB 목록 갱신*/
                follwerBtn.setImageResource(R.drawable.following_btn);
            follwerBtn.setOnClickListener(this);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(v.getContext(), "inside viewholder position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(v.getContext(),AnotherprofileActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
            j = 1 - j;
            if (j == 0)
                follwerBtn.setImageResource(R.drawable.follow_btn);
            else
                /*DB 목록 갱신*/
               follwerBtn.setImageResource(R.drawable.following_btn);
        }
    }

    public FollowingListAdapter(ArrayList<TwoImgTwoStringCardView> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower_list, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImg1());
        holder.name.setText(mDataset.get(position).getText1());
        holder.id.setText(mDataset.get(position).getText2());
        holder.follwerBtn.setImageResource(mDataset.get(position).getImg2());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
