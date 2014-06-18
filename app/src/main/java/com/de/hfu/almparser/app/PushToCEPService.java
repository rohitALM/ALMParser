package com.de.hfu.almparser.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Rohit on 18-06-2014.
 */
public class PushToCEPService extends Service {

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Push Service created ...",
                Toast.LENGTH_LONG).show();
        Log.d("Msg", " ........Push Service Created........");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Push Service destroyed ...",
                Toast.LENGTH_LONG).show();
        Log.d("Msg", " ........Push Service destroyed........");

    }
}
