package com.example.doanandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class InGameActivity extends AppCompatActivity {
    ArrayList<QuestionOBJ> lstQuestion;
    int pos=0;
    int  pic=0;
    TextView timeCountDown;
    Button DA1;
    ImageButton Call;
    ImageButton People;
    CountDownTimer countDownTimer=null;
    TextView m_NdCauhoi;
    TextView m_btn_da1;
    TextView m_btn_da2;
    TextView m_btn_da3;
    TextView m_btn_da4;
    ImageView img_timer;
    ImageView picture;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        Anhxa();
        showQuestion(pos);//d
        CountDown();

    }
    private Boolean get_lstQuestion(String jSonString){
        try {
            lstQuestion=new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            for(int i=0;i<jr.length();i++)
            {
                JSONObject jb = (JSONObject)jr.getJSONObject(i);
                QuestionOBJ quiz=new QuestionOBJ();
                quiz.NoiDung = jb.getString("noi_dung");
                quiz.DapAn1 = jb.getString("phuong_an_A");
                quiz.DapAn2 = jb.getString("phuong_an_B");
                quiz.DapAn3 = jb.getString("phuong_an_C");
                quiz.DapAn4 = jb.getString("phuong_an_D");
                quiz.DapAn = jb.getString("dap_an");
                quiz.Chon="0";
                lstQuestion.add(quiz);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void CountDown() {

        final Animation animTimerNhapnhay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhay);
        final Animation animTimerNhapnhaycucmanh= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhaycucmanh);
        final Animation animTimerXoay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_xoay);
        countDownTimer=new CountDownTimer(/*Integer.parseInt(timeCountDown.getText().toString())*1000*/30000,1000) {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTick(long l) {
                timeCountDown.setText(""+l/1000);
                timeCountDown.startAnimation(animTimerNhapnhay);
                img_timer.startAnimation(animTimerXoay);
                int a=Integer.parseInt((String) timeCountDown.getText());
                if(a<6){
                    timeCountDown.startAnimation(animTimerNhapnhaycucmanh);
                    timeCountDown.setTextColor(Color.rgb(200,0,0));

                }else {
                    timeCountDown.setTextColor(Color.rgb(255,255,255));
                }
            }


            @Override
            public void onFinish() {
                timeCountDown.setText("0");
                //Toast.makeText(InGameActivity.this,"GAMEOVER",Toast.LENGTH_SHORT).show();
                DialogGameOver();


            }

        };
        countDownTimer.start();

    }
    public void Anhxa(){
        timeCountDown=(TextView)findViewById(R.id.textViewTime);
        DA1=(Button)findViewById(R.id.buttonDA1);
        m_NdCauhoi=(TextView)findViewById(R.id.textViewNdCauHoi);
        m_btn_da1=(Button)findViewById(R.id.buttonDA1);
        m_btn_da2=(Button)findViewById(R.id.buttonDA2);
        m_btn_da3=(Button)findViewById(R.id.buttonDA3);
        m_btn_da4=(Button)findViewById(R.id.buttonDA4);
        Call=(ImageButton)findViewById(R.id.imageButtonPhone);
        People=(ImageButton)findViewById(R.id.imageButtonPeople);
        img_timer=(ImageView)findViewById(R.id.imageViewTimer);
        picture=(ImageView)findViewById(R.id.imageViewPicture);
    }
    public void CancelCountDown(){
        if(countDownTimer!=null)
            countDownTimer.cancel();
    }
    public void showQuestion(int pos){
        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("message");
        if( get_lstQuestion(jSonString)==true)
        {
            m_NdCauhoi.setText(lstQuestion.get(pos).NoiDung);
            m_btn_da1.setText("A. "+lstQuestion.get(pos).DapAn1);
            m_btn_da2.setText("B. "+lstQuestion.get(pos).DapAn2);
            m_btn_da3.setText("C. "+lstQuestion.get(pos).DapAn3);
            m_btn_da4.setText("D. "+lstQuestion.get(pos).DapAn4);
        }
        else
        {
            m_NdCauhoi.setText("API can not run.");
            m_btn_da1.setVisibility(View.INVISIBLE);
            m_btn_da2.setVisibility(View.INVISIBLE);
            m_btn_da3.setVisibility(View.INVISIBLE);
            m_btn_da4.setVisibility(View.INVISIBLE);
        }

    }
