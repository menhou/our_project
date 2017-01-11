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

import com.example.mhoumine.our_project.model.datasource.Contract;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.Add);
        Button button = (Button) findViewById(R.id.loadButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new myAsynctask().execute();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new myAsyncTaskPost().execute();
            }
        });



    }

    private class myAsynctask extends AsyncTask<Void,Void,ArrayList<activity> >{
        @Override
        protected ArrayList<activity> doInBackground(Void... params) {
            Cursor c = getContentResolver().query(Contract.ActivityAdjust.CONTENT_URI,null, null, null, null);
            ArrayList<activity> list = Contract.ActivityAdjust.cursorToList(c);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<activity> activities) {
            for(activity act : activities){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String s = sdf.format(act.getStartDate().getTime());
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        }
    }

    private class myAsyncTaskPost extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            GregorianCalendar g1 = new GregorianCalendar(1990, 9, 11);
            GregorianCalendar g2 = new GregorianCalendar(1800, 5, 2);
            ContentValues contentValues = Contract.ActivityAdjust.createContentValues(activityType.FLIGHT,"Holand",g2, g1,10,"Very long", "2580" );
            Uri uri = getContentResolver().insert(Contract.ActivityAdjust.CONTENT_URI, contentValues);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
        }

    }
}

