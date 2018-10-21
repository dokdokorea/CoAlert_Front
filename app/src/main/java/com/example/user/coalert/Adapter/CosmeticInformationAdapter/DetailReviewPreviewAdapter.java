package com.example.user.coalert.Adapter.CosmeticInformationAdapter;

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

import com.example.user.coalert.Activity.AnotherprofileActivity;
import com.example.user.coalert.Activity.ViewDetailActivity;
import com.example.user.coalert.Adapter.MyprofileAdapter.MyprofileFollowerAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgFourStringCardView;
import com.kakao.usermgmt.response.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailReviewPreviewAdapter extends RecyclerView.Adapter<DetailReviewPreviewAdapter.ViewHolder> {
    private ArrayList<TwoImgFourStringCardView> mDataSet;
    Context context;
    int item_layout;

    public DetailReviewPreviewAdapter(Context context, ArrayList<TwoImgFourStringCardView> items, int item_layout){
        this.context = context;
        mDataSet = items;
        this.item_layout =item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review_in_cosmeticinfo, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ProfilePicture,PhotoPreview;
        private TextView UserId,LikeCount,Title,Context;
        private CardView DetailCard;


        public ViewHolder(View itemView) {
            super(itemView);
            ProfilePicture = (ImageView) itemView.findViewById(R.id.profile_pic);
            UserId=(TextView)itemView.findViewById(R.id.profile_id);
            Title = (TextView) itemView.findViewById(R.id.detail_title);
            Context=(TextView)itemView.findViewById(R.id.detail_context);
            LikeCount=(TextView)itemView.findViewById(R.id.like_count);
            PhotoPreview=(ImageView)itemView.findViewById(R.id.preview_img);
            DetailCard=(CardView)itemView.findViewById(R.id.detail_review_preview_card);
            DetailCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final TwoImgFourStringCardView item=mDataSet.get(getAdapterPosition());
            Intent intent = new Intent(context, ViewDetailActivity.class);
            intent.putExtra("title",item.getT3());
            intent.putExtra("like",item.getT2());
            intent.putExtra("profile",item.getImg1());
            intent.putExtra("id",item.getT1());

            context.startActivity(intent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TwoImgFourStringCardView item=mDataSet.get(position);
        //Drawable drawable1=context.getResources().getDrawable(item.getImg1());
       // Drawable drawable2=context.getResources().getDrawable(item.getImg2());
        //holder.ProfilePicture.setImageResource(mDataSet.get(position).getImg1());
        holder.UserId.setText(item.getT1());
        holder.LikeCount.setText(mDataSet.get(position).getT2());
        holder.Title.setText(mDataSet.get(position).getT3());
        holder.Context.setText(mDataSet.get(position).getT4());
        holder.PhotoPreview.setBackgroundResource(item.getImg2());
        holder.ProfilePicture.setImageResource(item.getImg1());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
