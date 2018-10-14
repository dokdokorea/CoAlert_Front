package com.example.user.coalert.Adapter.FragmentHomeElementAdapter;

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
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class BestReviewPreviewAdapter extends RecyclerView.Adapter<BestReviewPreviewAdapter.ViewHolder> {
    private ArrayList<OneImgOneStringCardView> mDataSet;
    Context context;
    int item_layout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Title,CreatorId;
        private ImageView TitleImg,CreatorImg;
        private CardView ReviewCard;

        public ViewHolder(View itemView) {
            super(itemView);
            TitleImg = (ImageView) itemView.findViewById(R.id.title_img);
            Title=(TextView)itemView.findViewById(R.id.title_text);
            ReviewCard=(CardView)itemView.findViewById(R.id.detail_review_card);
        }
    }

    public BestReviewPreviewAdapter(Context context, ArrayList<OneImgOneStringCardView> myDataset, int item_layout) {
        mDataSet = myDataset;
        this.context=context;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public BestReviewPreviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review_in_home, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final BestReviewPreviewAdapter.ViewHolder holder, int position) {
        final OneImgOneStringCardView item=mDataSet.get(position);
        holder.TitleImg.setBackgroundResource(mDataSet.get(position).getImage());
        holder.Title.setText(mDataSet.get(position).getText());
        holder.ReviewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goes to new activity passing the item name
                Intent intent = new Intent(context, ViewDetailActivity.class);
                intent.putExtra("title",item.getText());
                context.startActivity(intent);

                //Toast.makeText(context,mDataSet.get(position).getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
