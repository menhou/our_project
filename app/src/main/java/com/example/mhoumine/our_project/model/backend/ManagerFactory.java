package com.example.mhoumine.our_project.model.backend;

import android.content.Context;

/**
 * Created by mhoumine on 30/11/2016.
 */

public class ManagerFactory {
    static IDSManager instance = null;

    public static String mode = "sqlite";

    public final static IDSManager getInstance()
    {
        if (mode == "list")
        {
            if (instance == null)
                instance = new com.example.mhoumine.our_project.model.datasource.listDsManager();
            return instance;
        }

        if (mode == "sqlite")
        {
            if (instance == null)
                instance = new com.example.mhoumine.our_project.model.datasource.MySQL_DBManager();
            return instance;
        }
        return null;
    }


}
