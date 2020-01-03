package com.example.doanandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.R;
import com.example.doanandroid.model.LichSu;
import com.example.doanandroid.model.Money;

import java.util.ArrayList;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.ViewHolder> {
    ArrayList<LichSu>lichSus;
    Context context;


    public LichSuAdapter(ArrayList<LichSu> lichSus, Context context) {
        this.lichSus = lichSus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());


        View itemView=layoutInflater.inflate(R.layout.item_lsc,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMoney.setText(String.valueOf(lichSus.get(position).SoDiem));
        holder.txtCau.setText(String.valueOf(lichSus.get(position).SoCau));


//        holder.linearLayout.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public int getItemCount() {
        return lichSus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMoney;
        TextView txtCau;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMoney=itemView.findViewById(R.id.textViewSoDiemls);
            txtCau=itemView.findViewById(R.id.textViewSoCauls);

        }
    }

}
