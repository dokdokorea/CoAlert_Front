package com.example.user.coalert.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
        //edit Text 포커스가 있냐 없냐에 따라서
        //이벤트 구분
        //ViewFlipper를 통해서 구현
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean haveFocus) {
                ListView listView = v.findViewById(R.id.search_list);
                if (haveFocus){
                    viewFlipper.setDisplayedChild(1);
                    //List는 인터페이스이고
                    //list는 순간 순간 변하는 데이터들을 저장하고 뺴고 하기위한 역활
                    final List<String> list = new ArrayList<>();
                    settingList(list);
                    //ArrayList는 List를 상속받아서 구현하고 있다.
                    //arrayList는 list의 데이터를 복사해서 갖고 있다.
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
// 화면 누르면  focus제거
//        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.e("touch: ","my body");
//                edit.clearFocus();
//                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
//                return true;
//            }
//
//        });

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
//만든 이유
//EditText 커스텀하여 뒤로가기 버튼을 눌렀을 때 editText의 focus제거 해준다.
class ExEditText extends android.support.v7.widget.AppCompatEditText {

    public ExEditText(Context context) {
        super(context);
    }

    public ExEditText(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            if(keyCode == KeyEvent.KEYCODE_BACK){
                Log.e("asdasd", "Asdasdasd");
                this.clearFocus();
                return true;
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
