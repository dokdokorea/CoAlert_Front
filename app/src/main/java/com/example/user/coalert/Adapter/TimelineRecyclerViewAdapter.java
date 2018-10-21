package com.example.user.coalert.Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.user.coalert.Activity.ViewDetailActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;

import java.util.List;

public class TimelineRecyclerViewAdapter extends RecyclerView.Adapter<TimelineRecyclerViewAdapter.ViewHolder>{
    Context context;
    List <TwoImgFourStringCardView> list;
    int item_layout;
    public TimelineRecyclerViewAdapter(Context context, List<TwoImgFourStringCardView> items, int item_layout){
        this.context = context;
        list = items;
        this.item_layout =item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TwoImgFourStringCardView item = list.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImg2());
        holder.titleImg.setBackground(drawable);
        holder.ProfilePicture.setImageResource(item.getImg1());
        holder.likecount.setText(item.getT3());
        holder.comment.setText(item.getT4());
        holder.title.setText(item.getT2());
        holder.username.setText(item.getT1());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView titleImg,ProfilePicture;
        TextView title,comment,likecount;
        CardView cardview;
        TextView username;
        public ViewHolder(View itemView) {
            super(itemView);
            ProfilePicture=(ImageView)itemView.findViewById(R.id.profile_pic);
            titleImg=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            comment=(TextView)itemView.findViewById(R.id.comment);
            likecount=(TextView)itemView.findViewById(R.id.like_count);
            username=(TextView)itemView.findViewById(R.id.username);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final TwoImgFourStringCardView item = list.get(getAdapterPosition());
            Intent intent = new Intent(context, ViewDetailActivity.class);
            intent.putExtra("id",item.getT1());
            intent.putExtra("title",item.getT2());
            intent.putExtra("like",item.getT3());
            intent.putExtra("profile",item.getImg1());
            context.startActivity(intent);
            Toast.makeText(context,item.getT2(),Toast.LENGTH_SHORT).show();
        }
    }
}