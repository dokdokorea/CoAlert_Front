package com.example.user.coalert.Adapter.TabIngredListAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.ArrayList;

public class CombinationSkinAdapter extends RecyclerView.Adapter<CombinationSkinAdapter.ViewHolder> {
private ArrayList<OneImgOneStringCardView> mDataset;

public class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView textView;
    public ViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.combination_skin_card_risk_level);
        textView=(TextView)itemView.findViewById(R.id.combination_skin_card_ingred_name);
    }
}
    public CombinationSkinAdapter(ArrayList<OneImgOneStringCardView> myDataset){mDataset=myDataset;}
    @NonNull
    @Override
    public CombinationSkinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combination_skintype,null);
        return new CombinationSkinAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CombinationSkinAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImage());
        holder.textView.setText(mDataset.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}