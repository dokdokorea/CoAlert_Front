package com.example.user.coalert.Adapter.FragmentHomeElementAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class BestReviewAdapter extends RecyclerView.Adapter<BestReviewAdapter.ViewHolder> {
    private ArrayList<OneImgTwoStringCardView> mDataset;
    Context context;
int item_layout;

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView idTextView;
        private TextView contentView;
        CardView cardview;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.prod_image);
            idTextView = (TextView) itemView.findViewById(R.id.user_name);
            contentView = (TextView) itemView.findViewById(R.id.best_review_content);
            cardview = (CardView) itemView.findViewById(R.id.best_review_cardview);
        }
    }

    public BestReviewAdapter(Context context, ArrayList<OneImgTwoStringCardView> myDataset,int item_layout) {
        this.context = context;
        mDataset = myDataset;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public BestReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_reviews, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BestReviewAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImage());
        holder.idTextView.setText(mDataset.get(position).getText1());
        holder.contentView.setText(mDataset.get(position).getText2());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "나는 내 수대로 쓸거야.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
