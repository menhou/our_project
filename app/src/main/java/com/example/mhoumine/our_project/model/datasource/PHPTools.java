package com.example.mhoumine.our_project.model.datasource;

import android.content.ContentValues;
import android.net.http.HttpResponseCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gal on 02/01/2017.
 */

public class PHPTools {
    public static String POST(String url, ContentValues params) throws IOException{
        StringBuilder postData = new StringBuilder();
        for (String param : params.keySet()){
            if(postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param, "UTF-8"));
            postData.append("=");
            postData.append(URLEncoder.encode(String.valueOf(params.get(param)), "UTF-8"));
        }
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();

        int responeCode = con.getResponseCode();
        System.out.println("POST response code: " + responeCode);

        if(responeCode == HttpsURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();         return response.toString();
        }
        else{
            return "";
        }
    }

    public static String GET(String url) throws Exception{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "";
        }
    }
}
