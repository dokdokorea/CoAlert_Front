package com.example.user.coalert.Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;

import java.util.ArrayList;

public class LeftNavigationContentAdapter extends RecyclerView.Adapter<LeftNavigationContentAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;

    //Provide a reference to the view for each data item;
    //Complex data items may need more than one view per item, and
    //you provide access to all the view for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.company_pic);
            mTextView = (TextView) view.findViewById(R.id.company_name);
        }
    }

    //Provide a suitable constructor (depends on the kind of dataset)
    public LeftNavigationContentAdapter(ArrayList<MyData> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public LeftNavigationContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_navigation_content, parent, false);

        //set the view's size, margins, paddings and layout parameters ...

        LeftNavigationContentAdapter.ViewHolder vh = new LeftNavigationContentAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftNavigationContentAdapter.ViewHolder holder, int position) {
        //get element from your dataset at this position
        //replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).text);
        holder.mImageView.setImageResource(mDataset.get(position).img);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class MyData {
    public String text;
    public int img;

    public MyData(String text, int img) {
        this.text = text;
        this.img = img;
    }
}