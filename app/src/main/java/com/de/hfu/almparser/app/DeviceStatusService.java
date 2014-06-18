package com.de.hfu.almparser.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class DeviceStatusService extends Service {
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


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroyed ...",
                Toast.LENGTH_LONG).show();
        Log.d("Msg", " ........Service destroyed........");

    }


}
