package com.example.mhoumine.our_project.model.SharedPreference;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mhoumine.our_project.LoginActivity;

/**
 * Created by mhoumine on 11/12/2016.
 */
//
//public class SaveSharedPreference extends SharedPreferences{
//
//}

public class SaveSharedPreference{
    private String storedUsername;
    private String storedPass;
    private boolean isChecked;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public SaveSharedPreference(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        storedUsername = prefs.getString("Username",null);
        storedPass = prefs.getString("Password", null);
        isChecked = prefs.getBoolean("isChecked", false);
    }

    public void addUsernameAndPass(String user, String pass, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean("isChecked", true);
        editor.putString("Username", user);
        editor.putString("Password", pass);
        isChecked = true;
        storedUsername = user;
        storedPass = pass;
        editor.commit();
    }

    public String getStoredUsername()throws  Exception {
        if (isChecked && storedUsername != null) {
            return storedUsername;
        }
        throw new Exception("Username doesn't exist");
    }

    public void setStoredUsername(String storedUsername) {
        this.storedUsername = storedUsername;
    }

    public String getStoredPass() throws Exception{
        if (isChecked && storedPass != null) {
            return storedPass;
        }
        throw new Exception("Password doesn't exist");
    }

    public void setStoredPass(String storedPass) {
        this.storedPass = storedPass;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
