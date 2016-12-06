package com.example.mhoumine.our_project;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mhoumine.our_project.model.datasource.contentProvide;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        contentProvide content = new contentProvide();
//        activity activity1 = new activity(activityType.FLIGHT, "Israel", new Date(), new Date(), 13000, "blablabla", "26446");
//        ContentValues convertActivity =
//        content.insert("com.example.mhoumine.our_project.travelagencies/activities",activity1);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Clicking(v);
            }
        });
        public void Clicking (View v){
        try {
            final ContentValues content = new ContentValues();
            content.put("activityInfo",activityType.FLIGHT.toString() );
            content.put("country","Israel");
            content.put("startDate",new Date().toString());
            content.put("endDate",new Date().toString());
            double num = 13000;
            content.put("cost", Double.toString(num));
            content.put("description","blabla");
            content.put("id","35462");
        }
        catch(Exception e){

        }
        }
         setContentView(R.layout.activity_main);
    }
}
