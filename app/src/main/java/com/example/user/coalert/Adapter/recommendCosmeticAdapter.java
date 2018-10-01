package com.example.user.coalert.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.recommendCosmeticItem;

import java.util.ArrayList;

public class recommendCosmeticAdapter extends BaseAdapter {
    ArrayList<recommendCosmeticItem> itemList = new ArrayList();
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_recommend_cosmetic_show_item, viewGroup, false);
        }
        TextView Cname = view.findViewById(R.id.recommendCname);
        TextView percent = view.findViewById(R.id.matchingPercent);
        recommendCosmeticItem item = (recommendCosmeticItem) getItem(i);
        Cname.setText(item.getCname());
        percent.setText(String.valueOf(item.getPercent()));
        return view;
    }
    public void addItem(String Cname, String estimate){
        recommendCosmeticItem recommendCosmeticItem = new recommendCosmeticItem();
        recommendCosmeticItem.setCname(Cname);
        recommendCosmeticItem.setPercent(Float.parseFloat(estimate)*20);
        itemList.add(recommendCosmeticItem);
    }
}
