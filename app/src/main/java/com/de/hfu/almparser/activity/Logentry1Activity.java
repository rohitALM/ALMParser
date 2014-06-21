package com.de.hfu.almparser.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.de.hfu.almparser.app.R;
import com.de.hfu.almparser.data.ALMDB;
import com.de.hfu.almparser.data.Constants;

import java.text.DateFormat;
import java.util.Date;


public class Logentry1Activity extends ActionBarActivity {

    EditText titleET, contentET;
    Button submitBT;
    ALMDB dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logentry1);

        dba = new ALMDB(this);
        // dba.open();
        titleET = (EditText) findViewById(R.id.diarydescriptionText);
        contentET = (EditText) findViewById(R.id.diarycontentText);
        submitBT = (Button) findViewById(R.id.submitButton);
        submitBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    saveItToDB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveItToDB() {

        dba.open();
        dba.insertdiary(titleET.getText().toString(),
                contentET.getText().toString());

        titleET.setText("");
        contentET.setText("");

        Cursor c = dba.getdiaries();
        startManagingCursor(c);
        if (c.moveToFirst()) {
            do {
                String title =
                        c.getString(c.getColumnIndex(Constants.TITLE_NAME));
                String content =
                        c.getString(c.getColumnIndex(Constants.CONTENT_NAME));
                DateFormat dateFormat =
                        DateFormat.getDateTimeInstance();
                String datedata = dateFormat.format(new
                        Date(c.getLong(c.getColumnIndex(
                        Constants.DATE_NAME))).getTime());
                Log.d("Title", title);
                Log.d("content", content);
                Log.d("datedata", datedata);
            } while (c.moveToNext());
        }
        dba.close();
        Intent i = new Intent(Logentry1Activity.this, DisplayLog.class);
        startActivity(i);
    }


}
