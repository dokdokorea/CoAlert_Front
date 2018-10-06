package com.example.user.coalert.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.coalert.R;

import java.util.List;

public class searchAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    LayoutInflater inflater;
    ViewHolder viewHolder;

    public searchAdapter(List<String> list, Context context){
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.search_row_view, null);
            viewHolder = new ViewHolder();
            viewHolder.label = view.findViewById(R.id.label);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.label.setText(list.get(i));
        return view;
    }
    class ViewHolder{
        public TextView label;
    }
}
