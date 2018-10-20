package com.example.user.coalert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.coalert.Activity.CosmeticInformationActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgThreeStringCardView;

import java.text.DecimalFormat;
import java.util.List;

public class RecommendedCosmeticAdapter extends RecyclerView.Adapter<RecommendedCosmeticAdapter.ViewHolder>{

    Context context;
    List <OneImgThreeStringCardView> list;
    int item_layout;

    public RecommendedCosmeticAdapter(Context context, List<OneImgThreeStringCardView> items, int item_layout){
        this.context = context;
        this.list = items;
        this.item_layout =item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommended_cosmetic_list, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new RecommendedCosmeticAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OneImgThreeStringCardView item = list.get(position);
        DecimalFormat format = new DecimalFormat(".##");
        holder.CosmeticImage.setImageBitmap(item.getImage());
        holder.Company.setText(item.getText1());
        holder.Name.setText(item.getText2().replaceAll("\"", ""));
        String str = format.format(item.getNumber()*20);
//        holder.Rating.setText(Float.toString(item.getNumber()));
        holder.Rating.setText(str);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView CosmeticImage;
        TextView Company;
        TextView Name;
        TextView Rating;
        CardView recommendItem;
        public ViewHolder(View itemView) {
            super(itemView);
            recommendItem = itemView.findViewById(R.id.item_ingredient_list_card);
            CosmeticImage=itemView.findViewById(R.id.recommend_cosmetic_photo);
            Company=itemView.findViewById(R.id.recommend_company_name);
            Name=itemView.findViewById(R.id.recommend_cosmetic_name);
            Rating=itemView.findViewById(R.id.recommend_rating);
            recommendItem.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CosmeticInformationActivity.class);
            Log.e("클릭", String.valueOf(getAdapterPosition()));
            intent.putExtra("cname", list.get(getAdapterPosition()).getText2().replaceAll("\"",""));
            intent.putExtra("company", list.get(getAdapterPosition()).getText1());
            intent.putExtra("rating", list.get(getAdapterPosition()).getNumber());
            intent.putExtra("image", list.get(getAdapterPosition()).getImage());
            context.startActivity(intent);
        }
    }
}