package com.example.user.coalert.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.user.coalert.Activity.EditprofileActivity;
import com.example.user.coalert.Adapter.MyprofileFollowerAdapter;
import com.example.user.coalert.Adapter.MyprofileRecyclerViewAdapter;
import com.example.user.coalert.R;
import com.example.user.coalert.item.CosmeticList_mypage;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MyProfileFragment extends Fragment{
    List<String> list;
    Button Edit;
public MyProfileFragment(){

}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View v=inflater.inflate(R.layout.fragment_myprofile,container,false);
        Edit=(Button)v.findViewById(R.id.edit_personal_info);
        RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
        RecyclerView cosmeticList=(RecyclerView)v.findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        int ColumNumber=3;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        cosmeticList.setHasFixedSize(true);
        cosmeticList.setLayoutManager(new GridLayoutManager(getContext(),ColumNumber));
        cosmeticList.setNestedScrollingEnabled(false);


        List<CosmeticList_mypage> items=new ArrayList<>();
        CosmeticList_mypage[] item=new CosmeticList_mypage[5];
        item[0]=new CosmeticList_mypage(R.drawable.cardview1,"슬기1");
        item[1]=new CosmeticList_mypage(R.drawable.cardview2,"슬기2");
        item[2]=new CosmeticList_mypage(R.drawable.cardview3,"슬기3");
        item[3]=new CosmeticList_mypage(R.drawable.cardview4,"슬기4");
        item[4]=new CosmeticList_mypage(R.drawable.cardview5,"슬기5");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new MyprofileFollowerAdapter(getApplicationContext(),items,R.layout.fragment_myprofile));

        List<CosmeticList_mypage> cositems=new ArrayList<>();
        CosmeticList_mypage[] cositem=new CosmeticList_mypage[6];
        cositem[0]=new CosmeticList_mypage(R.drawable.cardview1,"슬기1");
        cositem[1]=new CosmeticList_mypage(R.drawable.cardview2,"슬기2");
        cositem[2]=new CosmeticList_mypage(R.drawable.cardview3,"슬기3");
        cositem[3]=new CosmeticList_mypage(R.drawable.cardview4,"슬기4");
        cositem[4]=new CosmeticList_mypage(R.drawable.cardview5,"슬기5");
        cositem[5]=new CosmeticList_mypage(R.drawable.irin,"irin");


        for(int i=0;i<6;i++) cositems.add(cositem[i]);
        cosmeticList.setAdapter(new MyprofileRecyclerViewAdapter(getApplicationContext(),cositems,R.layout.fragment_myprofile));

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EditprofileActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}


