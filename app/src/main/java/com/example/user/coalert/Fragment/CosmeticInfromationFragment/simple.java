package com.example.user.coalert.Fragment.CosmeticInfromationFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.coalert.R;

import org.w3c.dom.Text;

public class simple extends Fragment{

    TextView tv;

    public simple() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_simple_review_list, container, false);

        tv=(TextView)v.findViewById(R.id.sample);
        tv.setText("CHANGE");


        return v;
    }
}
