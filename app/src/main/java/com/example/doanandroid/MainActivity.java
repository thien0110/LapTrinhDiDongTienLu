package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button logOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logOn=(Button)findViewById(R.id.buttonVaoGame);
    }
   public void logOnClick(View view)throws ExecutionException, InterruptedException{
       Intent intent=new Intent(MainActivity.this,LinhVucActivity.class);
       startActivity(intent);

   }




}
