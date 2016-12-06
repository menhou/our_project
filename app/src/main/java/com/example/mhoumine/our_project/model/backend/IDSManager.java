package com.example.mhoumine.our_project.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.util.ArrayList;

/**
 * Created by mhoumine on 30/11/2016.
 */

public interface IDSManager {
    void addBusiness(ContentValues be);
    void addActivity (ContentValues act);
    void addUser (ContentValues user);
    Cursor getBusinessList ();
    Cursor getActivityList ();
    Cursor getUserList();
    boolean checkActivitiesAdded ();
}
