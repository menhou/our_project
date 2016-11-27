package com.example.mhoumine.our_project.model.datasource;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;
import com.example.mhoumine.our_project.model.entities.activity;
import java.util.ArrayList;

/**
 * Created by mhoumine on 27/11/2016.
 */

public class listDsManager {
    private ArrayList<business> businessList;
    private ArrayList<activity> activitiesList;
    private ArrayList<userAccount> userAccountList;

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

    public ArrayList<activity> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(ArrayList<activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public ArrayList<userAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<userAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }


}
