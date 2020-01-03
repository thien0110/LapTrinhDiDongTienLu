package com.example.doanandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJSON {//cái này của KhoaPham
    static String getAPI(String string){
        StringBuilder content=new StringBuilder();
        try {
            URL url=new URL(string);
            InputStreamReader inputStreamReader=new InputStreamReader(url.openStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                content.append(line);

            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();

    }
}
