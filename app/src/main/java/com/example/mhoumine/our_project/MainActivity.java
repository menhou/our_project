package com.example.mhoumine.our_project;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;
import com.example.mhoumine.our_project.model.datasource.contentProvide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
    }

    private Date convertStringToDate(String s){
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date = format.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String convertDateToString(Date d){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(d);
    }

    private void click(View v){
        CustomAsyncTask c = new CustomAsyncTask();
        c.execute();
    }

    private class CustomAsyncTask extends AsyncTask<String, Integer, Integer> {

        final ContentValues content = new ContentValues();
        @Override
        protected Integer doInBackground(String... params) {
//            MainActivity.this.getContentResolver().insert(Uri.parse("content://com.example.mhoumine.our_project.travelagencies/activities"), content);
//            Cursor mCursor = MainActivity.this.getContentResolver().query(Uri.parse("content://com.example.mhoumine.our_project.travelagencies/activities"), null, null, null, null);
//            ArrayList<activity> list = new ArrayList<activity>();
//            for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
//                list.add(new activity(activityType.getEnum(mCursor.getString(mCursor.getColumnIndex("activityInfo"))),mCursor.getString(mCursor.getColumnIndex("Country")), convertStringToDate(mCursor.getString(mCursor.getColumnIndex("startDate"))),convertStringToDate(mCursor.getString(mCursor.getColumnIndex("endDate"))),mCursor.getDouble(mCursor.getColumnIndex("cost")),mCursor.getString(mCursor.getColumnIndex("description")), mCursor.getString(mCursor.getColumnIndex("id"))) );
//            }
//            return list.get(0).getId();
            getContentResolver().insert(Uri.parse("content://com.example.mhoumine.our_project.travelagencies/useraccounts"), content);
            Cursor mCursor = getContentResolver().query(Uri.parse("content://com.example.mhoumine.our_project.travelagencies/useraccounts"), null, null, null, null);
            ArrayList<userAccount> list = new ArrayList<userAccount>();
            for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
                list.add(new userAccount(mCursor.getInt(mCursor.getColumnIndex("userId")), mCursor.getString(mCursor.getColumnIndex("username")), mCursor.getString(mCursor.getColumnIndex("password"))));
            }
            return list.get(0).getUserId();
        }

        @Override
        protected void onPreExecute() {
//            content.put("activityInfo", activityType.FLIGHT.toString() );
//            content.put("country","Israel");
//            content.put("startDate",new Date().toString());
//            content.put("endDate",new Date().toString());
//            double num = 13000;
//            content.put("cost", Double.toString(num));
//            content.put("description","blabla");
//            content.put("id","35462");
            content.put("userId", 635472);
            content.put("username", "hsdhf");
            content.put("password", "hdgxdvxdv");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Toast.makeText(MainActivity.this,integer, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

}

