package com.example.mhoumine.our_project;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.media.tv.TvContentRating;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.mhoumine.our_project.model.datasource.Contract;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddActivitiesActivity extends AppCompatActivity implements OnItemSelectedListener {
    int year_x,month_x,day_x;
    int year_y,month_y, day_y;
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

        year_y = cal.get(Calendar.YEAR);
        month_y = cal.get(Calendar.MONTH);
        day_y = cal.get(Calendar.DAY_OF_MONTH);

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
                        showDialog(1);
                        return true;
                    }
                }
                return false;
            }
        });

        editEnd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editEnd.getRight() - editEnd.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        showDialog(2);
                        return true;
                    }
                }
                return false;
            }
        });

        Button addActivty = (Button) findViewById(R.id.AddActivityButton);

        addActivty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) findViewById(R.id.typeActivitySpinner);
                activityType temp = activityType.getEnum(spinner.getSelectedItem().toString());

                EditText country = (EditText) findViewById(R.id.countryEditText);
                GregorianCalendar cal1 = new GregorianCalendar();
                GregorianCalendar cal2 = new GregorianCalendar();
                try {
                    EditText startDate = (EditText) findViewById(R.id.StartDateEditText);
                    DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date date1 = df1.parse(startDate.getText().toString());
                    cal1.setTime(date1);

                    EditText endDate = (EditText) findViewById(R.id.EndDateEditText);
                    DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date date2 = df2.parse(endDate.getText().toString());
                    cal2.setTime(date2);

                }
                catch(ParseException e){

                }
                EditText cost = (EditText) findViewById(R.id.CostEditText);
                EditText description = (EditText) findViewById(R.id.DescriptionEditText);


                new addAcvtivity().execute(new activity(temp, country.getText().toString(),cal1, cal2,Double.parseDouble(cost.getText().toString()),description.getText().toString(),"34"));
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
        if(id == 1){
            return new DatePickerDialog(this,dpickListener1, year_x, month_x, day_x);
        }
        if (id == 2){
            return new DatePickerDialog(this,dpickListener2, year_y, month_y, day_y);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickListener1 = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month;
            day_x = dayOfMonth;
            final EditText editStart = (EditText) findViewById(R.id.StartDateEditText);
            editStart.setText(day_x + "/" + month_x + "/" + year_x);
        }
    };

    private DatePickerDialog.OnDateSetListener dpickListener2 = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_y = year;
            month_y = month;
            day_y = dayOfMonth;
            final EditText editEnd = (EditText) findViewById(R.id.EndDateEditText);
            editEnd.setText(day_y + "/" + month_y + "/" + year_y);
        }
    };

    private class addAcvtivity extends AsyncTask<activity, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(activity... params) {

            ContentValues content = Contract.ActivityAdjust.createContentValues(params[0].getActivityInfo(),params[0].getCountry(), params[0].getStartDate(), params[0].getEndDate(),params[0].getCost(), params[0].getDescription(), params[0].getId() );
            Uri uri = getContentResolver().insert(Contract.ActivityAdjust.CONTENT_URI, content);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
