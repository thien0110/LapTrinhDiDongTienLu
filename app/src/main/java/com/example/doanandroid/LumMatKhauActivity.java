package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LumMatKhauActivity extends AppCompatActivity {
    Button laymk;
    EditText txt_tk,txt_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lum_mat_khau);
        laymk=findViewById(R.id.buttonLumMatKhau);
        txt_tk=findViewById(R.id.editTextTaiKhoan);
        txt_email=findViewById(R.id.editTextEmail);
        QuenMK();
        LightStick();
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
    public void QuenMK(){
        laymk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_tk.getText().length()!=0 && txt_email.getText().length()!=0){
                        Toast.makeText(LumMatKhauActivity.this, "Chức năng đăng phát triển",Toast.LENGTH_SHORT).show();

                }else{
                    txt_tk.setHint("Bạn chưa nhập tài khoản");
                    txt_email.setHint("Bạn chưa nhập Email");

                }
            }
        });
    }


}
