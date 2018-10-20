package com.example.user.coalert.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.coalert.Activity.recommendCosmeticShow;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class kindCosmeticAdapter extends RecyclerView.Adapter<kindCosmeticAdapter.ViewHolder> {
    ArrayList<OneImgOneStringCardView> realData;
    Context context;

    public kindCosmeticAdapter(ArrayList<OneImgOneStringCardView> data, Context context) {
        this.realData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_what_select_kind_cosmetic_item, null);
        return new kindCosmeticAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(realData.get(position).getText());
        holder.backImage.setBackgroundResource(realData.get(position).getImage());
        holder.backImage.setAlpha(0.6f);

    }

    @Override
    public int getItemCount() {
        return realData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        FrameLayout frameLayout;
        ImageView backImage;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.selectKindCosmeticItem);
            textView.setOnClickListener(this);
            frameLayout = itemView.findViewById(R.id.kind_background);
            backImage = itemView.findViewById(R.id.kind_background_image);
        }

        @Override
        public void onClick(View view) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Call<List<getRecommendModel>> call = ForRestSingleton.getInstance().recommendCall(0, getAdapterPosition() + 1, "0", 0);
                        List<getRecommendModel> result = call.execute().body();
                        String moveRecommendCosmetic = result.toString();
                        Intent recommendPage = new Intent(context, recommendCosmeticShow.class);
                        recommendPage.putExtra("kindCosmetic", getAdapterPosition());
                        recommendPage.putExtra("cname", realData.get(getAdapterPosition()).getText());
                        recommendPage.putExtra("recommendData", moveRecommendCosmetic);
                        context.startActivity(recommendPage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
