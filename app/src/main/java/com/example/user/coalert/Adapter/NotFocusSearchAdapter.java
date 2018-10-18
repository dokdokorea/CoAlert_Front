package com.example.user.coalert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Activity.AdActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import java.util.ArrayList;

public class NotFocusSearchAdapter extends Adapter<NotFocusSearchAdapter.ViewHolder> {
    ArrayList<OneImgOneStringCardView> data;
    Context context;
    public NotFocusSearchAdapter(ArrayList<OneImgOneStringCardView> inputData, Context context) {
        data = inputData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_focus_search, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cosmeticCompanyImage.setImageResource(data.get(position).getImage());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView cosmeticCompanyImage;
        FrameLayout oneCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cosmeticCompanyImage = itemView.findViewById(R.id.cosmetic_company_image);
            oneCardView = itemView.findViewById(R.id.notFocusItem);
            oneCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, AdActivity.class);
            intent.putExtra("whatCompany", data.get(getAdapterPosition()).getText());
            context.startActivity(intent);
        }
    }
}
