package com.example.mhoumine.our_project.model.datasource;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.mhoumine.our_project.model.backend.IDSManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;

/**
 * Created by Gal on 02/01/2017.
 */

public class MySQL_DBManager implements IDSManager {
    private final String USERNAME = "galk";
    private final String WEB_URL = "http://"+ USERNAME + ".vlab.jct.ac.il/php/";
    @Override
    public boolean addBusiness(ContentValues be) {
//        try{
//            String result = PHPTools.POST(WEB_URL + "/InsertTrip.php", be);
//            String id = result.toString();
//            if(id != ""){
//                SetUpdate();
//            }
//        }
//        catch(IOException e){
//
//        }
    }

    @Override
    public boolean addActivity(ContentValues act) {
        return false;
    }

    @Override
    public boolean addUser(ContentValues user) {
        return false;
    }

    @Override
    public Cursor getBusinessList() {
        try {
            MatrixCursor matrix = new MatrixCursor(Contract.BusinessAdjust.COLS);
            String str = PHPTools.GET(WEB_URL + "/GetAgencies.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("agencies");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);
                matrix.addRow(new Object[]{
                        jsonObject.getString(Contract.BusinessAdjust.ID_COL),
                        jsonObject.getString(Contract.BusinessAdjust.NAME_COL),
                        jsonObject.getString(Contract.BusinessAdjust.COUNTRY_COL),
                        jsonObject.getString(Contract.BusinessAdjust.CITY_COL),
                        jsonObject.getString(Contract.BusinessAdjust.STREET_COL),
                        jsonObject.getString(Contract.BusinessAdjust.PHONE_NUMBER_COL),
                        jsonObject.getString(Contract.BusinessAdjust.EMAIL_COL),
                        jsonObject.getString(Contract.BusinessAdjust.LINK_URL_COL)
                });
            }
            return matrix;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cursor getActivityList() {
        try {
            MatrixCursor matrix = new MatrixCursor(Contract.ActivityAdjust.COLS);
            String str = PHPTools.GET(WEB_URL + "/GetTrip.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("trips");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);
                Gson gson = new Gson(); // finish with gson
                matrix.addRow(new Object[]{
                        jsonObject.getString(Contract.ActivityAdjust.TYPE_COL),
                        jsonObject.getString(Contract.ActivityAdjust.COUNTRY_COL),
                        jsonObject.getString(Contract.ActivityAdjust.START_DATE_COL),
                        jsonObject.getString(Contract.ActivityAdjust.END_DATE_COL),
                        jsonObject.getDouble(Contract.ActivityAdjust.COST_COL),
                        jsonObject.getString(Contract.ActivityAdjust.DESCRIPTION_COL),
                        jsonObject.getString(Contract.ActivityAdjust.ID_COL)
                });
            }
            return matrix;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cursor getUserList() {
        return null;
    }

    @Override
    public Cursor checkChanges() {
        return null;
    }
}
