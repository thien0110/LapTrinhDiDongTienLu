package com.example.doanandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import dmax.dialog.SpotsDialog;

import static dmax.dialog.SpotsDialog.*;

public class APIGetting extends AsyncTask<String,String,String> {
    private Context m_con;
    public  APIGetting(Context con)
    {
        m_con =con;
    }


    @Override
    protected String doInBackground(String... strings) {
        return APIQuestion.getQuestions(strings[0]);
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(m_con, s, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(m_con, InGameActivity.class);
        intent.putExtra("message", s);
        Activity activity = (Activity) m_con;
        activity.startActivity(intent);

    }
}
