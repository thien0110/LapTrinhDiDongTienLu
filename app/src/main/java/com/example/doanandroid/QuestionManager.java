package com.example.doanandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class QuestionManager extends LinkedList<QuestionOBJ> {
        public static QuestionManager ParseJSON(String json){
            QuestionManager questionOBJS=new QuestionManager();
            try {
                JSONArray jsonArray=new JSONArray(json);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = (JSONObject) (jsonArray.get(i));
                        //questionOBJS.add(QuestionOBJ.ParseJSON(jsonObject));

                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return questionOBJS;

        }
}
