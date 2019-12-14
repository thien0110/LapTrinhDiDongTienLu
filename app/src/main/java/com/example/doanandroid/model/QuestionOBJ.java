package com.example.doanandroid.model;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionOBJ {
        public String id;
        public String NoiDung;
        public String DapAn;
        public String DapAn1;
        public String DapAn2;
        public String DapAn3;
        public String DapAn4;
        public String Chon;
        public QuestionOBJ(){

        }

        /*public QuestionOBJ(String id, String noiDung, String dapAn, String dapAn1, String dapAn2, String dapAn3, String dapAn4, String chon) {
                this.id = id;
                NoiDung = noiDung;
                DapAn = dapAn;
                DapAn1 = dapAn1;
                DapAn2 = dapAn2;
                DapAn3 = dapAn3;
                DapAn4 = dapAn4;
                Chon = chon;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getNoiDung() {
                return NoiDung;
        }

        public void setNoiDung(String noiDung) {
                NoiDung = noiDung;
        }

        public String getDapAn() {
                return DapAn;
        }

        public void setDapAn(String dapAn) {
                DapAn = dapAn;
        }

        public String getDapAn1() {
                return DapAn1;
        }

        public void setDapAn1(String dapAn1) {
                DapAn1 = dapAn1;
        }

        public String getDapAn2() {
                return DapAn2;
        }

        public void setDapAn2(String dapAn2) {
                DapAn2 = dapAn2;
        }

        public String getDapAn3() {
                return DapAn3;
        }

        public void setDapAn3(String dapAn3) {
                DapAn3 = dapAn3;
        }

        public String getDapAn4() {
                return DapAn4;
        }

        public void setDapAn4(String dapAn4) {
                DapAn4 = dapAn4;
        }

        public String getChon() {
                return Chon;
        }

        public void setChon(String chon) {
                Chon = chon;
        }

        public static QuestionOBJ ParseJSON(JSONObject object) throws JSONException{
            QuestionOBJ questionOBJ=new QuestionOBJ();
            questionOBJ.id=object.getString("id");
            questionOBJ.NoiDung=object.getString("noi_dung");
            questionOBJ.DapAn=object.getString("dap_an");
            questionOBJ.DapAn1=object.getString("phuong_an_A");
            questionOBJ.DapAn2=object.getString("phuong_an_B");
            questionOBJ.DapAn3=object.getString("phuong_an_C");
            questionOBJ.DapAn4=object.getString("phuong_an_D");
            return questionOBJ;
        }*/
}
