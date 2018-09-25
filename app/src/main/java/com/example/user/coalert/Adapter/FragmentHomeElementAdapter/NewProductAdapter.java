package com.example.user.coalert.Adapter.FragmentHomeElementAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.NewProductCardView;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.util.ArrayList;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {
    private ArrayList<OneImgTwoStringCardView> mDataset;

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView prodNameView;
        private TextView companyView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.new_product_image);
            prodNameView = (TextView) itemView.findViewById(R.id.new_product_name);
            companyView = (TextView) itemView.findViewById(R.id.company_name);
        }
    }

    public NewProductAdapter(ArrayList<OneImgTwoStringCardView> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public NewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_product, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImage());
        holder.prodNameView.setText(mDataset.get(position).getText1());
        holder.companyView.setText(mDataset.get(position).getText2());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
