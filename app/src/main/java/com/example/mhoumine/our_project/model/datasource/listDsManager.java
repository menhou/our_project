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
import java.util.GregorianCalendar;
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
        businessList = new ArrayList<>();
        activitiesList = new ArrayList<>();
        userAccountList = new ArrayList<>();
    }

    public listDsManager(ArrayList<business> businessList, ArrayList<activity> activitiesList, ArrayList<userAccount> userAccountList) {
        businessList = new ArrayList<>();
        activitiesList = new ArrayList<>();
        userAccountList = new ArrayList<>();
        this.businessList = businessList;
        this.activitiesList = activitiesList;
        this.userAccountList = userAccountList;
    }

    public Cursor getBusinessList() {
        MatrixCursor matrix = new MatrixCursor(Contract.BusinessAdjust.COLS);

        for (business b : businessList) {
            matrix.addRow(new Object[]{b.getId(), b.getName(), b.getCountry(), b.getCity(), b.getStreet(), b.getPhoneNumber(), b.getEmail(), b.getLinkUrl()});
        }
        return matrix;
    }

    public void setBusinessList(ArrayList<business> businessList) {
        this.businessList = businessList;
    }

    public boolean addBusiness(ContentValues be) {
        try {
            String id = (String) be.get("id");
            String name = (String) be.get("name");
            String country = (String) be.get("country");
            String city = (String) be.get("city");
            String phoneNumber = (String) be.get("phoneNumber");
            String street = (String) be.get("street");
            String email = (String) be.get("email");
            String linkUrl = (String) be.get("linkUrl");

            this.businessList.add(new business(id, name, country, city, phoneNumber, street, email, linkUrl));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Cursor getActivityList() {
        MatrixCursor matrix = new MatrixCursor(Contract.ActivityAdjust.COLS);

        for (activity act : activitiesList) {
            matrix.addRow(new Object[]{act.getActivityInfo(), act.getCountry(), (Long)act.getStartDate().getTimeInMillis(), (Long)act.getEndDate().getTimeInMillis(), act.getCost(), act.getDescription(), act.getId()});
        }
        return matrix;
    }

    public void setActivitiesList(ArrayList<activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public boolean addActivity(ContentValues ac) {
        try {
            activityType activityInfo = activityType.getEnum((String) ac.get("activityInfo"));
            String country = (String) ac.get("country");
            GregorianCalendar startDate = new GregorianCalendar();
            startDate.setTimeInMillis((Long) ac.get(Contract.ActivityAdjust.START_DATE_COL));
            GregorianCalendar endDate = new GregorianCalendar();
            endDate.setTimeInMillis((Long) ac.get(Contract.ActivityAdjust.END_DATE_COL));
            Double cost = (Double) ac.get("cost");
            String description = (String) ac.get("description");
            String id = (String) ac.get("id");


            this.activitiesList.add(new activity(activityInfo, country, startDate, endDate, cost, description, id));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public Cursor getUserList() {

        MatrixCursor matrix = new MatrixCursor(Contract.UserAccountAdjust.COLS);

        for (userAccount user : userAccountList) {
            matrix.addRow(new Object[]{user.getUsername(), user.getPassword()});
        }
        return matrix;
    }

    public void setUserAccountList(ArrayList<userAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public boolean addUser(ContentValues user) {
        try {
            String username = (String) user.get(Contract.UserAccountAdjust.USERNAME_COL);
            String password = (String) user.get(Contract.UserAccountAdjust.PASSWORD_COL);

            this.userAccountList.add(new userAccount(username, password));
        }
        catch (Exception e){
            return false;
        }
        return true;
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

    private Date convertStringToDate(String s){
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            Date date = format.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String convertDateToString(Date d){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(d);
    }
}
