package com.example.user.coalert.Adapter.CosmeticInformationAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.Activity.ViewDetailActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class DetailReviewAdapter extends RecyclerView.Adapter<DetailReviewAdapter.ViewHolder> {
    private ArrayList<TwoImgTwoStringCardView> mDataSet;
    Context context;
    int item_layout;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView Title,CreatorId;
        private ImageView TitleImg,CreatorImg;
        private CardView PreviewCard;

        public ViewHolder(View itemView) {
            super(itemView);
            TitleImg = (ImageView) itemView.findViewById(R.id.title_img);
            CreatorImg=(ImageView)itemView.findViewById(R.id.profile_pic);
            Title=(TextView)itemView.findViewById(R.id.title_text);
            CreatorId=(TextView)itemView.findViewById(R.id.profile_id);
            PreviewCard=(CardView)itemView.findViewById(R.id.detail_review_card);
            PreviewCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final TwoImgTwoStringCardView item=mDataSet.get(getAdapterPosition());
            Intent intent = new Intent(context, ViewDetailActivity.class);
            intent.putExtra("title",item.getText2());
            intent.putExtra("id",item.getText1());
            intent.putExtra("profile",item.getImg1());

            if(getAdapterPosition()==0){
                intent.putExtra("like","2103");
            }else if(getAdapterPosition()==1){
                intent.putExtra("like","4932");
            }else if(getAdapterPosition()==2){
                intent.putExtra("like","8732");
            }else{
                intent.putExtra("like","3306");
            }
            context.startActivity(intent);
        }
    }

    public DetailReviewAdapter(Context context,ArrayList<TwoImgTwoStringCardView> myDataset,int item_layout) {
        mDataSet = myDataset;
        this.context=context;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public DetailReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailReviewAdapter.ViewHolder holder, int position) {
        holder.TitleImg.setBackgroundResource(mDataSet.get(position).getImg2());
        holder.Title.setText(mDataSet.get(position).getText2());
        holder.CreatorId.setText(mDataSet.get(position).getText1());
        holder.CreatorImg.setImageResource(mDataSet.get(position).getImg1());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
