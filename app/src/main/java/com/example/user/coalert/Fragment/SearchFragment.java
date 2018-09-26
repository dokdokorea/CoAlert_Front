package com.example.user.coalert.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.user.coalert.R;



public class SearchFragment extends Fragment {
    FragmentTransaction fragmentTransaction;
    EditText edit;
    LinearLayout linearLayout;
    InputMethodManager imm;
    public SearchFragment() {
        // Required empty public constructor
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"ClickableViewAccessibility", "ServiceCast"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        final ViewFlipper viewFlipper = v.findViewById(R.id.viewFlipper);
        edit = v.findViewById(R.id.editSearch);
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean haveFocus) {
                if (haveFocus){
                    viewFlipper.setDisplayedChild(1);
                }else{
                    viewFlipper.setDisplayedChild(0);
                }
            }
        });

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e("touch: ","my body");
                edit.clearFocus();
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                return true;
            }
        });
        return v;
    }


}
