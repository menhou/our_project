package com.example.mhoumine.our_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.mhoumine.our_project.model.entities.activityType;

import java.util.ArrayList;
import java.util.Calendar;

public class AddActivitiesActivity extends AppCompatActivity implements OnItemSelectedListener {
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);
        setTitle("Add Activity");
        Spinner spinner = (Spinner) findViewById(R.id.typeActivitySpinner);
        final EditText editStart = (EditText) findViewById(R.id.StartDateEditText);
        final EditText editEnd = (EditText) findViewById(R.id.EndDateEditText);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

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
        editStart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editStart.getRight() - editStart.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        showDialog(DIALOG_ID);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return new DatePickerDialog(this,dpickListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month;
            day_x = dayOfMonth;
            final EditText editStart = (EditText) findViewById(R.id.StartDateEditText);
            editStart.setText(day_x + "/" + month_x + "/" + year_x);
        }
    };
}
