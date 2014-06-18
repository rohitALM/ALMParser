package com.de.hfu.almparser.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Rohit on 17-06-2014.
 */
public class ParserBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("Action Name" , action);
        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.d("OnReceive","Action Received - Packege Added");

        } else {
            Log.d("OnReceive","Action Received - Packege Removed");
        }

    }
}
