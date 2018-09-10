//package com.example.user.coalert.Adapter;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.user.coalert.R;
//
//public class TextInSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item, parent, false);
//        //xml 디자인 한 부분 적용
//        return new RowCell(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        //xml 디자인 한 부분에 안에 내용변경
//    }
//
//    @Override
//    public int getItemCount() {
//
//        //아이템을 측정하는 카운터
//        return 0;
//    }
//
//    private class RowCell extends RecyclerView.ViewHolder {
//        public TextView textView;
//        public RowCell(View view) {
//            super(view);
//            textView = view.findViewById(R.id.search_input_view);
//        }
//    }
//}
