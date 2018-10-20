package com.example.user.coalert.Adapter.TabIngredListAdapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import com.example.user.coalert.item.TwoStringCardView;

import java.util.ArrayList;

public class TabIngredientListAdapter extends RecyclerView.Adapter<TabIngredientListAdapter.ViewHolder> {
    private ArrayList<TwoStringCardView> mDataset;

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView toxic_level;
        private ImageView toxic_circle;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_ingredient_list_ingredient_name);
            toxic_level=(TextView)itemView.findViewById(R.id.toxic_level);
            toxic_circle=(ImageView)itemView.findViewById(R.id.circle_color);
        }
    }

    public TabIngredientListAdapter(ArrayList<TwoStringCardView> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient_list, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position).getText1().replaceAll("\"", ""));
        holder.toxic_level.setText(String.valueOf(Math.abs(Integer.parseInt(mDataset.get(position).getText2()))));
        setColor(holder,Math.abs(Integer.parseInt(mDataset.get(position).getText2())));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setColor(@NonNull ViewHolder holder, int level){    // 색상 설정
        if(level<=2){
            holder.toxic_level.setTextColor(Color.parseColor("#3ADF00"));
            holder.toxic_circle.setImageResource(R.color.ingredient_safe);
        }else if(level<=4){
            holder.toxic_level.setTextColor(Color.parseColor("#FFFF00"));
            holder.toxic_circle.setImageResource(R.color.ingredient_soso);
        }else if(level<=6){
            holder.toxic_level.setTextColor(Color.parseColor("#FF8000"));
            holder.toxic_circle.setImageResource(R.color.ingredient_alert);
        }
        else{
            holder.toxic_level.setTextColor(Color.parseColor("#FE2E2E"));
            holder.toxic_circle.setImageResource(R.color.ingredient_danger);
        }
    }

}
