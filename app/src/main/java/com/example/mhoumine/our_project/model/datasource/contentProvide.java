package com.example.mhoumine.our_project.model.datasource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.ContentUris;
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
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.ACTIVITY, 1); //need to make some compatibles
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.BUSINESS, 2);    //need to make some compatibles
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.USER_ACCOUNT, 3);    //need to make some compatibles
    }
    @Override
    public boolean onCreate() {
        return false;
    }
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1)
    {
            switch (sUriMatcher.match(uri)) {

                case 1:
                    return manager.getActivityList();

                case 2:
                    return manager.getBusinessList();
                case 3:
                    return manager.getUserList();
                default:
                    throw new IllegalArgumentException("Unrecognized Query-Table");
            }
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

        switch(sUriMatcher.match(uri)) {
            case 1:
                if ( manager.addActivity(obj) )
                    return ContentUris.withAppendedId(Contract.ActivityAdjust.CONTENT_URI, 1);
                return ContentUris.withAppendedId(Contract.ActivityAdjust.CONTENT_URI, 0);

            case 2:
                if ( manager.addBusiness(obj) )
                    return ContentUris.withAppendedId(Contract.BusinessAdjust.CONTENT_URI, 1);
                return ContentUris.withAppendedId(Contract.BusinessAdjust.CONTENT_URI, 0);

            case 3:
                if ( manager.addUser(obj) )
                    return ContentUris.withAppendedId(Contract.UserAccountAdjust.CONTENT_URI, 1);
                return ContentUris.withAppendedId(Contract.UserAccountAdjust.CONTENT_URI, 0);

            default:
                throw new IllegalArgumentException("Unrecognized Insertion-Table");
        }


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
}
