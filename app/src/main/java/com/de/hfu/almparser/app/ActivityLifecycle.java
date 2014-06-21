package com.de.hfu.almparser.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Rohit
 */
public class ActivityLifecycle extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        startService(new Intent(ActivityLifecycle.this,
                DeviceStatusService.class));



    }


    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }


}
