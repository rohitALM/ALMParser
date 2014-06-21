package com.de.hfu.almparser.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public long insertdiary(String event_type, String event_specific_data) {
//        try {
            ContentValues newTaskValue = new ContentValues();
            newTaskValue.put(Constants.TITLE_NAME, event_type);
            newTaskValue.put(Constants.CONTENT_NAME, event_specific_data);
            newTaskValue.put(Constants.DATE_NAME,
                    java.lang.System.currentTimeMillis());
            return db.insert(Constants.TABLE_NAME, null, newTaskValue);
//        } catch (SQLiteException ex) {
//            Log.v("Insert into database exception caught",
//                    ex.getMessage());
//            return -1;
//        }
    }

    public Cursor getdiaries() {
        Cursor c = db.query(Constants.TABLE_NAME, null, null,
                null, null, null, null);
        return c;
    }

}
