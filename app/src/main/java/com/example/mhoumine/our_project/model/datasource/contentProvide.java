package com.example.mhoumine.our_project.model.datasource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.mhoumine.our_project.model.backend.IDSManager;
import com.example.mhoumine.our_project.model.backend.ManagerFactory;

/**
 * Created by mhoumine on 30/11/2016.
 */

public class contentProvide extends ContentProvider {
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static IDSManager manager = ManagerFactory.getInstance();
    //sets the uris
    static {
        sUriMatcher.addURI("com.example.mhoumine.our_project.travelagencies", "activities", 1); //need to make some compatibles
        sUriMatcher.addURI("com.example.mhoumine.our_project.travelagencies", "businesses", 2);    //need to make some compatibles
        sUriMatcher.addURI("com.example.mhoumine.our_project.travelagencies", "useraccounts", 3);    //need to make some compatibles
    }
    @Override
    public boolean onCreate() {
        return false;
    }
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1)
    {
        /*
         ### The uri.getPath() function returns the path with the preceding "/", if you want to get rid of it you can simply
         use the substring function  as shown below.
         */
        String table = uri.getPath().substring(1);
        if (table.equalsIgnoreCase("activities"))
        {
            try {
                return manager.getActivityList();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (table.equalsIgnoreCase("businesses")){
            try {
                return manager.getBusinessList();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (table.equalsIgnoreCase("useraccounts")){
            try {
                return manager.getBusinessList();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues obj) {

        String table = uri.getPath().substring(1);

        if (table.equalsIgnoreCase("activities")) {

            manager.addActivity(obj);

            return null;

        }

        if(table.equalsIgnoreCase("businesses")){

            manager.addBusiness(obj);

            return null;

        }

        if(table.equalsIgnoreCase("useraccounts")){

            manager.addUser(obj);

            return null;

        }

        throw new IllegalArgumentException("This Content Provider supports only activities insertion");

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
}
