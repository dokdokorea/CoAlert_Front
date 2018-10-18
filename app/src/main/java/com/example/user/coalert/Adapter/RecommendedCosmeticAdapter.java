package com.example.user.coalert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgThreeStringCardView;

import java.util.List;

public class RecommendedCosmeticAdapter extends RecyclerView.Adapter<RecommendedCosmeticAdapter.ViewHolder>{


    Context context;
    List <OneImgThreeStringCardView> list;
    int item_layout;

    public RecommendedCosmeticAdapter(Context context, List<OneImgThreeStringCardView> items, int item_layout){
        this.context = context;
        list = items;
        this.item_layout =item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_youtube_list, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OneImgThreeStringCardView item = list.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImage());
        holder.CosmeticImage.setImageResource(item.getImage());
        holder.Company.setText(item.getText1());
        holder.Name.setText(item.getText2());
//        holder.Rating.setText(Float.toString(item.getNumber()));
        holder.Rating.setText(String.format("%.1f",item.getNumber()));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView CosmeticImage;
        TextView Company;
        TextView Name;
        TextView Rating;

        public ViewHolder(View itemView) {
            super(itemView);
            CosmeticImage=(ImageView)itemView.findViewById(R.id.cosmetic_photo);
            Company=(TextView)itemView.findViewById(R.id.company_name);
            Name=(TextView)itemView.findViewById(R.id.cosmetic_name);
            Rating=(TextView)itemView.findViewById(R.id.rating);

        }

    }
}