package com.de.hfu.almparser.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;

import com.de.hfu.almparser.data.ALMDB;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;

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
            pushMessageToCEP(action, added_package);

        } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String removed_package = intent.getData().toString();
            Log.d("OnReceive", "Action Received - Package Removed");
            Log.d("Package name", removed_package);
            saveLogEntry(action, removed_package, context);
            pushMessageToCEP(action, removed_package);
        }

    }


    private void pushMessageToCEP(String eventType, String eventInformation) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://192.168.178.61:8080/ALMCep/webapi/myresource/");
        post.setHeader("content-type", "application/json; charset=UTF-8");

        JSONObject dato = new JSONObject();


        try {
            dato.put("eventType", eventType);
            dato.put("eventInformation", eventInformation);


            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

            HttpResponse resp = httpClient.execute(post);


            System.out.println("OKAY!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
