package com.example.user.coalert.Adapter;

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
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<OneImgTwoStringCardView> mDataSet;
    Context context;
int item_layout;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTextView;
        private TextView textView2;
        private CardView cardView;


        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.notification_card_image);
            mTextView = (TextView) itemView.findViewById(R.id.notification_card_text);
            textView2=itemView.findViewById(R.id.user_name);
            cardView=itemView.findViewById(R.id.notification_cardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context,mDataSet.get(getAdapterPosition()).getText1(),Toast.LENGTH_SHORT).show();
        }
    }

    public NotificationAdapter(Context context,ArrayList<OneImgTwoStringCardView> myDataset,int item_layout) {
        mDataSet = myDataset;
        this.context=context;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataSet.get(position).getText2());
        holder.textView2.setText(mDataSet.get(position).getText1());
        holder.mImageView.setImageResource(mDataSet.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}