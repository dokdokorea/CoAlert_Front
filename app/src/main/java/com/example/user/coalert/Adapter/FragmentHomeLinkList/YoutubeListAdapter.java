package com.example.user.coalert.Adapter.FragmentHomeLinkList;

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
import android.widget.Toast;

import com.example.user.coalert.Activity.ViewDetailActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;

import java.util.ArrayList;
import java.util.List;

public class YoutubeListAdapter extends RecyclerView.Adapter<YoutubeListAdapter.ViewHolder>{

    Context context;
    List <TwoImgFourStringCardView> list;
    int item_layout;

    public YoutubeListAdapter(Context context, List<TwoImgFourStringCardView> items, int item_layout){
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
        final TwoImgFourStringCardView item = list.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImg1());
        holder.TitleImage.setBackground(drawable);
        holder.TitleText.setText(item.getT1());
        holder.username.setText(item.getT2());
        holder.UserProfile.setImageResource(item.getImg2());
        holder.SeeNum.setText(item.getT3());
        holder.url=item.getT4();
        holder.cardview.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                //Toast.makeText(context,mDataSet.get(position).getText1(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView TitleImage;
        TextView TitleText;
        ImageView UserProfile;
        CardView cardview;
        TextView username;
        TextView SeeNum;
        private String url;
        public ViewHolder(View itemView) {
            super(itemView);
            TitleImage=(ImageView)itemView.findViewById(R.id.youtube);
            UserProfile=(ImageView)itemView.findViewById(R.id.user_profile) ;
            TitleText=(TextView)itemView.findViewById(R.id.title);
            username=(TextView)itemView.findViewById(R.id.creator);
            SeeNum=(TextView) itemView.findViewById(R.id.see_num);
            cardview=(CardView)itemView.findViewById(R.id.hot_youtuber_cardview);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final TwoImgFourStringCardView item = list.get(getAdapterPosition());
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse( "http://youtu.be/" + item.getT4() ));
            context.startActivity( intent );
        }
    }
}