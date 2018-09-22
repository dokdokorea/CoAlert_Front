package com.example.user.coalert.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
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
import com.example.user.coalert.item.CosmeticList_mypage;

import java.util.List;

public class MypageFollowerAdapter extends RecyclerView.Adapter<MypageFollowerAdapter.ViewHolder>{
    Context context;
    List <CosmeticList_mypage> list;
    int item_layout;
    public MypageFollowerAdapter(Context context, List<CosmeticList_mypage> items, int item_layout){
        this.context = context;
        list = items;
        this.item_layout =item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, null);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CosmeticList_mypage item = list.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImage());
        holder.image.setImageDrawable(drawable);
        holder.name.setText(item.getTitle());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        CardView cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.cosphoto);
            name=(TextView)itemView.findViewById(R.id.Name);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}