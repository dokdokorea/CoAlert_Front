package com.example.user.coalert.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
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
import com.example.user.coalert.item.adCardViewItem;

import java.util.ArrayList;

public class AdImgAdapter extends RecyclerView.Adapter<AdImgAdapter.ViewHolder> {
    ArrayList<adCardViewItem> itemList ;
    Context context;
    public AdImgAdapter(Context context, ArrayList itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad_cosmetic, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        adCardViewItem adCardViewItem = itemList.get(position);
        Bitmap bitmap = adCardViewItem.getBitmap();
        holder.productImage.setImageBitmap(bitmap);
        holder.productName.setText("코알라 화장품");
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView productImage;
        TextView productName;
        CardView oneCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.inisfreeText);
            productImage = itemView.findViewById(R.id.inisfreeImg);
            oneCardView = itemView.findViewById(R.id.adCardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Clicked on position: " + getAdapterPosition(), Toast.LENGTH_LONG);
        }
    }
}
