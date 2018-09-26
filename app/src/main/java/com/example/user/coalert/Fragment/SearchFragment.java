package com.example.user.coalert.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.user.coalert.Adapter.searchAdapter;
import com.example.user.coalert.R;
import java.util.ArrayList;
import java.util.List;


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
        final View v = inflater.inflate(R.layout.fragment_search, container, false);
        final ViewFlipper viewFlipper = v.findViewById(R.id.viewFlipper);
        edit = v.findViewById(R.id.editSearch);
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean haveFocus) {
        ListView listView = v.findViewById(R.id.search_list);
                if (haveFocus){
                    viewFlipper.setDisplayedChild(1);
                    final List<String> list = new ArrayList<>();
                    settingList(list);
                    final ArrayList<String> arrayList = new ArrayList<String>(list);
                    final searchAdapter searchAdapter = new searchAdapter(list, getActivity());
                    listView.setAdapter(searchAdapter);
                    edit.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            String text = edit.getText().toString();
                            search(text, list, arrayList, searchAdapter);
                        }
                    });
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

    public void search(String findText, List<String> list, ArrayList<String> arrayList, searchAdapter searchAdapter){
        list.clear();

        if(findText.length() == 0){
            list.addAll(arrayList);
        }
        else{
            for(int i = 0; i<arrayList.size(); i++){
                if(arrayList.get(i).toLowerCase().contains(findText)){
                    list.add(arrayList.get(i));
                }
            }
        }
        searchAdapter.notifyDataSetChanged();
    }
    void settingList(List<String> list){
        list.add("슬기");
        list.add("슬기의 파우치");
        list.add("수지");
        list.add("수지의 화장품");
        list.add("크리스탈 비비 크림");
        list.add("크리스탈");
        list.add("손나은 쉐이딩");
        list.add("손나은");
        list.add("홍진영의 눈 커지는 비법");
        list.add("루이");
        list.add("진영");
        list.add("슬기");
        list.add("설리");
        list.add("김예림");
        list.add("혜리");
        list.add("허영지");
    }

}
