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
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class DetailReviewAdapter extends RecyclerView.Adapter<DetailReviewAdapter.ViewHolder> {
    private ArrayList<TwoImgTwoStringCardView> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Title,CreatorId;
        private ImageView TitleImg,CreatorImg;

        public ViewHolder(View itemView) {
            super(itemView);
            TitleImg = (ImageView) itemView.findViewById(R.id.title_img);
            CreatorImg=(ImageView)itemView.findViewById(R.id.profile_pic);
            Title=(TextView)itemView.findViewById(R.id.title_text);
            CreatorId=(TextView)itemView.findViewById(R.id.profile_id);
        }
    }

    public DetailReviewAdapter(ArrayList<TwoImgTwoStringCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public DetailReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailReviewAdapter.ViewHolder holder, int position) {
        holder.TitleImg.setBackgroundResource(mDataSet.get(position).getImg2());
        holder.Title.setText(mDataSet.get(position).getText2());
        holder.CreatorId.setText(mDataSet.get(position).getText1());
        holder.CreatorImg.setImageResource(mDataSet.get(position).getImg1());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
