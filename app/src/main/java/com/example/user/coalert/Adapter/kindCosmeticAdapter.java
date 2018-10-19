package com.example.user.coalert.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class kindCosmeticAdapter extends RecyclerView.Adapter<kindCosmeticAdapter.ViewHolder> {
    ArrayList<OneImgOneStringCardView> realData;
    Context context;
    public kindCosmeticAdapter(ArrayList<OneImgOneStringCardView> data, Context context) {
        this.realData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_what_select_kind_cosmetic_item, null);
        return new kindCosmeticAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(realData.get(position).getText());
        holder.textView.setBackgroundResource(realData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return realData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.selectKindCosmeticItem);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
