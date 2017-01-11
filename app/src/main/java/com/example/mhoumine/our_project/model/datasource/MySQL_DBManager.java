package com.example.mhoumine.our_project.model.datasource;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

import com.example.mhoumine.our_project.model.backend.IDSManager;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Gal on 02/01/2017.
 */

public class MySQL_DBManager implements IDSManager {
    private final String USERNAME = "galk";
    private final String WEB_URL = "http://"+ USERNAME + ".vlab.jct.ac.il/php/";
    private Gson gson;

    public MySQL_DBManager() {
        gson = new GsonBuilder().create();
    }

    @Override
    public boolean addBusiness(ContentValues be) {
        try {

            String results = PHPTools.POST(WEB_URL + "InsertAgency.php", be);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean addActivity(ContentValues ac) {
        try {
            String results = PHPTools.POST(WEB_URL + "InsertTrip.php", ac);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean addUser(ContentValues user) {
        try {
            String results = PHPTools.POST(WEB_URL + "InsertUser.php", user);
            if(results.equals("")){
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 5).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(5));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return true;
    }

    @Override
    public Cursor getBusinessList() {
        try {
            MatrixCursor matrix = new MatrixCursor(Contract.BusinessAdjust.COLS);
            String str = PHPTools.GET(WEB_URL + "GetAgencies.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("agencies");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);
                business b = gson.fromJson(jsonObject.toString(), business.class);
                matrix.addRow(new Object[]{
                        b.getId(),
                        b.getName(),
                        b.getCountry(),
                        b.getCity(),
                        b.getStreet(),
                        b.getPhoneNumber(),
                        b.getEmail(),
                        b.getLinkUrl()
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
            String str = PHPTools.GET(WEB_URL + "GetTrips.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("trips");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);
                activity ac = gson.fromJson(jsonObject.toString(), activity.class);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                GregorianCalendar g1 = new GregorianCalendar();
                Date date1 = sdf.parse(jsonObject.getString("startDate"));
                g1.setTime(date1);
                GregorianCalendar g2 = new GregorianCalendar();
                Date date2 = sdf.parse(jsonObject.getString("endDate"));
                g2.setTime(date2);

                ac.setStartDate(g1);
                ac.setEndDate(g2);
                matrix.addRow(new Object[]{
                        ac.getActivityInfo(),
                        ac.getCountry(),
                        (Long)ac.getStartDate().getTimeInMillis(),
                        (Long) ac.getEndDate().getTimeInMillis(),
                        ac.getCost(),
                        ac.getDescription(),
                        ac.getId()});
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
        try {
            MatrixCursor matrix = new MatrixCursor(Contract.UserAccountAdjust.COLS);
            String str = PHPTools.GET(WEB_URL + "GetUserAccounts.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("user_accounts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);
                userAccount user = gson.fromJson(jsonObject.toString(), userAccount.class);
                matrix.addRow(new Object[]{
                        user.getUsername(),
                        user.getPassword()
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
    public Cursor checkChanges() {
        return null;
    }
}
