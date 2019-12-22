package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LightStick();
        DropDollar();
    }
    public void onClickDangKy(View view){
        Intent intent=new Intent(LoginActivity.this,DangKyActivity.class);
        startActivity(intent);
    }
    public void onClickLumMatKhau(View view){
        Intent intent=new Intent(LoginActivity.this,LumMatKhauActivity.class);
        startActivity(intent);
    }
    public void onClickDangNhap(View view){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
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
