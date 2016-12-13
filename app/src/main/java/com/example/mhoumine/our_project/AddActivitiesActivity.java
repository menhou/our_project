package com.example.mhoumine.our_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.mhoumine.our_project.model.entities.activityType;

import java.util.ArrayList;

public class AddActivitiesActivity extends AppCompatActivity implements OnItemSelectedListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);
        setTitle("Add Activity");
        Spinner spinner = (Spinner) findViewById(R.id.typeActivitySpinner);
        spinner.setPrompt("Choose Activity Type");
        spinner.setOnItemSelectedListener(this);
        ArrayList<String> items = new ArrayList<String>();
        items.add(activityType.ENTERTAINMENT_SHOW.toString());
        items.add(activityType.FLIGHT.toString());
        items.add(activityType.TRIP.toString());
        items.add(activityType.HOTEL_RESORT.toString());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
