package com.example.user.coalert.Adapter.MyprofileAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import com.example.user.coalert.Singleton.ForRestSingleton;
import com.example.user.coalert.forRestServer.GetBadIngredientModel;
import com.example.user.coalert.item.OneImgOneStringCardView;

import java.util.List;

import retrofit2.Call;

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
//        holder.image.setImageResource(item.getImage());
        holder.name.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        CardView cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.cosphoto);
            name=(TextView)itemView.findViewById(R.id.Name);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Call<List<GetBadIngredientModel>> call = ForRestSingleton.getInstance().ingredientPerCosmetic(list.get(getAdapterPosition()).getText().split("_")[1],
                                String.valueOf(list.get(getAdapterPosition()).getWhatKind()));
                        List<GetBadIngredientModel> result = call.execute().body();
                        Intent intent = new Intent(context, CosmeticInformationActivity.class);
                        intent.putExtra("check", 0);
                        intent.putExtra("cname", list.get(getAdapterPosition()).getText().split("_")[1]);
                        intent.putExtra("image", list.get(getAdapterPosition()).getImage());
                        intent.putExtra("kind", list.get(getAdapterPosition()).getWhatKind());
                        intent.putExtra("company", list.get(getAdapterPosition()).getText().split("_")[0]);

                        intent.putExtra("ingredient", result.toString());
                        intent.putExtra("check", 0);

                        context.startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    }
}