package com.de.hfu.almparser.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.de.hfu.almparser.app.DeviceStatusService;

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
        Toast.makeText(ActivityLifecycle.this, "Wohoo!!",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ActivityLifecycle.this, Logentry1Activity.class);
        startActivity(i);



    }


    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }


}
