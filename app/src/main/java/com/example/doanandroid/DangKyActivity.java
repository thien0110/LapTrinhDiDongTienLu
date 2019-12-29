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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanandroid.model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DangKyActivity extends AppCompatActivity {
    ArrayList<Player> lstPlayer=new ArrayList<>();

    EditText txt_taikhoan,txt_mk,txt_email;
   ReadJSONOBJ readJSONOBJ = new ReadJSONOBJ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        LightStick();
        txt_taikhoan=findViewById(R.id.editTextTaiKhoan);
        txt_mk=findViewById(R.id.editTextMatKhau);
        txt_email=findViewById(R.id.editTextEmail);
        readJSONOBJ.execute("http://192.168.202.2:8000/api/NguoiChoiJson");

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
        Player p=lstPlayer.get(0);
        Toast.makeText(DangKyActivity.this,p.mAcc,Toast.LENGTH_SHORT).show();

    }
    private class ReadJSONOBJ extends AsyncTask<String,Void,String> {

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
//            Toast.makeText(LoginActivity.this, s,Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }

    }




}
