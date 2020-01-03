package com.example.doanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.R;
import com.example.doanandroid.model.BangXepHang;
import com.example.doanandroid.model.LichSu;

import java.util.ArrayList;

public class BXHAdapter extends RecyclerView.Adapter<BXHAdapter.ViewHolder> {
    ArrayList<BangXepHang>bangXepHangs;
    Context context;


    public BXHAdapter(ArrayList<BangXepHang> bangXepHangs, Context context) {
        this.bangXepHangs = bangXepHangs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());


        View itemView=layoutInflater.inflate(R.layout.item_bxh,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMoney.setText(String.valueOf(bangXepHangs.get(position).SoTien));
        holder.txtTen.setText(String.valueOf(bangXepHangs.get(position).Ten));
        holder.txthang.setText(String.valueOf(bangXepHangs.get(position).Hang));


//        holder.linearLayout.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public int getItemCount() {
        return bangXepHangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen;
        TextView txtMoney;
        TextView txthang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMoney=itemView.findViewById(R.id.textViewSoDiembxh);
            txtTen=itemView.findViewById(R.id.textViewTenbxh);
            txthang=itemView.findViewById(R.id.hang);

        }
    }

}
