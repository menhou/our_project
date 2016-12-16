package com.example.mhoumine.our_project;
import android.content.ContentValues;
import android.database.Cursor;
import android.media.tv.TvContentRating;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mhoumine.our_project.model.datasource.Contract;
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
                CustomAsyncTask c= new CustomAsyncTask();
                c.execute();
            }
        });
    }

//    private Date convertStringToDate(String s){
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        try {
//            Date date = format.parse(s);
//            return date;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    private String convertDateToString(Date d){
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        return df.format(d);
//    }


    private class CustomAsyncTask extends AsyncTask<Void, Void, ArrayList<userAccount>> {
        public CustomAsyncTask() {
        }

        @Override
        protected ArrayList<userAccount> doInBackground(Void... params) {
            ContentValues content = Contract.UserAccountAdjust.createContentValues(45263,"gal","katz");
            Uri uri = getContentResolver().insert(Contract.UserAccountAdjust.CONTENT_URI, content);

            Cursor c = getContentResolver().query(Contract.UserAccountAdjust.CONTENT_URI, null, null, null, null);
            ArrayList<userAccount> list = new ArrayList<>();
            list = Contract.UserAccountAdjust.cursorToList(c);

            return list;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<userAccount> list) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(list.get(0).getUserId());
            String strI = sb.toString();
            Toast.makeText(getApplicationContext(),strI, Toast.LENGTH_LONG).show();
        }
    }

}

