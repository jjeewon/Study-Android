package com.jiwon.recyclerviewsample;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.ViewHolder> {
    protected List<String> dataset;
    private View.OnClickListener onItemViewListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView textView;
        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.simple_text_view);
        }

    }

    // 이번에는 생성자로 데이터를 넘겨준다.
    public SimpleStringAdapter(List<String> myDataSet){
        dataset = myDataSet;
    }

    public void setOnItemViewListener(View.OnClickListener onItemViewListener){
        this.onItemViewListener = onItemViewListener;
    }

    // 새로운 ViewHolder를 작성한다.(LayoutManager에서 호출한다)
    @Override
    public SimpleStringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // 새로운 View를 만든다.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_row,parent,false);
        // View에 클릭 리스너를 붙인다.
        if (onItemViewListener != null) v.setOnClickListener(onItemViewListener);

        //데이터와 관련없는 레이아웃 조정은 여기서한다.(여기서 만든 레이아웃을 돌려쓰기 위하여)
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // 뷰 안의 데이터를 변경한다.(LayoutManger에서 호출된다.)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // 설정할 데이터를 가져온다.
        String text = dataset.get(position);
        // ViewHolder의 View 안의 데이터를 변경한다.
        holder.textView.setText(text);
    }

    // 데이터 수를 반환한다.(LayoutManager에서 호출된다.)
    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
