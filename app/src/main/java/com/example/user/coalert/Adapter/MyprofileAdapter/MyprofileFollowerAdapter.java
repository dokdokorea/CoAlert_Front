package com.example.user.coalert.Adapter.MyprofileAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Activity.AnotherprofileActivity;
import com.example.user.coalert.Activity.MainActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.List;

public class MyprofileFollowerAdapter extends RecyclerView.Adapter<MyprofileFollowerAdapter.ViewHolder> {
    Context context;
    List<OneImgOneStringCardView> list;

    int item_layout;

    public MyprofileFollowerAdapter(Context context, List<OneImgOneStringCardView> items, int item_layout) {
        this.context = context;
        list = items;
        this.item_layout = item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OneImgOneStringCardView item = list.get(position);
        Drawable drawable = context.getResources().getDrawable(item.getImage());
        holder.image.setImageDrawable(drawable);
        holder.name.setText(item.getText());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goes to new activity passing the item name

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.cosphoto);
            name = (TextView) itemView.findViewById(R.id.Name);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final OneImgOneStringCardView item = list.get(getAdapterPosition());
            Intent intent = new Intent(context, AnotherprofileActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, item.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}