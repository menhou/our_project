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
import java.util.GregorianCalendar;
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


    private class CustomAsyncTask extends AsyncTask<Void, Void, ArrayList<business>> {
        public CustomAsyncTask() {
        }

        @Override
        protected ArrayList<business> doInBackground(Void... params) {
            GregorianCalendar date1 = new GregorianCalendar(1900, 11, 27);
            GregorianCalendar date2 = new GregorianCalendar(1990, 6, 15);

            ContentValues content = Contract.BusinessAdjust.createContentValues("34234","busi","Israel", "Tel Aviv", "hfsd","324234", "zdfu@gmail.com", "google.co.il");
            Uri uri = getContentResolver().insert(Contract.BusinessAdjust.CONTENT_URI, content);

            Cursor c = getContentResolver().query(Contract.BusinessAdjust.CONTENT_URI, null, null, null, null);
            ArrayList<business> list = new ArrayList<>();
            list = Contract.BusinessAdjust.cursorToList(c);

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
        protected void onPostExecute(ArrayList<business> list) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("");
//            sb.append(list.get(0).getStartDate());
//            String strI = sb.toString();
//            GregorianCalendar d = list.get(0).getStartDate();
//            SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

            Toast.makeText(getApplicationContext(),list.get(0).getEmail(), Toast.LENGTH_LONG).show();
        }
    }

}

