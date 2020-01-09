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
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.adapter.MoneyAdapter;
import com.example.doanandroid.model.Money;
import com.example.doanandroid.model.Player;
import com.example.doanandroid.model.QuestionOBJ;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;


@SuppressWarnings("deprecation")
public class InGameActivity extends AppCompatActivity {
    private boolean isPaused = false;
    private long timeRemaining = 0;
    int chon=0, ch=1;
    ArrayList<QuestionOBJ> lstQuestion;
    int pos=0, tien=0, luutien=0;
    Handler handler;
    ImageButton Call,People,btn_50,btn_reset;
    CountDownTimer countDownTimer=null;
    TextView m_NdCauhoi,timeCountDown,Diem,SoCau,credit;
    ImageView img_timer,tim1,tim2,tim3,tim4,tim5;
    Button mpa1,mpa2,mpa3,mpa4;
    ArrayList<Money> arrayList = new ArrayList<>();
    int demtym=0;
//    Player player1=new Player().

    MediaPlayer mediaPlayerNen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
//        credit.setText();
        Anhxa();
        showQuestion(pos);
        CountDown();
        nhacNen();
        LightStick();
        LightCircle();
        DropDollar();

    }

    //Hàm ánh xạ tất cả các View
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
        Diem=findViewById(R.id.textViewSoDiem);
        SoCau=findViewById(R.id.textViewSoCau);
        tim1=findViewById(R.id.tym1);
        tim2=findViewById(R.id.tym2);
        tim3=findViewById(R.id.tym3);
        tim4=findViewById(R.id.tym4);
        tim5=findViewById(R.id.tym5);
        mediaPlayerNen=MediaPlayer.create(InGameActivity.this,R.raw.soruekranigenel);
        credit=findViewById(R.id.textViewSoTiem);
    }
    //Lấy dữ liệu từ JSON
    private Boolean get_lstQuestion(String jSonString){
        try {
            lstQuestion=new ArrayList(); //Tạo mảng chứa các câu hỏi
            JSONArray jr = new JSONArray(jSonString);//Mảng câu hỏi trong JSON
            for(int i=0;i<jr.length();i++)
            {
                JSONObject jb = jr.getJSONObject(i);//Câu hỏi thứ i trong Mảng câu hỏi trong JSON
                QuestionOBJ quiz=new QuestionOBJ();
                quiz.NoiDung = jb.getString("noi_dung");// gán vào model
                quiz.DapAn1 = jb.getString("phuong_an_A");
                quiz.DapAn2 = jb.getString("phuong_an_B");
                quiz.DapAn3 = jb.getString("phuong_an_C");
                quiz.DapAn4 = jb.getString("phuong_an_D");
                quiz.DapAn = jb.getString("dap_an");
                quiz.Chon="0";
                lstQuestion.add(quiz);//thêm vào mảng
            }
            Collections.shuffle(lstQuestion);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }
    //Hiển thị câu hỏi vào View
    public void showQuestion(int pos){//Gán câu hỏi vào các View
        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("message");//dữ liệu câu hỏi ở trong đây
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

    //Sự kiện Tạm dừng game
    public void onClickPause(View view){
        final Dialog dialogPause=new Dialog(this);//Dialog đã được custom trong file xml
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
                stopnhacnen();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                stopnhacnen();
            }
        });
        PauseTimer();
        dialogPause.show();
    }


