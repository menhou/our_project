package com.example.mhoumine.our_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button b1 = (Button) findViewById(R.id.AddActivityButton);
        Button b2 = (Button) findViewById(R.id.AddBusinessButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(OptionsActivity.this, AddActivitiesActivity.class);
                OptionsActivity.this.startActivity(myIntent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent myIntent = new Intent(OptionsActivity.this, AddBusinessDialog.class);
//                OptionsActivity.this.startActivity(myIntent);
            }
        });
    }




}
