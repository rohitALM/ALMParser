package com.de.hfu.almparser.app;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * Service which listens for event change information
 *
 * @author Rohit
 */
public class DeviceStatusService extends Service {

    ParserBroadCastReceiver intentReceiver = new ParserBroadCastReceiver();

    public DeviceStatusService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    /**
     * Register Intent to listen for package add and remove on creation of service
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Msg", " ........Service Created........");
        IntentFilter intentFilter =
                new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        registerReceiver(intentReceiver, intentFilter);
        Log.d("OnCreate - Service", "Receiver Registered");


    }

    /**
     * Unregister receiver on destroy
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(intentReceiver);
        Log.d("Msg", " ........Service destroyed........");
        Log.d("Msg", " ........Receiver destroyed........");

    }


}
