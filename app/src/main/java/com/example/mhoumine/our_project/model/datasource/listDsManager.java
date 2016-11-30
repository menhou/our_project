package com.example.mhoumine.our_project.model.datasource;

import android.content.Context;
import android.database.Cursor;

import com.example.mhoumine.our_project.model.backend.IDSManager;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;
import com.example.mhoumine.our_project.model.entities.activity;

import java.util.Date;
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

    public ArrayList<business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(ArrayList<business> businessList) {
        this.businessList = businessList;
    }

    public void addBusiness(business be) {
        this.businessList.add(be);
    }

    public ArrayList<activity> getActivityList() {
        return activitiesList;
    }

    public void setActivitiesList(ArrayList<activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public void addActivity(activity be) {
        this.activitiesList.add(be);
        lastChangedActivity.add(new Date());
    }

    public ArrayList<userAccount> getUserList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<userAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public void addUser(userAccount be) {
        this.userAccountList.add(be);
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
}
