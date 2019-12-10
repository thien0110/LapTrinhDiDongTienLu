package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class LinhVucActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);
        button =findViewById(R.id.buttonLinhVuc1);
    }
    public void ChonLinhVucOnClick(View view)throws ExecutionException, InterruptedException{
        Button btn = (Button)view;
        String lst=new APIGetting(this).execute(view.getId()==R.id.buttonLinhVuc1?"1":"2").get();
    }
}
