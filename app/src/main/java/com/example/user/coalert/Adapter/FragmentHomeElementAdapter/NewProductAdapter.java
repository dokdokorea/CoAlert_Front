package com.example.user.coalert.Adapter.FragmentHomeElementAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.coalert.Activity.CosmeticInformationActivity;
import com.example.user.coalert.R;
import com.example.user.coalert.item.OneImgTwoStringCardView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {
    private ArrayList<OneImgTwoStringCardView> mDataset;
    Context context;
    int item_layout;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView prodNameView;
        private TextView companyView;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.new_product_image);
            prodNameView = (TextView) itemView.findViewById(R.id.new_product_name);
            companyView = (TextView) itemView.findViewById(R.id.company_name);
            cardView=itemView.findViewById(R.id.new_product_cardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bitmap sendBitmap = BitmapFactory.decodeResource(context.getResources(),mDataset.get(getAdapterPosition()).getImage());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();


            Intent intent=new Intent(context, CosmeticInformationActivity.class);
            intent.putExtra("picture",mDataset.get(getAdapterPosition()).getImage());
//            intent.putExtra("image",byteArray);
            intent.putExtra("cname",mDataset.get(getAdapterPosition()).getText1());
            intent.putExtra("company",mDataset.get(getAdapterPosition()).getText2());
            context.startActivity(intent);
//            Toast.makeText(context,mDataset.get(getAdapterPosition()).getText2(),Toast.LENGTH_SHORT).show();
        }
    }

    public NewProductAdapter(Context context,ArrayList<OneImgTwoStringCardView> myDataset,int item_layout) {
        mDataset = myDataset;
        this.context=context;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public NewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_product, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageResource(mDataset.get(position).getImage());
        holder.prodNameView.setText(mDataset.get(position).getText1());
        holder.companyView.setText(mDataset.get(position).getText2());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
