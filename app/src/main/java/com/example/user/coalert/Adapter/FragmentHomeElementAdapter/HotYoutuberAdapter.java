package com.example.user.coalert.Adapter.FragmentHomeElementAdapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class HotYoutuberAdapter extends RecyclerView.Adapter<HotYoutuberAdapter.ViewHolder> {
    private ArrayList<OneImgTwoStringCardView> mDataSet;
    Context context;
    int item_layout;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;
        private String url;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.youtube);
            textView = (TextView) itemView.findViewById(R.id.youtube_title);
            cardView=itemView.findViewById(R.id.hot_youtuber_cardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse( "http://youtu.be/" + mDataSet.get(getAdapterPosition()).getText2() ));
            context.startActivity( intent );
        }
    }

    public HotYoutuberAdapter(Context context, ArrayList<OneImgTwoStringCardView> myDataset, int item_layout) {
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
        holder.imageView.setBackgroundResource(mDataSet.get(position).getImage());
        holder.textView.setText(mDataSet.get(position).getText1());
        holder.url=mDataSet.get(position).getText2();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
