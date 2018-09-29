package com.example.user.coalert.Adapter.CosmeticInformationAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.coalert.R;
import com.example.user.coalert.item.IngredientList;

import java.util.ArrayList;

public class ingredientAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<IngredientList> data;
    private int layout;
    public ingredientAdapter(Context context, int layout, ArrayList<IngredientList> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
    }
    @Override
    public int getCount(){return data.size();}
    @Override
    public String getItem(int position){return data.get(position).getName();}
    @Override
    public long getItemId(int position){return position;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }
        IngredientList listviewitem=data.get(position);
        TextView icon=(TextView)convertView.findViewById(R.id.rate);
        icon.setText(Double.toString(listviewitem.getNumber()));
        TextView name=(TextView)convertView.findViewById(R.id.name);
        name.setText(listviewitem.getName());
        return convertView;
    }
}