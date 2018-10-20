package com.example.user.coalert.Adapter.SelectedDetailReviewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class DetaillReviewCommentAdapter extends RecyclerView.Adapter<DetaillReviewCommentAdapter.ViewHolder> {
    private ArrayList<OneImgTwoStringCardView> mDataSet;
    int rate;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id,comment;
        private ImageView profile;


        public ViewHolder(View itemView) {
            super(itemView);
            profile = (ImageView) itemView.findViewById(R.id.comment_profile);
            id = (TextView) itemView.findViewById(R.id.comment_id);
            comment = (TextView) itemView.findViewById(R.id.comment_text);
        }
    }

    public DetaillReviewCommentAdapter(ArrayList<OneImgTwoStringCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public DetaillReviewCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review_comment, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetaillReviewCommentAdapter.ViewHolder holder, int position) {
        holder.profile.setImageResource(mDataSet.get(position).getImage());
        holder.id.setText(mDataSet.get(position).getText1());
        holder.comment.setText(mDataSet.get(position).getText2());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
