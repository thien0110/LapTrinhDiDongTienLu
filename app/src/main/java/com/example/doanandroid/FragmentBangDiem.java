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
//    TextView txtcau1;
//    TextView txtcau2;
//    TextView txtcau3;
//    TextView txtcau4;
//    TextView txtcau5;
//    TextView txtcau6;
//    TextView txtcau7;
//    TextView txtcau8;
//    TextView txtcau9;
//    TextView txtcau10;
//    TextView txtcau11;
//    TextView txtcau12;
//    TextView txtcau13;
//    TextView txtcau14;
//    TextView txtcau15;
    TextView score;
    static int score_id;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_bangdiem,container,false);


        return view;
    }
    public void UpScore(int pos){
        score_id=pos;
        switch (score_id) {
            case 0:
                score = view.findViewById(R.id.textView1);
                break;
            case 1:
                score = view.findViewById(R.id.textView2);
                break;
            case 2:
                score = view.findViewById(R.id.textView3);
                break;
            case 3:
                score = view.findViewById(R.id.textView4);
                break;
            case 4:
                score = view.findViewById(R.id.textView5);
                break;
            case 5:
                score = view.findViewById(R.id.textView6);
                break;
            case 6:
                score = view.findViewById(R.id.textView7);
                break;
            case 7:
                score = view.findViewById(R.id.textView8);
                break;
            case 8:
                score = view.findViewById(R.id.textView9);
                break;
            case 9:
                score = view.findViewById(R.id.textView10);
                break;
            case 10:
                score = view.findViewById(R.id.textView11);
                break;
            case 11:
                score = view.findViewById(R.id.textView12);
                break;
            case 12:
                score = view.findViewById(R.id.textView13);
                break;
            case 13:
                score = view.findViewById(R.id.textView14);
                break;
            case 14:
                score = view.findViewById(R.id.textView15);
                break;
        }

        score.setBackgroundColor(Color.YELLOW);
    }
//    public void anhxa(View view){
//        txtcau1=view.findViewById(R.id.textView1);
//        txtcau2=view.findViewById(R.id.textView2);
//        txtcau3=view.findViewById(R.id.textView3);
//        txtcau4=view.findViewById(R.id.textView4);
//        txtcau5=view.findViewById(R.id.textView5);
//        txtcau6=view.findViewById(R.id.textView6);
//        txtcau7=view.findViewById(R.id.textView7);
//        txtcau8=view.findViewById(R.id.textView8);
//        txtcau9=view.findViewById(R.id.textView9);
//        txtcau10=view.findViewById(R.id.textView10);
//        txtcau11=view.findViewById(R.id.textView11);
//        txtcau12=view.findViewById(R.id.textView12);
//        txtcau13=view.findViewById(R.id.textView13);
//        txtcau14=view.findViewById(R.id.textView14);
//        txtcau15=view.findViewById(R.id.textView15);
//    }
}
