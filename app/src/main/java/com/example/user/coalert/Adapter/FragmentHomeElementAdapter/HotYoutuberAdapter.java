package com.example.user.coalert.Adapter.FragmentHomeElementAdapter;

import android.content.Context;
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
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.ArrayList;

public class HotYoutuberAdapter extends RecyclerView.Adapter<HotYoutuberAdapter.ViewHolder> {
    private ArrayList<OneImgOneStringCardView> mDataSet;
    Context context;
    int item_layout;

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.youtube);
            textView = (TextView) itemView.findViewById(R.id.youtube_title);
            cardView=itemView.findViewById(R.id.hot_youtuber_cardview);
        }
    }

    public HotYoutuberAdapter(Context context,ArrayList<OneImgOneStringCardView> myDataset,int item_layout) {
        mDataSet = myDataset;
        this.context=context;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public HotYoutuberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_view, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotYoutuberAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageResource(mDataSet.get(position).getImage());
        holder.textView.setText(mDataSet.get(position).getText());
        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context,mDataSet.get(position).getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
