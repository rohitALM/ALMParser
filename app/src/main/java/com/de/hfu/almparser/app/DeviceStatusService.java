package com.de.hfu.almparser.app;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
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


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created ...",
                Toast.LENGTH_LONG).show();
        Log.d("Msg", " ........Service Created........");

        IntentFilter intentFilter =
                new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        registerReceiver(intentReceiver, intentFilter);
        Log.d("OnCreate - Service", "Receiver Registered");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(intentReceiver);
        Toast.makeText(this, "Service destroyed ...",
                Toast.LENGTH_LONG).show();
        Log.d("Msg", " ........Service destroyed........");
        Log.d("Msg", " ........Receiver destroyed........");

    }


}
