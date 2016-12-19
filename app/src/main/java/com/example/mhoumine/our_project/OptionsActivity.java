package com.example.mhoumine.our_project;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.mhoumine.our_project.model.datasource.Contract;
import com.example.mhoumine.our_project.model.entities.business;

public class OptionsActivity extends AppCompatActivity{
    final Context context = this;
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
                final Dialog AddBusinessDialog = new Dialog(context);
                AddBusinessDialog.setContentView(R.layout.custom_dialog);
                AddBusinessDialog.setTitle("Add Business");

                Button dialogButton = (Button) AddBusinessDialog.findViewById(R.id.AddBusinessButton);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    EditText idEditText = (EditText) AddBusinessDialog.findViewById(R.id.BusinessIDEditText);
                    EditText nameEditText = (EditText) AddBusinessDialog.findViewById(R.id.NameEditText);
                    EditText countryEditText = (EditText) AddBusinessDialog.findViewById(R.id.CountryEditText);
                    EditText cityEditText = (EditText) AddBusinessDialog.findViewById(R.id.CityEditText);
                    EditText streetEditText = (EditText) AddBusinessDialog.findViewById(R.id.StreetEditText);
                    EditText phoneNumEditText = (EditText) AddBusinessDialog.findViewById(R.id.PhoneNumEditText);
                    EditText emailEditText = (EditText) AddBusinessDialog.findViewById(R.id.EmailEditText);
                    EditText linkEditText = (EditText) AddBusinessDialog.findViewById(R.id.LinkEditText);
                    @Override
                    public void onClick(View v) {
                        new AddBusinessAsyncTask().execute(new business(idEditText.getText().toString(),
                                nameEditText.getText().toString(),
                                countryEditText.getText().toString(),
                                cityEditText.getText().toString(),
                                streetEditText.getText().toString(),
                                phoneNumEditText.getText().toString(),
                                emailEditText.getText().toString(),
                                linkEditText.getText().toString()));
                    }
                });
                Window window = AddBusinessDialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                AddBusinessDialog.show();

            }
        });


    }


    private class AddBusinessAsyncTask extends AsyncTask<business,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(business... params) {
            ContentValues content = Contract.BusinessAdjust.createContentValues(params[0].getId(), params[0].getName(), params[0].getCountry(), params[0].getCity(), params[0].getStreet(), params[0].getPhoneNumber(), params[0].getEmail(), params[0].getLinkUrl());
            Uri uri = getContentResolver().insert(Contract.BusinessAdjust.CONTENT_URI, content);
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }



}
