package com.example.user.coalert.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.HotYoutuberCardView;

import java.util.ArrayList;

public class HotYoutuberAdapter extends RecyclerView.Adapter<HotYoutuberAdapter.ViewHolder> {
    private ArrayList<HotYoutuberCardView> mDataSet;

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.youtube);
            textView = (TextView) itemView.findViewById(R.id.youtube_title);
        }
    }

    public HotYoutuberAdapter(ArrayList<HotYoutuberCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public HotYoutuberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_view, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotYoutuberAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataSet.get(position).getImage());
        holder.textView.setText(mDataSet.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
