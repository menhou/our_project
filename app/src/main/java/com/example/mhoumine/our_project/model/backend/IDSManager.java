package com.example.mhoumine.our_project.model.backend;

import com.example.mhoumine.our_project.model.datasource.listDsManager;
import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mhoumine on 27/11/2016.
 */

public class IDSManager {
    listDsManager list;
    private ArrayList<Date> lastChangedBusiness;
    private ArrayList<Date> lastChangedActivity;
    private ArrayList<Date> lastChangedUserAccount;

    public IDSManager() {
        listDsManager list = new listDsManager();
    }

    public void insertUser(userAccount user) {

        list.getUserAccountList().add(user);
        Date date = new Date();
        lastChangedUserAccount.add(date);
    }

    public void insertBusiness(business b) {
        list.getBusinessList().add(b);
        Date date = new Date();
        lastChangedBusiness.add(date);
    }

    public void insertBusiness(activity a) {
        list.getActivitiesList().add(a);
        Date date = new Date();
        lastChangedActivity.add(date);
    }

    public ArrayList<userAccount> returnUsers() {
        return list.getUserAccountList();
    }

    public ArrayList<business> returnBusinesses() {
        return list.getBusinessList();
    }

    public ArrayList<activity> returnActivities() {
        return list.getActivitiesList();
    }

    public ArrayList<business> recentBusinessUpdates() {
        ArrayList<business> tempBusiness = new ArrayList();
        int i = 0;
        for (Date d : lastChangedBusiness) {

            if (getDateDiff(d, new Date(), TimeUnit.DAYS) < 30) {
                tempBusiness.add(list.getBusinessList().get(i))
            }
            i++;
        }
        return tempBusiness;
    }

    public ArrayList<activity> recentActivityUpdates() {
        ArrayList<activity> tempActivity = new ArrayList();
        int i = 0;
        for (Date d : lastChangedActivity) {

            if (getDateDiff(d, new Date(), TimeUnit.DAYS) < 30) {
                tempActivity.add(list.getActivitiesList().get(i))
            }
            i++;
        }
        return tempActivity;
    }

    public ArrayList<userAccount> recentUserAccountUpdates() {
        ArrayList<userAccount> tempusers = new ArrayList();
        int i = 0;
        for (Date d : lastChangedUserAccount) {

            if (getDateDiff(d, new Date(), TimeUnit.DAYS) < 30) {
                tempusers.add(list.getUserAccountList().get(i))
            }
            i++;
        }
        return tempusers;
    }

    private static long getDateDiff(Date date_1, Date date_2, TimeUnit timeunit) {
        long diffInMillies = date_2.getTime() - date_1.getTime();
        return timeunit.convert(diffInMillies, TimeUnit.MILLISECONDS);

    }
}
