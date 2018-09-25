package com.example.user.coalert.Adapter.MyprofileAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImageCardView;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {
private ArrayList<OneImageCardView> mDataset;
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wish_card_photo);
        }
    }
public WishListAdapter(ArrayList<OneImageCardView> myDataset){mDataset=myDataset;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wish_card,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
