package com.de.hfu.almparser.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by Rohit on 21-06-2014.
 */
public class ALMDB {

    private final Context context;
    private final DBHelper dbhelper;
    private SQLiteDatabase db;

    public ALMDB(Context c) {
        context = c;
        dbhelper = new DBHelper(context, Constants.DATABASE_NAME, null,
                Constants.DATABASE_VERSION);
    }

    public void close() {
        db.close();
    }

    public void open() throws SQLiteException {
        try {
            db = dbhelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            Log.v("Open database exception caught", ex.getMessage());
            db = dbhelper.getReadableDatabase();
        }
    }

}
