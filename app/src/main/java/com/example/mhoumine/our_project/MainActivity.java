package com.example.mhoumine.our_project;
import com.example.mhoumine.our_project.model.entities.business;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.content.Context;

import com.example.mhoumine.our_project.model.datasource.contentProvide;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ContentValues content = new ContentValues();
        content.put("activityInfo",activityType.FLIGHT.toString() );
        content.put("country","Israel");
        content.put("startDate",new Date().toString());
        content.put("endDate",new Date().toString());
        double num = 13000;
        content.put("cost", Double.toString(num));
        content.put("description","blabla");
        content.put("id","35462");
//       getApplicationContext().getContentResolver().insert(Uri.parse("content://com.example.mhoumine.our_project.travelagencies/activities"), content);
//        Cursor mCursor = getApplicationContext().getContentResolver().query(Uri.parse("content://com.example.mhoumine.our_project.travelagencies/activities"),null, null, null, null);
//        ArrayList<business> list = new ArrayList<business>();
//        for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
//            list.add(new business(mCursor.getString(mCursor.getColumnIndex("country")), )));
//        }
        setContentView(R.layout.activity_main);
    }
}
