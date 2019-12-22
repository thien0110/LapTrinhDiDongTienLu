package com.example.doanandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentBangDiem extends Fragment{
    TextView txtcau1;
    TextView txtcau2;
    TextView txtcau3;
    TextView txtcau4;
    TextView txtcau5;
    TextView txtcau6;
    TextView txtcau7;
    TextView txtcau8;
    TextView txtcau9;
    TextView txtcau10;
    TextView txtcau11;
    TextView txtcau12;
    TextView txtcau13;
    TextView txtcau14;
    TextView txtcau15;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bangdiem,container,false);
        txtcau1=view.findViewById(R.id.textView1);
        txtcau2=view.findViewById(R.id.textView2);
        txtcau3=view.findViewById(R.id.textView3);
        txtcau4=view.findViewById(R.id.textView4);
        txtcau5=view.findViewById(R.id.textView5);
        txtcau6=view.findViewById(R.id.textView6);
        txtcau7=view.findViewById(R.id.textView7);
        txtcau8=view.findViewById(R.id.textView8);
        txtcau9=view.findViewById(R.id.textView9);
        txtcau10=view.findViewById(R.id.textView10);
        txtcau11=view.findViewById(R.id.textView11);
        txtcau12=view.findViewById(R.id.textView12);
        txtcau13=view.findViewById(R.id.textView13);
        txtcau14=view.findViewById(R.id.textView14);
        txtcau15=view.findViewById(R.id.textView15);

        return view;
    }
    public void nayDiem(){
        txtcau1.setBackgroundColor(Color.RED);
    }
}
