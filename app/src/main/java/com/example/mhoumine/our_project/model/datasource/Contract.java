package com.example.mhoumine.our_project.model.datasource;

/**
 * Created by Gal on 15/12/2016.
 */

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mhoumine.our_project.model.entities.activity;
import com.example.mhoumine.our_project.model.entities.activityType;
import com.example.mhoumine.our_project.model.entities.business;
import com.example.mhoumine.our_project.model.entities.userAccount;

import java.util.ArrayList;
import java.util.Date;

public final class Contract {
    private Contract(){}
    public static final String AUTHORITY = "com.example.mhoumine.our_project.travelagencies.contentProvide";
    public static final Uri BASE_PROVIDER_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PERMISSIONS = "com.example.mhoumine.our_project.travelagencies.permissions.ACCESS_DATA";
    public static final String BUSINESS = "businesses";
    public static final String ACTIVITY = "activities";
    public static final String USER_ACCOUNT = "useraccounts";

    public static final class ActivityAdjust implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_PROVIDER_URI.buildUpon().appendPath(ACTIVITY).build();

        public static final String TYPE_COL = "activityInfo";
        public static final String COUNTRY_COL = "country";
        public static final String START_DATE_COL = "startDate";
        public static final String END_DATE_COL = "endDate";
        public static final String COST_COL = "cost";
        public static final String DESCRIPTION_COL = "description";
        public static final String ID_COL = "id";
        public static final String[] COLS = {
            TYPE_COL,COUNTRY_COL,START_DATE_COL,END_DATE_COL,COST_COL, DESCRIPTION_COL, ID_COL
        };

        public static ArrayList<activity> cursorToList(Cursor c){
            if (c == null){
                return new ArrayList<>();
            }

            ArrayList<activity> list = new ArrayList<>();
            c.moveToFirst();

            do {

                activityType type = activityType.valueOf( c.getString( c.getColumnIndex( TYPE_COL ) ) );
                String country = c.getString( c.getColumnIndex( COUNTRY_COL ) );

                Date start = new Date();
                start.setTime( c.getLong( c.getColumnIndex( START_DATE_COL ) ) );

                Date end = new Date();
                end.setTime( c.getLong( c.getColumnIndex( END_DATE_COL ) )); ;

                double price = c.getDouble( c.getColumnIndex( COST_COL ) );
                String description = c.getString( c.getColumnIndex( DESCRIPTION_COL ) );
                String id = c.getString( c.getColumnIndex( ID_COL ) );


                list.add(new activity(type, country, start, end, price, description, id));
            } while (c.moveToNext());
            return list;
        }

        public static ContentValues createContentValues(activityType type, String country, Date start, Date end, double price, String description, String id) {
            ContentValues content = new ContentValues();

            content.put(TYPE_COL, type.toString());
            content.put(COUNTRY_COL, country);
            content.put(START_DATE_COL, start.getTime());
            content.put(END_DATE_COL, end.getTime());
            content.put(COST_COL, price);
            content.put(DESCRIPTION_COL, description);
            content.put(ID_COL, id);

            return content;
        }
    }


    public static final class BusinessAdjust implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_PROVIDER_URI.buildUpon().appendPath(BUSINESS).build();

        public static final String ID_COL = "id";
        public static final String NAME_COL = "name";
        public static final String COUNTRY_COL = "country";
        public static final String CITY_COL = "city";
        public static final String STREET_COL = "street";
        public static final String PHONE_NUMBER_COL = "phoneNumber";
        public static final String EMAIL_COL = "email";
        public static final String LINK_URL_COL = "linkUrl";

        public static final String[] COLS = {
                ID_COL,NAME_COL,COUNTRY_COL,CITY_COL,STREET_COL,PHONE_NUMBER_COL,EMAIL_COL,LINK_URL_COL
        };

        public static ArrayList<business> cursorToList(Cursor c){
            if (c == null){
                return new ArrayList<>();
            }

            ArrayList<business> list = new ArrayList<business>();
            c.moveToFirst();

            do {
                String id = c.getString(c.getColumnIndex(ID_COL));
                String name = c.getString(c.getColumnIndex(NAME_COL));
                String country = c.getString(c.getColumnIndex(COUNTRY_COL));
                String city = c.getString(c.getColumnIndex(CITY_COL));
                String street = c.getString(c.getColumnIndex(STREET_COL));
                String phoneNumber = c.getString(c.getColumnIndex(PHONE_NUMBER_COL));
                String email = c.getString(c.getColumnIndex(EMAIL_COL));
                String linkUrl = c.getString(c.getColumnIndex(LINK_URL_COL));

                list.add(new business(id,name,country,city,street,phoneNumber,email,linkUrl));
            } while (c.moveToNext());
            return list;
        }
        public static ContentValues createContentValues(String ID, String name, String country, String city, String street, String phoneNumber, String email, String website) {
            ContentValues content = new ContentValues();

            content.put(ID_COL, ID);
            content.put(NAME_COL, name);
            content.put(COUNTRY_COL, country);
            content.put(CITY_COL, city);
            content.put(STREET_COL, street);
            content.put(PHONE_NUMBER_COL, phoneNumber);
            content.put(EMAIL_COL, email);
            content.put(LINK_URL_COL, website);

            return content;
        }
    }

    public static final class classUserAccountAdjust implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_PROVIDER_URI.buildUpon().appendPath(USER_ACCOUNT).build();

        public static final String USER_ID_COL = "userId";
        public static final String USERNAME_COL = "username";
        public static final String PASSWORD_COL = "password";

        public static final String[] COLS = {
                USER_ID_COL, USERNAME_COL,PASSWORD_COL
        };

        public static ArrayList<userAccount> cursorToList(Cursor c){
            if (c == null){
                return new ArrayList<>();
            }

            ArrayList<userAccount> list = new ArrayList<>();
            c.moveToFirst();

            do {
                int userId = c.getInt(c.getColumnIndex(USER_ID_COL));
                String username = c.getString(c.getColumnIndex(USERNAME_COL));
                String password = c.getString(c.getColumnIndex(PASSWORD_COL));

                list.add(new userAccount(userId,username,password));
            } while (c.moveToNext());
            return list;
        }

        public static ContentValues createContentValues(int userId, String username, String password) {
            ContentValues content = new ContentValues();

            content.put(USER_ID_COL, userId);
            content.put(USERNAME_COL, username);
            content.put(PASSWORD_COL, password);

            return content;
        }
    }


}
