package com.example.mhoumine.our_project;

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
import android.widget.Toast;

import com.example.mhoumine.our_project.model.SharedPreference.SaveSharedPreference;
import com.example.mhoumine.our_project.model.datasource.Contract;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    boolean isMatch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_login);

        final SaveSharedPreference shared = new SaveSharedPreference(getApplicationContext());
        final EditText usernameEditText = (EditText) findViewById(R.id.UsernameEditbox);
        final EditText passwordEditText = (EditText) findViewById(R.id.PasswordEdibox);
        boolean isCheckedBefore = shared.isChecked();
        try {
            if (isCheckedBefore) {
                usernameEditText.setText(shared.getStoredUsername());
                passwordEditText.setText(shared.getStoredPass());
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
                final CheckBox rememberCheckBox = (CheckBox) findViewById(R.id.rememberCheckBox);
                if (rememberCheckBox.isChecked() && isMatch == true){
                    shared.addUsernameAndPass(usernameEditText.getText().toString(), passwordEditText.getText().toString(), getApplicationContext());
                    isMatch = false;
                }
            }
        });
    }

    private class loginAsyncTask extends AsyncTask<userAccount, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(userAccount... params) {
            // need to call provider
            Cursor c = getContentResolver().query(Contract.UserAccountAdjust.CONTENT_URI, null, null, null, null);
            ArrayList<userAccount> list = new ArrayList<>();
            list = Contract.UserAccountAdjust.cursorToList(c);
            if (list.get(0).getUsername() == params[0].getUsername()){
                isMatch = true;
                Intent myIntent = new Intent(LoginActivity.this, OptionsActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
            else{
                Toast.makeText(getApplicationContext(),"The account doesn't exist!", Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }
}
