package com.example.user.coalert.Adapter.MyReviewListAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Activity.CosmeticInformationActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.TwoImgFourStringCardView;

import java.util.ArrayList;

public class MyDetailReviewAdapter extends RecyclerView.Adapter<MyDetailReviewAdapter.ViewHolder> {
    private ArrayList<TwoImgFourStringCardView> mDataSet;
    int rate;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView CosmeticPicture,PhotoPreview;
        private TextView CosmeticName,LikeCount,Title,Context;


        public ViewHolder(View itemView) {
            super(itemView);
            CosmeticPicture = (ImageView) itemView.findViewById(R.id.cosmetic_pic);
            CosmeticName=(TextView)itemView.findViewById(R.id.cosmetic_name);
            Title = (TextView) itemView.findViewById(R.id.detail_title);
            Context=(TextView)itemView.findViewById(R.id.detail_context);
            LikeCount=(TextView)itemView.findViewById(R.id.like_count);
            PhotoPreview=(ImageView)itemView.findViewById(R.id.preview_img);
        }
    }

    public MyDetailReviewAdapter(ArrayList<TwoImgFourStringCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public MyDetailReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_detail_review, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyDetailReviewAdapter.ViewHolder holder, int position) {
        holder.CosmeticPicture.setImageResource(mDataSet.get(position).getImg1());
        holder.CosmeticName.setText(mDataSet.get(position).getT1());
        holder.LikeCount.setText(mDataSet.get(position).getT2());
        holder.Title.setText(mDataSet.get(position).getT3());
        holder.Context.setText(mDataSet.get(position).getT4());
        holder.PhotoPreview.setBackgroundResource(mDataSet.get(position).getImg2());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
