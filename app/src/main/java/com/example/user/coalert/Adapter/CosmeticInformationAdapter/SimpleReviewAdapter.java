package com.example.user.coalert.Adapter.CosmeticInformationAdapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringOneNumberCardView;
import com.example.user.coalert.item.TwoImgTwoStringCardView;

import java.util.ArrayList;

public class SimpleReviewAdapter extends RecyclerView.Adapter<SimpleReviewAdapter.ViewHolder> {
    private ArrayList<OneImgOneStringOneNumberCardView> mDataSet;
    int rate;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView,one,two,three,four,five;
        private TextView title;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.profile_pic);
            title = (TextView) itemView.findViewById(R.id.context);
            one=(ImageView)itemView.findViewById(R.id.first);
            two=(ImageView)itemView.findViewById(R.id.second);
            three=(ImageView)itemView.findViewById(R.id.third);
            four=(ImageView)itemView.findViewById(R.id.fourth);
            five=(ImageView)itemView.findViewById(R.id.fifth);
            one.setImageResource(R.drawable.emptystar);
            two.setImageResource(R.drawable.emptystar);
            three.setImageResource(R.drawable.emptystar);
            four.setImageResource(R.drawable.emptystar);
            five.setImageResource(R.drawable.emptystar);
        }
    }

    public SimpleReviewAdapter(ArrayList<OneImgOneStringOneNumberCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public SimpleReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_review, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpleReviewAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataSet.get(position).getImage());
        holder.title.setText(mDataSet.get(position).getText());
        rate=mDataSet.get(position).getNumber();
        switch(rate){
            case 5:
                holder.five.setImageResource(R.drawable.fullstar);
            case 4:
                holder.four.setImageResource(R.drawable.fullstar);
            case 3:
                holder.three.setImageResource(R.drawable.fullstar);
            case 2:
                holder.two.setImageResource(R.drawable.fullstar);
            case 1:
                holder.one.setImageResource(R.drawable.fullstar);
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
