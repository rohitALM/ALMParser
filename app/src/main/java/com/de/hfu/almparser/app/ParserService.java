package com.de.hfu.almparser.app;

import android.app.IntentService;
import android.content.Intent;


/**
 * Created by Rohit on 11-06-2014.
 * Will be the main service class which will run the parser
 */
public class ParserService extends IntentService{

    public ParserService() {


        super("ParserService");
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               android.content.Context#startService(android.content.Intent)}.
     */

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
