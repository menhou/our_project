package com.example.mhoumine.our_project.model.Service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.example.mhoumine.our_project.model.backend.IDSManager;
import com.example.mhoumine.our_project.model.backend.ManagerFactory;
import com.example.mhoumine.our_project.model.datasource.Contract;


public class CheckerService extends Service {
    private IDSManager manager;
    private boolean isRunning = false;
    @Override
    public void onCreate() {
        manager = ManagerFactory.getInstance();
        isRunning = true;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                while(isRunning) {
                    try {
                        Thread.sleep(10000);
                        Cursor c = getContentResolver().query(Contract.CheckDBUpdate.CONTENT_URI, null, null, null, null);
                        if (Contract.CheckDBUpdate.isUserAccountUpdated(c)){
                            Intent i = new Intent("Update");
                            i.putExtra("Extra", "User Extra");
                            sendBroadcast(i);
                        }
                        if (Contract.CheckDBUpdate.isBusinessUpdated(c)){
                            Intent i = new Intent("Update");
                            i.putExtra("Extra", "Business Extra");
                            sendBroadcast(i);
                        }
                        if (Contract.CheckDBUpdate.isActivityUpdated(c)){
                            Intent i = new Intent("Update");
                            i.putExtra("Extra", "Activity Extra");
                            sendBroadcast(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
        return Service.START_STICKY;
    }
}
