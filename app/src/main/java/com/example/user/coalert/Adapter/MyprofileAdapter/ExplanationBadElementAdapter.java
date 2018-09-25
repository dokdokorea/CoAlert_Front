package com.example.user.coalert.Adapter.MyprofileAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.ExplanationBadElementCardView;

import java.util.ArrayList;

public class ExplanationBadElementAdapter extends RecyclerView.Adapter<ExplanationBadElementAdapter.ViewHolder> {
    private ArrayList<ExplanationBadElementCardView> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rank;
        private TextView element_name;

        public ViewHolder(View itemView) {
            super(itemView);
            rank = (TextView) itemView.findViewById(R.id.bad_ele_rank);
            element_name = (TextView) itemView.findViewById(R.id.bad_element_name);
        }
    }

    public ExplanationBadElementAdapter(ArrayList<ExplanationBadElementCardView> myDataset) {
        mDataSet = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expl_bad_element, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rank.setText(mDataSet.get(position).getRank());
        holder.element_name.setText(mDataSet.get(position).getElement_name());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
