package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(tk.isEmpty()||mk.isEmpty()||mkcf.isEmpty()||emal.isEmpty()){
            Toast.makeText(DangKyActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else{

                ThemTaiKhoan("http://192.168.202.2:8000/api/ThemNguoiChoi");
                Toast.makeText(DangKyActivity.this, "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();

//            }else Toast.makeText(DangKyActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        }
//            String data="{"+txt_taikhoan.getText().toString()+"}";
//            Submit(data);

    }
    public void ThemTaiKhoan(String url){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),object.toString(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DangKyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("tendangnhap",txt_taikhoan.getText().toString().trim());
                params.put("matkhau",txt_mk.getText().toString().trim());
                params.put("matkhaucf",txt_mkcf.getText().toString().trim());
                params.put("email",txt_email.getText().toString().trim());

                return params;

            }
        };
        requestQueue.add(stringRequest);
    }
//    public void Submit(String data){
//        tk=txt_taikhoan.getText().toString();
//        mk=txt_mk.getText().toString();
//        mkcf=txt_mkcf.getText().toString();
//        emal=txt_email.getText().toString();
//        final String savedata=data;
//        String URL="http://192.168.202.2:8000/api/NguoiChoiJson";
//        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(),object.toString(),Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        }){
//            @Override
//            public String getBodyContentType() {
//                return "application/json: charset=utf-8";
//            }
//
//            @Override
//            public byte[] getBody() throws AuthFailureError {
//
//                try {
//                    return savedata==null?null:savedata.getBytes("utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    return null;
//                }
//            }
//
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String,String>params=new HashMap<String,String>();
////                params.put("tendangnhap",tk);
////                params.put("matkhau",mk);
////                params.put("email",emal);
////
////                return params;
////            }
//        };
//        requestQueue.add(stringRequest);
//    }
//    private class ReadJSONOBJ extends AsyncTask<String,Void,String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return GetJSON.getAPI(strings[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//
//
//            try {
//                lstPlayer=new ArrayList<>();
//                JSONArray jsonArray=new JSONArray(s);
//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject=jsonArray.getJSONObject(i);
//                    Player player=new Player();
//                    player.mAcc=jsonObject.getString("ten_dang_nhap");
//                    player.mPass =jsonObject.getString("mat_khau");
//                    player.mEmail =jsonObject.getString("Email");
//                    player.mAvatar =jsonObject.getString("hinh_dai_dien");
//                    player.mHighScore =jsonObject.getString("diem_cao_nhat");
//                    player.mCredit =jsonObject.getString("credit");
//                    lstPlayer.add(player);
//
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            Toast.makeText(LoginActivity.this, s,Toast.LENGTH_SHORT).show();
//            super.onPostExecute(s);
//        }
//
//    }




}
