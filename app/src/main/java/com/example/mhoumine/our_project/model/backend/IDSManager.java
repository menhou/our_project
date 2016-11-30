package com.example.mhoumine.our_project.model.backend;

import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.util.ArrayList;

/**
 * Created by mhoumine on 30/11/2016.
 */

public interface IDSManager {
    void addBusiness(business be);
    void addActivity (activity be);
    void addUser (userAccount be);
    ArrayList<business> getBusinessList ();
    ArrayList<activity> getActivityList ();
    ArrayList<userAccount> getUserList();
    boolean checkActivitiesAdded ();
}
