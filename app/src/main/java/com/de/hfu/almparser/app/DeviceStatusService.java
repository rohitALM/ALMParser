package com.de.hfu.almparser.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DeviceStatusService extends Service {
    public DeviceStatusService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