//    public void upScore(int pic){
//        int pictureArray[]= {
//                R.drawable.picture1,
//                R.drawable.picture2,
//                R.drawable.picture3,
//                R.drawable.picture4,
//                R.drawable.picture5,
//                R.drawable.picture6,
//                R.drawable.picture7,
//                R.drawable.picture8,
//                R.drawable.picture9,
//                R.drawable.picture10,
//                R.drawable.picture11,
//                R.drawable.picture12,
//                R.drawable.picture13,
//                R.drawable.picture14,
//                R.drawable.picture15,
//                R.drawable.picture0
//        };
//
//        picture.setImageResource(pictureArray[pic]);
//
//    }
    public  void onClickTraLoi(View view) {
        pos++;
        if(pos>=lstQuestion.size()) pos = lstQuestion.size()-(lstQuestion.size()-1);
        showQuestion(pos);
        CancelCountDown();
        CountDown();
        pic++;
//        if(pic==15){
//            DialogWin();
//        }
//        upScore(pic);

    }
    public void DialogWin(){
        AlertDialog.Builder alertDialogBuilderwin = new AlertDialog.Builder(InGameActivity.this);
        alertDialogBuilderwin
                .setTitle("Thông Báo ")
                .setMessage("Bạn Đã Trở Thành Triệu Phú! ")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LinhVucActivity.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //System.exit(0);
                        finishAffinity();
                    }
                });
        alertDialogBuilderwin.show();
    }
    public  void onClickBack(View view){
        CancelCountDown();
        Intent intent=new Intent(InGameActivity.this,LinhVucActivity.class);
        startActivity(intent);
    }
    public void onClickCall(View view){
        DialogCalling();
    }
    public void onClickPeople(View view){
        DialogPeople();
    }
    public void onClick50(View view){
        m_btn_da2.setText("");
        m_btn_da4.setText("");

    }
    public void DialogCalling() {
        final Dialog dialogCall=new Dialog(this);
        dialogCall.setContentView(R.layout.dialog_calling);
        Button imageButtonEndCall=(Button)dialogCall.findViewById(R.id.buttonEndCall);
        imageButtonEndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCall.dismiss();
            }
        });
        dialogCall.show();
    }
    public void DialogGameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InGameActivity.this);
        alertDialogBuilder
                .setTitle("Thông Báo ")
                .setMessage("Hết Thời Gian! ")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LinhVucActivity.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //System.exit(0);
                        finishAffinity();
                    }
                });
        alertDialogBuilder.show();
    }
    public void DialogPeople(){
        final Dialog dialogPeople=new Dialog(this);
        dialogPeople.setContentView(R.layout.dialog_people);
        BarChart barChart=(BarChart)dialogPeople.findViewById(R.id.barChart);
        barChart.setDrawBarShadow(false);
        barChart.setTouchEnabled(false);
        barChart.setDrawValueAboveBar(false);


        ArrayList<BarEntry>barEntries=new ArrayList<>();
        Random random=new Random();
        int da1=random.nextInt(101);
        int da2=random.nextInt(101-da1);
        int da3=random.nextInt(101-(da1+da2));
        int da4=(100-(da1+da2+da3));

        barEntries.add(new BarEntry(1,da1));
        barEntries.add(new BarEntry(2,da2));
        barEntries.add(new BarEntry(3,da3));
        barEntries.add(new BarEntry(4,da4));

        BarDataSet barDataSet=new BarDataSet(barEntries,"DapAn");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.8f);
        barChart.setData(data);
        dialogPeople.show();
    }
    public void onClickPause(View view){
        final Dialog dialogPause=new Dialog(this);
        dialogPause.setContentView(R.layout.dialog_pause);
        Button buttonResume=(Button)dialogPause.findViewById(R.id.buttonResume);
        Button buttonNewGame=(Button)dialogPause.findViewById(R.id.buttonNewgame);
        Button buttonExit=(Button)dialogPause.findViewById(R.id.buttonExit);

        buttonResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPause.dismiss();
            }
        });

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InGameActivity.this,LinhVucActivity.class);
                startActivity(intent);

            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
        CancelCountDown();
        dialogPause.show();
    }


}
