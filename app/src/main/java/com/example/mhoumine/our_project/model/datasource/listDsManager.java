package com.example.mhoumine.our_project.model.datasource;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.MatrixCursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.mhoumine.our_project.model.backend.IDSManager;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

/**
 * Created by mhoumine on 27/11/2016.
 */

public class listDsManager implements IDSManager {
    private ArrayList<business> businessList;
    private ArrayList<activity> activitiesList;
    private ArrayList<userAccount> userAccountList;
    private ArrayList<Date> lastChangedActivity;

    public listDsManager() {
    }

    public listDsManager(ArrayList<business> businessList, ArrayList<activity> activitiesList, ArrayList<userAccount> userAccountList) {
        this.businessList = businessList;
        this.activitiesList = activitiesList;
        this.userAccountList = userAccountList;
    }

    public Cursor getBusinessList() {
        String[] columns = new String[]{"id", "name", "country", "city", "phoneNumber", "street", "email", "linkUrl"};
        MatrixCursor matrix = new MatrixCursor(columns);

        for (business b : businessList) {
            matrix.addRow(new Object[]{b.getId(), b.getName(), b.getCountry(), b.getCity(), b.getStreet(), b.getPhoneNumber(), b.getEmail(), b.getLinkUrl()});
        }
        return matrix;
    }

    public void setBusinessList(ArrayList<business> businessList) {
        this.businessList = businessList;
    }

    public void addBusiness(ContentValues be) {
        this.businessList.add(new business(
                be.getAsString("id"),
                be.getAsString("name"),
                be.getAsString("country"),
                be.getAsString("city"),
                be.getAsString("phoneNumber"),
                be.getAsString("street"),
                be.getAsString("email"),
                be.getAsString("linkUrl")));
    }

    public Cursor getActivityList() {
        String[] columns = new String[]{"activityInfo", "country", "startDate", "endDate", "cost", "description", "id"};
        MatrixCursor matrix = new MatrixCursor(columns);

        for (activity act : activitiesList) {
            matrix.addRow(new Object[]{act.getActivityInfo(), act.getCountry(), act.getStartDate(), act.getEndDate(), act.getCost(), act.getDescription(), act.getId()});
        }
        return matrix;
    }

    public void setActivitiesList(ArrayList<activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public void addActivity(ContentValues ac) {
        activityType activityInfo = (activityType) ac.get("activityInfo");
        String country = (String) ac.get("country");
        Date startDate = (Date) ac.get("startString");
        Date endDate = (Date) ac.get("endString");
        Double cost = (Double) ac.get("cost");
        String description = (String) ac.get("description");
        String id = (String) ac.get("id");


        this.activitiesList.add(new activity(activityInfo,country,startDate,endDate,cost,description, id));
    }

    public Cursor getUserList() {
        String[] columns = new String[]{"userId", "username", "password"};
        MatrixCursor matrix = new MatrixCursor(columns);

        for (userAccount user : userAccountList) {
            matrix.addRow(new Object[]{user.getUserId(), user.getUsername(), user.getPassword()});
        }
        return matrix; // maybe the problem!!!!
    }

    public void setUserAccountList(ArrayList<userAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public void addUser(ContentValues user) {
        int id = (int) user.get("userId");
        String username = (String) user.get("username");
        String password = (String) user.get("password");

        this.userAccountList.add(new userAccount(id,username,password));
    }

    public boolean checkActivitiesAdded() {
        for (Date d : lastChangedActivity) {
            if (getDateDiff(d, new Date(), TimeUnit.DAYS) < 30) {
                return true;
            }
        }
        return false;
    }

    private static long getDateDiff(Date date_1, Date date_2, TimeUnit timeunit) {
        long diffInMillies = date_2.getTime() - date_1.getTime();
        return timeunit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private Date convertStringToDate(String s) {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date = format.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertDateToString(Date d) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(d);
    }
}
