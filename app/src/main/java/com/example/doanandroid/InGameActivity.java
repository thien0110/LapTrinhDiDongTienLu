package com.example.doanandroid;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.model.QuestionOBJ;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

@SuppressWarnings("deprecation")
public class InGameActivity extends AppCompatActivity {
    private boolean isPaused = false;
    private long timeRemaining = 0;
    int chon=0;
    ArrayList<QuestionOBJ> lstQuestion;
    int pos=0;
    Handler handler;
    ImageButton Call,People,btn_50,btn_reset;
    CountDownTimer countDownTimer=null;
    TextView m_NdCauhoi,timeCountDown;
    ImageView img_timer;
    Button mpa1,mpa2,mpa3,mpa4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        Anhxa();
        showQuestion(pos);
        CountDown();
//        nhacNen();
        LightStick();
        LightCircle();
        DropDollar();



    }
    public void Anhxa(){
        timeCountDown= findViewById(R.id.textViewTime);
        m_NdCauhoi= findViewById(R.id.textViewNdCauHoi);
        Call= findViewById(R.id.imageButtonPhone);
        People= findViewById(R.id.imageButtonPeople);
        img_timer= findViewById(R.id.imageViewTimer);
        mpa1=findViewById(R.id.buttonDA1);
        mpa2=findViewById(R.id.buttonDA2);
        mpa3=findViewById(R.id.buttonDA3);
        mpa4=findViewById(R.id.buttonDA4);
        btn_50=findViewById(R.id.imageButton50);
        btn_reset=findViewById(R.id.imageButtonReset);
    }
    private Boolean get_lstQuestion(String jSonString){
        try {
            lstQuestion=new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            for(int i=0;i<jr.length();i++)
            {
                JSONObject jb = jr.getJSONObject(i);
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
    public void showQuestion(int pos){
        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("message");
        if(get_lstQuestion(jSonString)==true)
        {
            m_NdCauhoi.setText(lstQuestion.get(pos).NoiDung);
            mpa1.setText("A. "+lstQuestion.get(pos).DapAn1);
            mpa2.setText("B. "+lstQuestion.get(pos).DapAn2);
            mpa3.setText("C. "+lstQuestion.get(pos).DapAn3);
            mpa4.setText("D. "+lstQuestion.get(pos).DapAn4);
        }
        else
        {
            m_NdCauhoi.setText("API can not run.");

        }

    }
    public void onClickPause(View view){
        final Dialog dialogPause=new Dialog(this);
        dialogPause.setContentView(R.layout.dialog_pause);
        Button buttonResume= dialogPause.findViewById(R.id.buttonResume);
        Button buttonNewGame= dialogPause.findViewById(R.id.buttonNewgame);
        Button buttonExit= dialogPause.findViewById(R.id.buttonExit);

        buttonResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPause.dismiss();
                ResumeTimer();
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
        PauseTimer();
        dialogPause.show();
    }




    //Xử lý trả lời câu hỏi
    public void DialogWin(){
        AlertDialog.Builder alertDialogBuilderwin = new AlertDialog.Builder(InGameActivity.this);
        alertDialogBuilderwin
                .setTitle("Thông Báo ")
                .setMessage("Xin chúc mừng, Bạn Đã Trở Thành Triệu Phú! ")
                .setCancelable(false)
                .setPositiveButton("Tiếp ván mới", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LinhVucActivity.class));
                    }
                })
                .setNegativeButton("Không chơi nữa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //System.exit(0);
                        finishAffinity();
                    }
                });
        alertDialogBuilderwin.show();
    }
    public void DialogGameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InGameActivity.this);
        alertDialogBuilder
                .setTitle("Game Over! ")
                .setMessage(" ")
                .setCancelable(false)
                .setPositiveButton("Tiếp ván mới", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LinhVucActivity.class));
                    }
                })
                .setNegativeButton("Không chơi nữa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //System.exit(0);
                        finishAffinity();
                    }
                });
        alertDialogBuilder.show();
    }
    public  void onClickTraLoi(View view) {
        switch (view.getId()){
            case R.id.buttonDA1:
                if(lstQuestion.get(pos).DapAn.equals("1")){
                    XulyDapAnDung(mpa1);
                }
                else
                {
                    XuLyDapAnSai(mpa1);
                }
                break;
            case R.id.buttonDA2:
                if(lstQuestion.get(pos).DapAn.equals("2")){
                    XulyDapAnDung(mpa2);
                }
                else
                    XuLyDapAnSai(mpa2);
                break;
            case R.id.buttonDA3:
                if(lstQuestion.get(pos).DapAn.equals("3")){
                    XulyDapAnDung(mpa3);
                }
                else
                    XuLyDapAnSai(mpa3);
                break;
            case R.id.buttonDA4:
                if(lstQuestion.get(pos).DapAn.equals("4")){
                    XulyDapAnDung(mpa4);
                }
                else
                    XuLyDapAnSai(mpa4);
                break;
        }

//        showQuestion(pos);
        CancelCountDown();
//        CountDown();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void XulyDapAnDung(final View view){
        final Animation animation=new AlphaAnimation(1,0);
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        final MediaPlayer clapyourhand=MediaPlayer.create(InGameActivity.this,R.raw.vo_tay);
        final MediaPlayer chonsound=MediaPlayer.create(InGameActivity.this,R.raw.chon_sound);
        final MediaPlayer winround=MediaPlayer.create(InGameActivity.this,R.raw.win_round);
        new CountDownTimer(4000,1000){
            @Override
            public void onTick(long l) {
                view.startAnimation(animation);
                view.setBackgroundResource(R.drawable.custom_button_dapan_gandung);
                chonsound.start();
            }
            @Override
            public void onFinish() {
                new CountDownTimer(4000,1000){
                    @Override
                    public void onTick(long l) {
                        chonsound.stop();
                        animation.cancel();
                        winround.start();
                        clapyourhand.start();
                        view.setBackgroundResource(R.drawable.custom_button_dapan_dung);
                    }
                    @Override
                    public void onFinish() {
                        clapyourhand.stop();
                        view.setBackgroundResource(R.drawable.custom_button_dapan);
                        pos++;
                        if(pos>=lstQuestion.size()) pos = lstQuestion.size()-(lstQuestion.size()-1);
                        showQuestion(pos);
                        CancelCountDown();
                        CountDown();
                        mpa1.setVisibility(View.VISIBLE);
                        mpa2.setVisibility(View.VISIBLE);
                        mpa3.setVisibility(View.VISIBLE);
                        mpa4.setVisibility(View.VISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        FragmentBangDiem fragmentBangDiem= (FragmentBangDiem) getSupportFragmentManager().findFragmentById(R.id.fragmentDangDiem);
                        fragmentBangDiem.UpScore(pos);

                    }
                }.start();
            }
        }.start();
    }
    public void XuLyDapAnSai(final View view){
        final Animation animation=new AlphaAnimation(1,0);
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        new CountDownTimer(4000,1000){
            @Override
            public void onTick(long l) {
                view.startAnimation(animation);
                view.setBackgroundResource(R.drawable.custom_button_dapan_gandung);
            }
            @Override
            public void onFinish() {
                new CountDownTimer(4000,1000){
                    @Override
                    public void onTick(long l) {
                        animation.cancel();
                        view.setBackgroundResource(R.drawable.custom_button_dapan_gandung);
                        switch (lstQuestion.get(pos).DapAn){
                            case "1":mpa1.setBackgroundResource(R.drawable.custom_button_dapan_dung);
                                break;
                            case "2":mpa2.setBackgroundResource(R.drawable.custom_button_dapan_dung);
                                break;
                            case "3":mpa3.setBackgroundResource(R.drawable.custom_button_dapan_dung);
                                break;
                            case "4":mpa4.setBackgroundResource(R.drawable.custom_button_dapan_dung);
                                break;
                        }
                    }
                    @Override
                    public void onFinish() {
                        CancelCountDown();
                        DialogGameOver();
                    }
                }.start();
                view.setBackgroundResource(R.drawable.custom_button_dapan);
            }
        }.start();

    }





    //Xử lý quyền trợ giúp
    public void onClickBuyQues(View view) {
    }

    public void onClickReset(View view) {
        Random random=new Random();
        int i=random.nextInt(3)+1;
        pos=pos+i;
        if(pos>=lstQuestion.size()) pos = lstQuestion.size()-(lstQuestion.size()-1);
        showQuestion(pos);
        btn_reset.setImageResource(R.drawable.reset_icon_x);
        btn_reset.setEnabled(false);

    }
    public void onClickCall(View view){
        DialogCalling();
    }
    public void onClickPeople(View view){
        DialogPeople();
    }
    public void onClick50(View view){
//        ArrayList arrayListDA=new ArrayList();
//        arrayListDA.add("1");
//        arrayListDA.add("2");
//        arrayListDA.add("3");
//        arrayListDA.add("4");
//        String da=lstQuestion.get(pos).DapAn;
//        arrayListDA.remove(da);
//        int n;
//        Random random=new Random();
//        int k=random.nextInt(3);
//        arrayListDA.remove(k);
//        n=arrayListDA.size();
//        for(int i=0;i<n;i++){
//            if(arrayListDA.get(i)=="1"){
//                m_btn_da1.setVisibility(View.INVISIBLE);
//            }
//            if(arrayListDA.get(i)=="2"){
//                m_btn_da2.setVisibility(View.INVISIBLE);
//            }
//            if(arrayListDA.get(i)=="3"){
//                m_btn_da3.setVisibility(View.INVISIBLE);
//            }
//            if(arrayListDA.get(i)=="4"){
//                m_btn_da4.setVisibility(View.INVISIBLE);
//            }
//        }
//        btn_50.setImageResource(R.drawable.nam050_icon_x);
//        btn_50.setEnabled(false);

        LinkedHashMap<String, Button> list = new LinkedHashMap<>();
        list.put("1", mpa1);
        list.put("2", mpa2);
        list.put("3", mpa3);
        list.put("4", mpa4);

        list.remove(lstQuestion.get(pos).DapAn);
        list.remove(list.keySet().toArray()[new Random().nextInt(2)]);

        for(Map.Entry<String, Button> item : list.entrySet()){
            item.getValue().setVisibility(View.INVISIBLE);

        }
        btn_50.setImageResource(R.drawable.nam050_icon_x);
        btn_50.setEnabled(false);

    }
    public void DialogCalling() {
        final Dialog dialogCall=new Dialog(this);
        dialogCall.setContentView(R.layout.dialog_calling);
        Button imageButtonEndCall= dialogCall.findViewById(R.id.buttonEndCall);
        imageButtonEndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCall.dismiss();
            }
        });
        dialogCall.show();
    }
    public void DialogPeople (){
        final Dialog dialogPeople=new Dialog(this);
        dialogPeople.setContentView(R.layout.dialog_people);
        BarChart barChart= dialogPeople.findViewById(R.id.barChart);
        barChart.setDrawBarShadow(false);
        barChart.setTouchEnabled(false);
        barChart.setDrawValueAboveBar(false);
        barChart.animateXY(1,3000);
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
        People.setImageResource(R.drawable.people_icon_x);
        People.setEnabled(false);
    }





    //Xử lý đồng hồ đém ngược
    public void CountDown() {
        final Animation animTimerNhapnhay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhay);
        final Animation animTimerNhapnhaycucmanh= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhaycucmanh);
        final Animation animTimerXoay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_xoay);
        countDownTimer=new CountDownTimer(/*Integer.parseInt(timeCountDown.getText().toString())*1000*/120000,1000) {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTick(long l) {
                if(isPaused){
                    cancel();
                }else {
                    timeCountDown.setText(""+l/1000);
                    timeRemaining=l;
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
    public void PauseTimer(){
        isPaused=true;
    }
    public void ResumeTimer(){
        isPaused = false;
        final Animation animTimerNhapnhay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhay);
        final Animation animTimerNhapnhaycucmanh= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhaycucmanh);
        final Animation animTimerXoay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_xoay);
        countDownTimer=new CountDownTimer(timeRemaining,1000) {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTick(long l) {
                if(isPaused){
                    cancel();
                }else {
                    timeCountDown.setText(""+l/1000);
                    timeRemaining=l;
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
    public void CancelCountDown(){
        if(countDownTimer!=null)
            countDownTimer.cancel();
    }





    //Xử Lý Hiệu ứng hình ảnh, Nhạc nhẽo
    public void nhacNen(){
        MediaPlayer mediaPlayerNen=MediaPlayer.create(InGameActivity.this,R.raw.soruekranigenel);
        mediaPlayerNen.start();
        mediaPlayerNen.setLooping(true);
    }
    public void LightStick (){
        ImageView imageViewLight;
        ImageView imageViewLight2;
        imageViewLight=findViewById(R.id.imageViewLight);
        imageViewLight2=findViewById(R.id.imageViewLight2);
        final Animation animationLight= AnimationUtils.loadAnimation(this, R.anim.anim_light);
        final Animation animationLight2= AnimationUtils.loadAnimation(this, R.anim.anim_light2);
        imageViewLight.startAnimation(animationLight);
        imageViewLight2.startAnimation(animationLight2);
    }
    public void LightCircle (){
        ImageView imageViewLight0;
        ImageView imageViewLight01;
        imageViewLight0=findViewById(R.id.imageViewLightCircle0);
        imageViewLight01=findViewById(R.id.imageViewLightCircle01);
        final Animation animationLight= AnimationUtils.loadAnimation(this, R.anim.anim_circle_light);
        final Animation animationLight2= AnimationUtils.loadAnimation(this, R.anim.anim_circle_light2);
        imageViewLight0.startAnimation(animationLight);
        imageViewLight01.startAnimation(animationLight2);
    }
    public void DropDollar(){
        ImageView imageViewDollar1=findViewById(R.id.imageViewDollar1);
        ImageView imageViewDollar2=findViewById(R.id.imageViewDollar2);
        ImageView imageViewDollar3=findViewById(R.id.imageViewDollar3);
        ImageView imageViewDollar4=findViewById(R.id.imageViewDollar4);
        ImageView imageViewDollar5=findViewById(R.id.imageViewDollar5);
        final Animation animationDollar=AnimationUtils.loadAnimation(this,R.anim.anim_drop_dollar2);
        final Animation animationDollar2=AnimationUtils.loadAnimation(this,R.anim.anim_drop_dollar);
        final Animation animationDollar3=AnimationUtils.loadAnimation(this,R.anim.anim_drop_dollar3);
        final Animation animationDollar4=AnimationUtils.loadAnimation(this,R.anim.anim_drop_dollar4);
        final Animation animationDollar5=AnimationUtils.loadAnimation(this,R.anim.anim_drop_dollar5);
        imageViewDollar1.startAnimation(animationDollar);
        imageViewDollar2.startAnimation(animationDollar2);
        imageViewDollar3.startAnimation(animationDollar3);
        imageViewDollar4.startAnimation(animationDollar4);
        imageViewDollar5.startAnimation(animationDollar5);

    }





}
