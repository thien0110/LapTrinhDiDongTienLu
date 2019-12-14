package com.example.doanandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doanandroid.R;
import com.example.doanandroid.model.Score;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {
    private Context myContext;
    private int myLayout;
    private List<Score>arrScore;

    public ScoreAdapter(Context context, int layout, List<Score>lstScore){
        myContext=context;
        myLayout=layout;
        arrScore=lstScore;
    }
    @Override
    public int getCount() {
        return arrScore.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(myLayout,null);
        TextView tv_number=view.findViewById(R.id.textViewCauhoiNum);
        TextView tv_money=view.findViewById(R.id.textViewMoney);
        tv_number.setText(String.valueOf(arrScore.get(i).mNumber));
        tv_money.setText(arrScore.get(i).mMoney +" VND");
        return view;
    }
}
