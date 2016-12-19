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
    public SaveSharedPreference(){

    }
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public void addUsernameAndPass(String user, String pass, Context context)throws Exception{
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String storedUsername = prefs.getString("Username",null);
        String storedPass = prefs.getString("Password", null);
        if (storedUsername == null || storedPass == null){
            editor.putString("Username", user);
            editor.putString("Password", pass);
            editor.commit();
        }
        else {
            throw new Exception();
        }
    }

    public String[] getUsernameAndPass(Context context) throws Exception{
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String storedUsername = prefs.getString("Username",null);
        String storedPass = prefs.getString("Password", null);
        if(storedUsername == null || storedPass == null){
            throw new Exception("No Such Username Or Password in the shared");
        }
        else{
            return new String[] {storedUsername, storedPass};
        }
    }
}
