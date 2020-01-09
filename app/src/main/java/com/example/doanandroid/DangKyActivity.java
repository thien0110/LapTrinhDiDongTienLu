package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanandroid.model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiParameters;
import ml.huytools.lib.API.ApiProvider;

public class DangKyActivity extends AppCompatActivity {

    EditText txt_taikhoan,txt_mk,txt_email,txt_mkcf;
    String tk,mk,mkcf,emal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        LightStick();
        txt_taikhoan=findViewById(R.id.editTextTaiKhoan);
        txt_mk=findViewById(R.id.editTextMatKhau);
        txt_email=findViewById(R.id.editTextEmail);
        txt_mkcf=findViewById(R.id.editTextXacNhanMatKhau);
//        readJSONOBJ.execute("http://192.168.202.2:8000/api/NguoiChoiJson");

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
    public void onClickDangKi(View view){
        tk=txt_taikhoan.getText().toString().trim();
        mk=txt_mk.getText().toString().trim();
        mkcf=txt_mkcf.getText().toString().trim();
        emal=txt_email.getText().toString().trim();
        String hash = BCrypt.hashpw(mk, BCrypt.gensalt());
        if(tk.isEmpty()||mk.isEmpty()||mkcf.isEmpty()||emal.isEmpty()){
            Toast.makeText(DangKyActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else{
            if(mk.equals(mkcf)){
                ApiParameters apiParameters= new ApiParameters();
                apiParameters.add("tendangnhap",tk);
                apiParameters.add("matkhau",hash);
                apiParameters.add("email",emal);
                apiParameters.add("hinhdaidien","null");
                ApiProvider.Async.POST("http://192.168.202.2:8000/api/ThemNguoiChoi").SetParams(apiParameters).Then(new ApiProvider.Async.Callback() {
                    @Override
                    public void OnAPIResult(ApiOutput output, int requestCode) {
                        if(output.Status==false){
                            Toast.makeText(DangKyActivity.this,"Tài Khoản Đã Tồn Tại" , Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DangKyActivity.this, "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(DangKyActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }else {
                Toast.makeText(DangKyActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }

        }

    }




}
