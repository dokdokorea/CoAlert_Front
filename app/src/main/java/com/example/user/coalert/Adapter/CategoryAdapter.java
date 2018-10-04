package com.example.user.coalert.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.coalert.Activity.recommendCosmeticShow;
import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.Singleton.UUFiSingleton;
import com.example.user.coalert.forRestServer.getRecommendModel;
import com.example.user.coalert.item.OneImageCardView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<OneImageCardView> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.item_follower_list_circleimageview);
        }
    }

    public CategoryAdapter(ArrayList<OneImageCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_category_skintype, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.circleImageView.setImageResource(mDataSet.get(position).getImg());
        holder.circleImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View v) {
                /*new Thread(){
                    @Override
                    public void run(){
                        super.run();
                        try{
                            Call<List<getRecommendModel>> call = ForRestSingleton.getInstance().recommendCall(0, pos+1,
                                    UUFiSingleton.getInstance().getIndependenceNum(), "0");
                            List<getRecommendModel> result = call.execute().body();
                            String moveRecommendCosmetic = result.toString();
                            Intent recommendPage = new Intent(v.getContext() , recommendCosmeticShow.class);
                            recommendPage.putExtra("cname", mDataSet[pos]);
                            recommendPage.putExtra("recommendData", moveRecommendCosmetic);
                            v.getContext().startActivity(recommendPage);
                            v.finish();
                        } catch (IOException e){e.printStackTrace();}
                    }
                }.start();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
