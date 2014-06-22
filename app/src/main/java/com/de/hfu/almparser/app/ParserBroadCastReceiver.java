package com.de.hfu.almparser.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.de.hfu.almparser.data.ALMDB;

/**
 * Created by Rohit on 17-06-2014.
 */
public class ParserBroadcastReceiver extends BroadcastReceiver {

    ALMDB dba;


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.d("Action Name", action);
        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            String added_package = intent.getData().toString();
            Log.d("OnReceive", "Action Received - Package Added");
            Log.d("Package name", added_package);
            saveLogEntry(action, added_package, context);

        } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String removed_package = intent.getData().toString();
            Log.d("OnReceive", "Action Received - Package Removed");
            Log.d("Package name", removed_package);
            saveLogEntry(action, removed_package, context);
        }

    }

    /**
     * Save log entries to the DB
     *
     * @param eventType
     * @param eventInformation
     * @param context
     */
    private void saveLogEntry(String eventType, String eventInformation, Context context) {

        dba = getDba(context);
        dba.open();
        dba.insertdiary(eventType, eventInformation);
        Log.d("Receiver saving Log", eventType);
        dba.close();


    }

    /**
     * Singleton to get db instance
     *
     * @param c context
     * @return ALMDB instance
     */
    private ALMDB getDba(Context c) {
        if (null == dba) {
            dba = new ALMDB(c);
        }
        return dba;
    }


}
