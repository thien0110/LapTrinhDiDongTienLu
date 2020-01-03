package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class QLTaiKhoanActivity extends AppCompatActivity {
    EditText txt_tk, txt_email, txt_mk, txt_mkcf;
    CircleImageView circleImageView;
    Button update;
    Uri selectedImage;
    public static final String EXTRA_DATA = "EXTRA_DATA";


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
        circleImageView=findViewById(R.id.imageViewAvatar);
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
                Toast.makeText(QLTaiKhoanActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(QLTaiKhoanActivity.this,MainActivity.class);
//                intent.putExtra("anh",selectedImage.toString());
//                setResult(AppCompatActivity.RESULT_OK, intent);
//                finish();
                startActivity(intent);
            }
        });

    }
    public void DoiAvatar(View view){
        Toast.makeText(QLTaiKhoanActivity.this, "Chưa đổi được", Toast.LENGTH_SHORT).show();
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch(requestCode) {
//            case 0:
//                if(resultCode == RESULT_OK){
//                     selectedImage = imageReturnedIntent.getData();
//                    circleImageView.setImageURI(selectedImage);
//
//                }
//
//                break;
//            case 1:
//                if(resultCode == RESULT_OK){
//                     selectedImage = imageReturnedIntent.getData();
//                    circleImageView.setImageURI(selectedImage);
//                }
//                break;
//        }
//    }
}
