package com.example.user.coalert.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.TabIngredientListCardView;

import java.util.ArrayList;

public class TabIngredientListAdapter extends RecyclerView.Adapter<TabIngredientListAdapter.ViewHolder> {
    private ArrayList<TabIngredientListCardView> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_ingredient_list_risk_level);
            textView = (TextView) itemView.findViewById(R.id.item_ingredient_list_ingredient_name);
        }
    }

    public TabIngredientListAdapter(ArrayList<TabIngredientListCardView> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_ingredient_list, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImg());
        holder.textView.setText(mDataset.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
