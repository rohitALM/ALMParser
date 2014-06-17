package com.de.hfu.almparser.app;

import android.app.Activity;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.widget.Toast;
=======
import android.util.Log;
import android.view.View;
import android.widget.Button;
>>>>>>> Stashed changes


public class ActivityLifecycle extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

<<<<<<< Updated upstream
    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
=======
        IntentFilter intentFilter =
                new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        registerReceiver(intentReceiver, intentFilter);
        Log.d("OnCreate" , "Receiver Registered");

        Button startButton = (Button) findViewById(R.id.Button01);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startService(new Intent(ActivityLifecycle.this,
                        DeviceStatusService.class));
            }
        });

        Button stopButton = (Button) findViewById(R.id.Button02);

        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopService(new Intent(ActivityLifecycle.this,
                        DeviceStatusService.class));
            }
        });
>>>>>>> Stashed changes
    }


}
