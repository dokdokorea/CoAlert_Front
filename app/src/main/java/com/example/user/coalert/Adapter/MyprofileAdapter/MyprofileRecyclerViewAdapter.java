package com.example.user.coalert.Adapter.MyprofileAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Activity.AnotherprofileActivity;
import com.example.user.coalert.Activity.CosmeticInformationActivity;
import com.example.user.coalert.Activity.CosmeticIngredientActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.List;

public class MyprofileRecyclerViewAdapter extends RecyclerView.Adapter<MyprofileRecyclerViewAdapter.ViewHolder>{
    Context context;
    List <OneImgOneStringCardView> list;
    int item_layout;
    public MyprofileRecyclerViewAdapter(Context context, List<OneImgOneStringCardView> items, int item_layout){
        this.context = context;
        list = items;
        this.item_layout =item_layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mapage_cometics, null);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OneImgOneStringCardView item = list.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImage());
        holder.image.setBackground(drawable);
        holder.name.setText(item.getText());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CosmeticInformationActivity.class);
                context.startActivity(intent);

                Toast.makeText(context,item.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        CardView cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.cosphoto);
            name=(TextView)itemView.findViewById(R.id.Name);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}