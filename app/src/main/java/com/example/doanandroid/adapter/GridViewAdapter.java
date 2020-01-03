package com.example.doanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid.R;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private String[] tiem;
    private int[] hinh;

    public GridViewAdapter(Context context, String[] tiem, int[] hinh) {
        this.context = context;
        this.tiem = tiem;
        this.hinh = hinh;
    }

    @Override
    public int getCount() {
        return tiem.length;
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.grid_row,null);
        TextView textView=view.findViewById(R.id.tiem);
        ImageView imageView=view.findViewById(R.id.img);

        textView.setText(tiem[i]);
        imageView.setImageResource(hinh[i]);
        return view;
    }
}
