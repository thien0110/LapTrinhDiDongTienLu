package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button logOn;
    ImageView imageViewLight;
    ImageView imageViewLight2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logOn=(Button)findViewById(R.id.buttonVaoGame);
        imageViewLight=findViewById(R.id.imageViewLight);
        imageViewLight2=findViewById(R.id.imageViewLight2);

        final Animation animationLight= AnimationUtils.loadAnimation(this, R.anim.anim_light);
        final Animation animationLight2= AnimationUtils.loadAnimation(this, R.anim.anim_light2);

        imageViewLight.startAnimation(animationLight);
        imageViewLight2.startAnimation(animationLight2);
    }
   public void logOnClick(View view){
       Intent intent=new Intent(MainActivity.this,LinhVucActivity.class);
       startActivity(intent);

   }
   public void onClickQLTK(View view){
       Intent intent=new Intent(MainActivity.this,QLTaiKhoanActivity.class);
       startActivity(intent);
   }




}
