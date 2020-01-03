package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class QLTaiKhoanActivity extends AppCompatActivity {
    EditText txt_tk, txt_email, txt_mk, txt_mkcf;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltai_khoan);
        txt_tk=findViewById(R.id.editTextTaiKhoan);
        txt_mk=findViewById(R.id.editTextMatKhau);
        txt_mkcf=findViewById(R.id.editTextXacNhanMatKhau);
        txt_email=findViewById(R.id.editTextEmail);
        update=findViewById(R.id.buttonCapNhat);
        txt_tk.setText(getIntent().getStringExtra("tk"));
        txt_email.setText(getIntent().getStringExtra("em"));
        LightStick();
        DoiMK();
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
    public void DoiMK(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QLTaiKhoanActivity.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