//Xử lý trả lời câu hỏi
    //Hiện dialog thắng cuộc (Không sài)
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

    //Hiện dialog Thua cuộc
    public void DialogGameOver(){
        //Khởi tạo dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InGameActivity.this);
        alertDialogBuilder //Set thuộc tính
                .setTitle("Game Over! ")//set tiêu đề
                .setMessage("Rất tiếc! Bạn phải ra về với số tiền thưởng là "+luutien+"$")//sét nội dung thông báo
                .setCancelable(false)//không cho diaglog có cancel
                .setPositiveButton("Tiếp ván mới", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//tạo button new game
                        stopnhacnen();
                        startActivity(new Intent(getApplicationContext(), LinhVucActivity.class));
                    }
                })
                .setNegativeButton("Không chơi nữa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//Tạo button exit
                        //System.exit(0); Bỏ
                        stopnhacnen();
                        finishAffinity();
                    }
                });
        alertDialogBuilder.show();//show dialog
    }
    public  void onClickTraLoi(View view) {
        switch (view.getId()){//Lấy id xem mình đã nhấn vào View nào
            case R.id.buttonDA1://Nếu nhấn vào View chứa button đáp án A
                if(lstQuestion.get(pos).DapAn.equals("1")){ //Nếu bằng Đáp án đúng trong csdl
                    XulyDapAnDung(mpa1);
                }
                else //ngược lại
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
        CancelCountDown(); //hủy đếm ngược
//        CountDown();
        //Không cho nhấn vào bất cứ đâu trên màn hình
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void XulyDapAnDung(final View view){
        //Khởi tạo các hiệu ứng âm thanh
        final Animation animSoCauXoay= AnimationUtils.loadAnimation(this, R.anim.anim_socau_xoay);
        final Animation animation=new AlphaAnimation(1,0);
        animation.setDuration(500);//số giây hiệu ứng xảy ra cho mỗi lần
        animation.setRepeatCount(Animation.INFINITE);//Cho nó luôn luôn lặp lại
        animation.setRepeatMode(Animation.REVERSE);//cho nó nháy đẹp hơn
        final MediaPlayer clapyourhand=MediaPlayer.create(InGameActivity.this,R.raw.vo_tay);
        final MediaPlayer chonsound=MediaPlayer.create(InGameActivity.this,R.raw.chon_sound);
        final MediaPlayer winround=MediaPlayer.create(InGameActivity.this,R.raw.win_round);
        new CountDownTimer(4000,1000){ //làm cho dừng các hoạt động 4s, chỉ làm những hoạt động được viết trong đây,
            // Chưa biết mình đúng, đang còn hồi hộp
            @Override
            public void onTick(long l) {
                view.startAnimation(animation);//bật hiệu ứng cho button(Nhấp nháy Nhấp nháy)
                view.setBackgroundResource(R.drawable.custom_button_dapan_gandung);//đổi màu đáp án cho phù hợp
                chonsound.start();//bật âm thanh cho phù hợp
            }
            @Override
            public void onFinish() {
                new CountDownTimer(4000,1000){//Tiếp tục xử lý đáp án đúng: biết là mình đúng :))
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
                        pos++;//để nhảy câu hỏi
                        ch++;//là số câu ở giữa màn hình
                        tien=tien+1000;//tiền thường tăng
                        luutien=tien;//lưu lại tiền
                        Diem.setText(tien+"$");
                        SoCau.setText(ch+"");

                        if(pos>=lstQuestion.size()) pos = lstQuestion.size()-(lstQuestion.size()-1);
                        showQuestion(pos);
                        CancelCountDown();
                        CountDown();
                        mpa1.setVisibility(View.VISIBLE);// nếu có bị dùng trợ giúp 50:50
                        mpa2.setVisibility(View.VISIBLE);//Thì hiển thị lại đáp án bị loại bỏ
                        mpa3.setVisibility(View.VISIBLE);
                        mpa4.setVisibility(View.VISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);//cho phép nhấn vào màn hình lại

                    }
                }.start();
            }
        }.start();
    }
    public void XuLyDapAnSai(final View view){//Như xử lý đáp án đúng
        final Animation animation=new AlphaAnimation(1,0);
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        final MediaPlayer chonsound=MediaPlayer.create(InGameActivity.this,R.raw.chon_sound);
        final MediaPlayer saisound=MediaPlayer.create(InGameActivity.this,R.raw.sai);
        new CountDownTimer(4000,1000){
            @Override
            public void onTick(long l) {
                view.startAnimation(animation);
                chonsound.start();
                view.setBackgroundResource(R.drawable.custom_button_dapan_gandung);
            }
            @Override
            public void onFinish() {
                new CountDownTimer(4000,1000){
                    @Override
                    public void onTick(long l) {
                        animation.cancel();
                        saisound.start();
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
                        demtym++;

                        switch (demtym){
                            case 1:tim5.setVisibility(View.INVISIBLE);
                                xlxlxl();//Xử lý chuyển câu hỏi mới và đủ thứ tại thằng case 5 nó khác
                            break;
                            case 2:tim4.setVisibility(View.INVISIBLE);
                                xlxlxl();
                                break;
                            case 3:tim3.setVisibility(View.INVISIBLE);
                                xlxlxl();
                                break;
                            case 4:tim2.setVisibility(View.INVISIBLE);
                                xlxlxl();
                                break;

                            case 5:CancelCountDown();
                                DialogGameOver();//Thua luôn hết mạng rồi
                                stopnhacnen();
                                break;
                        }

                    }
                }.start();
                view.setBackgroundResource(R.drawable.custom_button_dapan);
            }
        }.start();

    }

    public void xlxlxl(){//Xử lý chuyển câu hỏi mới và đủ thứ tại thằng case 5 nó khác
        pos++;
        ch++;
        SoCau.setText(ch+"");
        if(pos>=lstQuestion.size()) pos = lstQuestion.size()-(lstQuestion.size()-1);
        showQuestion(pos);
        CancelCountDown();
        CountDown();
        mpa1.setVisibility(View.VISIBLE);
        mpa2.setVisibility(View.VISIBLE);
        mpa3.setVisibility(View.VISIBLE);
        mpa4.setVisibility(View.VISIBLE);
        mpa4.setBackgroundResource(R.drawable.custom_button_dapan);
        mpa1.setBackgroundResource(R.drawable.custom_button_dapan);
        mpa2.setBackgroundResource(R.drawable.custom_button_dapan);
        mpa3.setBackgroundResource(R.drawable.custom_button_dapan);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }



    //Xử lý quyền trợ giúp
    public void onClickBuyQues(View view) {
    }
    public void onClickReset(View view) {//Đổi câu hỏi mới
//        Random random=new Random(); // Khỏi random luôm
//        int i=random.nextInt(3)+1;
        pos=pos+1;
        if(pos>=lstQuestion.size()) pos = lstQuestion.size()-(lstQuestion.size()-1);
        showQuestion(pos);
        btn_reset.setImageResource(R.drawable.reset_icon_x);//Đổi thành hình bị x bỏ
        btn_reset.setEnabled(false);//Cho khỏi nhấn được nữa

    }
    public void onClickCall(View view){
        DialogCalling();
        Call.setImageResource(R.drawable.phone_icon_x);
        Call.setEnabled(false);

    }
    public void onClickPeople(View view){//Trợ giúp hỏi ý  kiến khán giả
        DialogPeople();//Xem hàm dialog People
    }
    public void onClick50(View view){
        LinkedHashMap<String, Button> list = new LinkedHashMap<>();//Tạo cái mảng chứa 4 View đáp án
        list.put("1", mpa1);
        list.put("2", mpa2);
        list.put("3", mpa3);
        list.put("4", mpa4);

        list.remove(lstQuestion.get(pos).DapAn);//Xóa cái đáp án đúng khỏi mảng
        list.remove(list.keySet().toArray()[new Random().nextInt(2)]);//Xóa ngẫu nhiên thêm 1 thằng nữa

        for(Map.Entry<String, Button> item : list.entrySet()){
            item.getValue().setVisibility(View.INVISIBLE);

        }//Ẩn 2 thằng trong mảng đi, xong trợ giúp 50:50
        btn_50.setImageResource(R.drawable.nam050_icon_x);
        btn_50.setEnabled(false);

    }
    public void DialogCalling() {//Random Thằng đáp án đúng và thằng random 4 đáp án => đáp án đúng 62.5% xuất hiện
        final Dialog dialogCall=new Dialog(this);
        dialogCall.setContentView(R.layout.dialog_calling);
        Button imageButtonEndCall= dialogCall.findViewById(R.id.buttonEndCall);
        TextView da= dialogCall.findViewById(R.id.textViewDA);
        Random random=new Random();
        int so=random.nextInt(2);
        String rd = "";
        if(so==0){
            rd=RandomStringUtils.random(1,"ABCD");
        }else
        {
            switch (lstQuestion.get(pos).DapAn)
            {
                case "1":rd="A";break;
                case "2":rd="B";break;
                case "3":rd="C";break;
                case "4":rd="D";break;
            }
        }
        da.setText(rd);
        imageButtonEndCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCall.dismiss();
            }
        });
        dialogCall.show();
    }
    public void DialogPeople (){//Khởi tạo thư viện barchar
        final Dialog dialogPeople=new Dialog(this);

        dialogPeople.setContentView(R.layout.dialog_people);
        Button thanks=dialogPeople.findViewById(R.id.buttonThanks);
        thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPeople.dismiss();
            }
        });
        BarChart barChart= dialogPeople.findViewById(R.id.barChart);
        barChart.setDrawBarShadow(false);
        barChart.setTouchEnabled(false);
        barChart.setDrawValueAboveBar(false);
        barChart.animateXY(1,3000);
        ArrayList<BarEntry>barEntries=new ArrayList<>();
        Random random=new Random();
        //Random các % cột đáp án
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
        //Hiệu ứng
        final Animation animTimerNhapnhay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhay);
        final Animation animTimerNhapnhaycucmanh= AnimationUtils.loadAnimation(this, R.anim.anim_timer_nhapnhaycucmanh);
        final Animation animTimerXoay= AnimationUtils.loadAnimation(this, R.anim.anim_timer_xoay);
        countDownTimer=new CountDownTimer(30000,1000) {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onTick(long l) {
                if(isPaused){
                    cancel();//gán cho isPaused thì hủy đếm
                }else {
                    timeCountDown.setText(""+l/1000); //cho cái đồng hồ hiển thị số
                    timeRemaining=l;//Lưu lại thời gian để tạm dừng
                    timeCountDown.startAnimation(animTimerNhapnhay);
                    img_timer.startAnimation(animTimerXoay);
                    int a=Integer.parseInt((String) timeCountDown.getText());
                    if(a<6){//nếu đếm tới 5 thì chuyển thành màu đỏ cho nó vui
                        timeCountDown.startAnimation(animTimerNhapnhaycucmanh);
                        timeCountDown.setTextColor(Color.rgb(200,0,0));
                    }else {
                        timeCountDown.setTextColor(Color.rgb(255,255,255));
                    }
                }
            }
            @Override
            public void onFinish() {
                timeCountDown.setText("0");//Hết giờ thì gán cho nó bằng 0, tại hết giờ nó vẫn để 1
                //Toast.makeText(InGameActivity.this,"GAMEOVER",Toast.LENGTH_SHORT).show();
                demtym++;//Xử lý cái trái tym(mạng), Khi hết giờ thì vẫn chuyển sang câu mới và mất 1 mạng

                switch (demtym){
                    case 1:tim5.setVisibility(View.INVISIBLE);
                        xlxlxl();
                        break;
                    case 2:tim4.setVisibility(View.INVISIBLE);
                        xlxlxl();
                        break;
                    case 3:tim3.setVisibility(View.INVISIBLE);
                        xlxlxl();
                        break;
                    case 4:tim2.setVisibility(View.INVISIBLE);
                        xlxlxl();
                        break;

                    case 5:CancelCountDown();
                        DialogGameOver();
                        break;
                }
            }
        };
        countDownTimer.start();
    }
    public void PauseTimer(){
        isPaused=true;
    }//Dừng đếm ngược
    public void ResumeTimer(){//Tiếp tục đếm ngược
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
    //Trong đây toàn khỏi tạo hông à
    public void nhacNen(){
        mediaPlayerNen.start();
        mediaPlayerNen.setLooping(true);
    }
    public void stopnhacnen(){
        mediaPlayerNen.stop();
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
