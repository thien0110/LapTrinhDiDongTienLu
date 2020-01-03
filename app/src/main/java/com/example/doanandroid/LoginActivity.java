package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanandroid.model.Player;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    EditText txt_user;
    EditText txt_pass;
    List<Player> lstPlayer=new ArrayList<>();
    ReadJSONOBJ readJSONOBJ = new ReadJSONOBJ();
    private GoogleApiClient mGoogleApiClient;
    int RC_SIGN_IN=001;
    private static final String TAG = "SignInActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_user=findViewById(R.id.editTextTaiKhoan);
        txt_pass=findViewById(R.id.editTextMatKhau);
        LightStick();
        readJSONOBJ.execute("http://192.168.202.2:8000/api/NguoiChoiJson");

        //Yêu cầu người dùng cung cấp thông tin
        GoogleSignInOptions gso=new  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //kết nối google API client
        mGoogleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        // Button Google Sig-in
        SignInButton signInButton=findViewById(R.id.btnSignIn);
        signInButton.setSize(signInButton.SIZE_STANDARD);

        findViewById(R.id.btnSignIn).setOnClickListener(this);


//


    }
    public void onClickDangKy(View view){
        Intent intent=new Intent(LoginActivity.this,DangKyActivity.class);
        startActivity(intent);
    }
    public void onClickLumMatKhau(View view){
        Intent intent=new Intent(LoginActivity.this,LumMatKhauActivity.class);
        startActivity(intent);
    }
    public void onClickDangNhap(View view) {
//        String hash = BCrypt.hashpw(mk, BCrypt.gensalt());
        boolean mk = false;
        String tk="";
        String em="";
        for (Player p:lstPlayer){
            if(p.mAcc.equals(txt_user.getText().toString())){
                tk=p.mAcc;
                em=p.mEmail;
            }
            boolean valuate = BCrypt.checkpw(txt_pass.getText().toString(),p.mPass);
            if(valuate){
                mk= true;
            }
        }
        if(txt_user.getText().length()!=0 && txt_pass.getText().length()!=0){

                if(txt_user.getText().toString().equals(tk)){
                    if(mk==true){
                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("tendn",tk);
                        intent.putExtra("email",em);
                   startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Sai Mật Khẩu",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Tài khoản không tồn tại",Toast.LENGTH_SHORT).show();
                }
        }else {
            txt_user.setHint("Bạn chưa nhập tài khoản");
            txt_pass.setHint("Bạn chưa nhập mật khẩu");
        }
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Failed",connectionResult+"");
    }
    //FUNCTION Dang Nhap
    private  void SigIn(){
        Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
        Log.d("KET NOI THANH CONG",mGoogleApiClient.isConnected()+"");

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            handleSignInResult(result);

        }
    }
    public void handleSignInResult(GoogleSignInResult result){
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if(result.isSuccess()){
            GoogleSignInAccount acct=result.getSignInAccount();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
           // txtName.setText(acct.getDisplayName());
           // Picasso.with(this).load(acct.getPhotoUrl()).into(imgHinh);
//            intent.putExtra("ten",acct.getDisplayName());
//            intent.putExtra("hinh",acct.getPhotoUrl());



        }
        else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                SigIn();
                break;

        }

    }


    private class ReadJSONOBJ extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
           return GetJSON.getAPI(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {


            try {
                lstPlayer=new ArrayList<>();
                JSONArray jsonArray=new JSONArray(s);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Player player=new Player();
                    player.mAcc=jsonObject.getString("ten_dang_nhap");
                    player.mPass =jsonObject.getString("mat_khau");
                    player.mEmail =jsonObject.getString("Email");
                    player.mAvatar =jsonObject.getString("hinh_dai_dien");
                    player.mHighScore =jsonObject.getString("diem_cao_nhat");
                    player.mCredit =jsonObject.getString("credit");
                    lstPlayer.add(player);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
//            Toast.makeText(LoginActivity.this, s,Toast.LENGTH_SHORT).show();
        }
    }

//
//    public void ReadJSON(final String url){
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                Toast.makeText(LoginActivity.this, response.toString(),Toast.LENGTH_SHORT).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
//
//            }
//        }
//        );
//        requestQueue.add(jsonArrayRequest);
//    }

}
