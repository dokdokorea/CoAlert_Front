package com.example.user.coalert.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;
import java.util.ArrayList;

public class NotFocusSearchAdapter extends Adapter<NotFocusSearchAdapter.ViewHoler> {
    ArrayList<OneImgOneStringCardView> data = null;
    public NotFocusSearchAdapter(ArrayList<OneImgOneStringCardView> inputData ) {
        data = inputData;
        for(int i = 0; i < data.size();i++){
            Log.e("arrayListData",data.get(i).getText());
        }
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_not_focus_search, null,false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Log.e("asdasd", data.get(position).getText());
        holder.cosmeticCompanyImage.setImageResource(data.get(position).getImage());
        holder.cosmeticCompanyName.setText(data.get(position).getText());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        ImageView cosmeticCompanyImage;
        TextView cosmeticCompanyName;

        public ViewHoler(View itemView) {
            super(itemView);
            cosmeticCompanyImage = itemView.findViewById(R.id.cosmetic_company_image);
            cosmeticCompanyName = itemView.findViewById(R.id.cosmetic_company_name);
        }
    }
}
