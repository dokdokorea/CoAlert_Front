package com.example.user.coalert.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.BestReviewCardView;

import java.util.ArrayList;

public class BestReviewAdapter extends RecyclerView.Adapter<BestReviewAdapter.ViewHolder> {
private ArrayList<BestReviewCardView> mDataset;

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView idTextView;
        private TextView contentView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.prod_image);
            idTextView=(TextView)itemView.findViewById(R.id.user_name);
            contentView=(TextView)itemView.findViewById(R.id.best_review_content);
        }
    }

    public BestReviewAdapter(ArrayList<BestReviewCardView> myDataset){mDataset=myDataset;}

    @NonNull
    @Override
    public BestReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_reviews,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BestReviewAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImage());
        holder.idTextView.setText(mDataset.get(position).getId());
        holder.contentView.setText(mDataset.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
