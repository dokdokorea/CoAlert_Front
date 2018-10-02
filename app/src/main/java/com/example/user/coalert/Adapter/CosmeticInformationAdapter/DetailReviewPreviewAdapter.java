package com.example.user.coalert.Adapter.CosmeticInformationAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;
import com.kakao.usermgmt.response.model.User;

import java.util.ArrayList;

public class DetailReviewPreviewAdapter extends RecyclerView.Adapter<DetailReviewPreviewAdapter.ViewHolder> {
    private ArrayList<TwoImgFourStringCardView> mDataSet;
    int rate;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ProfilePicture,PhotoPreview;
        private TextView UserId,LikeCount,Title,Context;


        public ViewHolder(View itemView) {
            super(itemView);
            ProfilePicture = (ImageView) itemView.findViewById(R.id.profile_pic);
            UserId=(TextView)itemView.findViewById(R.id.profile_id);
            Title = (TextView) itemView.findViewById(R.id.detail_title);
            Context=(TextView)itemView.findViewById(R.id.detail_context);
            LikeCount=(TextView)itemView.findViewById(R.id.like_count);
            PhotoPreview=(ImageView)itemView.findViewById(R.id.preview_img);
        }
    }

    public DetailReviewPreviewAdapter(ArrayList<TwoImgFourStringCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public DetailReviewPreviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review_in_cosmeticinfo, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailReviewPreviewAdapter.ViewHolder holder, int position) {
        holder.ProfilePicture.setImageResource(mDataSet.get(position).getImg1());
        holder.UserId.setText(mDataSet.get(position).getT1());
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
