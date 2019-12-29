package com.example.doanandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.InGameActivity;
import com.example.doanandroid.R;
import com.example.doanandroid.model.Money;

import java.util.ArrayList;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {
    ArrayList<Money>monies;
    Context context;
    public int bienstt=0;
    public void getstt(int i){
        bienstt=i;
    }

    public MoneyAdapter(ArrayList<Money> monies, Context context) {
        this.monies = monies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());


        View itemView=layoutInflater.inflate(R.layout.item_money,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(monies.get(position).getiD()));
        holder.txtMoney.setText(String.valueOf(monies.get(position).getMoney()));
        int i=bienstt;
        if(position==i){
            holder.linearLayout.setBackgroundColor(Color.YELLOW);
        }


//        holder.linearLayout.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public int getItemCount() {
        return monies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtMoney;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId=itemView.findViewById(R.id.textViewid);
            txtMoney=itemView.findViewById(R.id.textViewmoney);
            linearLayout=itemView.findViewById(R.id.layoutItemMoney);
        }
    }

}
