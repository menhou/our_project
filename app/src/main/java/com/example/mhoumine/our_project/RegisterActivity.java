package com.example.mhoumine.our_project;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mhoumine.our_project.model.datasource.Contract;
import com.example.mhoumine.our_project.model.entities.userAccount;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register);
        Button registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.UsernameTextbox);
                EditText password = (EditText) findViewById(R.id.PasswordTextbox);
                new RegisterAsynctask().execute(new userAccount(username.getText().toString(), password.getText().toString()));
            }
        });
    }

    private class RegisterAsynctask extends AsyncTask<userAccount, Void, Uri>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Uri uri) {
            int length = uri.toString().length();
            String s = uri.toString();
            if (s.charAt(length-1) == '1'){
                Toast.makeText(getApplicationContext(),"Account has been created", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"The account already exists", Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Uri doInBackground(userAccount... params) {
            ContentValues content = Contract.UserAccountAdjust.createContentValues(params[0].getUsername(), params[0].getPassword() );
            Uri uri = getContentResolver().insert(Contract.UserAccountAdjust.CONTENT_URI, content);
            return uri;
        }
    }


}
