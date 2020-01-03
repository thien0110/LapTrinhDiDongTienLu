package com.example.doanandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanandroid.adapter.BXHAdapter;
import com.example.doanandroid.adapter.GridViewAdapter;
import com.example.doanandroid.adapter.LichSuAdapter;
import com.example.doanandroid.model.BangXepHang;
import com.example.doanandroid.model.LichSu;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    Button logOn,lichsu,bxh,credit;
    ImageView imageViewLight;
    ImageView imageViewLight2;
    ArrayList<LichSu> arrayList=new ArrayList();
    ArrayList<BangXepHang> arrayList2=new ArrayList();
    ArrayList<String> arrayListCre=new ArrayList();
    TextView ten;
    CircleImageView circleImageView;
    Uri uri=null;
    final int REQUEST_CODE_EXAMPLE = 0x9345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logOn=(Button)findViewById(R.id.buttonVaoGame);
        imageViewLight=findViewById(R.id.imageViewLight);
        imageViewLight2=findViewById(R.id.imageViewLight2);
        lichsu=findViewById(R.id.buttonLichSuChoi);
        bxh=findViewById(R.id.buttonBangXepHang);
        credit=findViewById(R.id.buttonMuaCredit);
        ten=findViewById(R.id.textViewTenDoan);
        circleImageView=findViewById(R.id.imageViewAvatar);

        String taikhoanintent=getIntent().getStringExtra("tendn");
        ten.setText(taikhoanintent);




        final Animation animationLight= AnimationUtils.loadAnimation(this, R.anim.anim_light);
        final Animation animationLight2= AnimationUtils.loadAnimation(this, R.anim.anim_light2);

        imageViewLight.startAnimation(animationLight);
        imageViewLight2.startAnimation(animationLight2);
        lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLichSu();
            }
        });
        bxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBXH();
            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogCredit();
            }
        });



    }

    private void DialogCredit() {
        final Dialog dialogCredit=new Dialog(this);
        dialogCredit.setContentView(R.layout.dialog_muacredit);
        GridView gridView =dialogCredit.findViewById(R.id.gridView);
        final String[] tiem={"5000đ","10000đ","20000đ","50000đ"};
        int[]hinh={
                R.drawable.dimond_200,
                R.drawable.dimond_500,
                R.drawable.dimond_800,
                R.drawable.dimond_1000
        };
        GridViewAdapter gridViewAdapter= new GridViewAdapter(this, tiem, hinh);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"Bạn đã mất "+tiem[i],Toast.LENGTH_SHORT).show();
            }
        });


        dialogCredit.show();
    }

    private void DialogBXH() {
        final Dialog dialogBXH=new Dialog(this);
        dialogBXH.setContentView(R.layout.dialog_bangxephang);
        Button buttonexit=dialogBXH.findViewById(R.id.button_thank);
        RecyclerView recyclerView=dialogBXH.findViewById(R.id.recyclerView);
        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList2.clear();
                dialogBXH.dismiss();
            }
        });


        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        arrayList2.add(new BangXepHang("Thien","30000$","1"));
        arrayList2.add(new BangXepHang("thinh","20000$","2"));
        arrayList2.add(new BangXepHang("Đạt","10000$","3"));

        BXHAdapter bxhAdapter =new BXHAdapter(arrayList2,getApplicationContext());
        recyclerView.setAdapter(bxhAdapter);

        dialogBXH.show();
    }

    public void logOnClick(View view){
       Intent intent=new Intent(MainActivity.this,LinhVucActivity.class);
       startActivity(intent);

   }
   public void onClickQLTK(View view){

       String emailintent=getIntent().getStringExtra("email");
       String  taikhoanintent=getIntent().getStringExtra("tendn");
       Intent intent=new Intent(MainActivity.this,QLTaiKhoanActivity.class);
       intent.putExtra("tk",taikhoanintent);
       intent.putExtra("em",emailintent);
       startActivity(intent);
   }
   public void DialogLichSu(){
       final Dialog dialogLichSu=new Dialog(this);
       dialogLichSu.setContentView(R.layout.dialog_lichsu);
       Button buttonexit=dialogLichSu.findViewById(R.id.button_thank);
       RecyclerView recyclerView=dialogLichSu.findViewById(R.id.recyclerView);
       buttonexit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               arrayList.clear();
               dialogLichSu.dismiss();
           }
       });


        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
       recyclerView.setLayoutManager(layoutManager);
       arrayList.add(new LichSu("10000$","10"));
       arrayList.add(new LichSu("20000$","20"));
       arrayList.add(new LichSu("20000$","20"));




       LichSuAdapter lichSuAdapter=new LichSuAdapter(arrayList,getApplicationContext());
       recyclerView.setAdapter(lichSuAdapter);

       dialogLichSu.show();
   }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_CODE_EXAMPLE) {
//
//            // resultCode được set bởi DetailActivity
//            // RESULT_OK chỉ ra rằng kết quả này đã thành công
//            if(resultCode == AppCompatActivity.RESULT_OK) {
//                // Nhận dữ liệu từ Intent trả về
//                final String result = data.getStringExtra(QLTaiKhoanActivity.EXTRA_DATA);
//
//                // Sử dụng kết quả result bằng cách hiện Toast
//                uri= Uri.parse(getIntent().getStringExtra("anh"));
//                if(uri==null){
//                    circleImageView.setImageResource(R.drawable.doanchibinh);
//                }else {
//                    circleImageView.setImageURI(uri);
//                }
//                Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
//            } else {
//                // DetailActivity không thành công, không có data trả về.
//            }
//        }
//    }
}
