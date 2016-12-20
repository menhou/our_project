package com.example.mhoumine.our_project;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mhoumine.our_project.model.SharedPreference.SaveSharedPreference;
import com.example.mhoumine.our_project.model.datasource.Contract;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    boolean inShared = false;
    public SaveSharedPreference shared;
    public EditText usernameEditText;
    public EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_login);

        this.shared = new SaveSharedPreference(getApplicationContext());
        this.usernameEditText = (EditText) findViewById(R.id.UsernameEditbox);
        this.passwordEditText = (EditText) findViewById(R.id.PasswordEditbox);
        CheckBox ch = (CheckBox) findViewById(R.id.rememberCheckBox);
        TextView registerTextView = (TextView) findViewById(R.id.RegisterTextView);

        boolean isCheckedBefore = shared.isChecked();
        try {
            if (isCheckedBefore) {
                usernameEditText.setText(shared.getStoredUsername());
                passwordEditText.setText(shared.getStoredPass());
                ch.setChecked(true);
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAccount user = new userAccount(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                new loginAsyncTask().execute(user);
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }

    private class loginAsyncTask extends AsyncTask<userAccount, Void, Boolean>{
        @Override
        protected void onPreExecute() {
            if (shared.isChecked()){
                try {
                    String username = shared.getStoredUsername();
                    String pass = shared.getStoredPass();
                    if(usernameEditText.getText().toString().equals(username) &&
                            passwordEditText.getText().toString().equals(pass)){
                        inShared = true;
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }



        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(userAccount... params) {
            if (inShared){
                return true;
            }
            Cursor c = getContentResolver().query(Contract.UserAccountAdjust.CONTENT_URI, null, null, null, null);
            ArrayList<userAccount> list = Contract.UserAccountAdjust.cursorToList(c);
            for (userAccount user: list){
                if (user.getUsername().equals(params[0].getUsername()) && user.getPassword().equals(params[0].getPassword())){
                    return true;
                }
            }
            return false;


        }

        @Override
        protected void onPostExecute(Boolean isMatch) {
            if (inShared){
                Intent myIntent = new Intent(LoginActivity.this, OptionsActivity.class);
                inShared = false;
                LoginActivity.this.startActivity(myIntent);
            }
            else {
                CheckBox rememberCheckBox = (CheckBox) findViewById(R.id.rememberCheckBox);
                if (rememberCheckBox.isChecked() && isMatch) {
                    shared.addUsernameAndPass(usernameEditText.getText().toString(), passwordEditText.getText().toString(), getApplicationContext());
                    Intent myIntent = new Intent(LoginActivity.this, OptionsActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                } else if (isMatch) {
                    Intent myIntent = new Intent(LoginActivity.this, OptionsActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                } else if (!isMatch && inShared) {
                    Intent myIntent = new Intent(LoginActivity.this, OptionsActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                    inShared = false;
                } else {
                    Toast.makeText(getApplicationContext(), "Account Doesn't Exist", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
