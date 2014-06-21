package com.de.hfu.almparser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.de.hfu.almparser.app.R;
import com.de.hfu.almparser.data.ALMDB;


public class Logentry1Activity extends ActionBarActivity {

    EditText titleET, contentET;
    Button submitBT;
    ALMDB dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logentry1);

        dba = new ALMDB(this);
        dba.open();
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
        dba.insertdiary(titleET.getText().toString(),
                contentET.getText().toString());
        dba.close();
        titleET.setText("");
        contentET.setText("");
        Intent i = new Intent(Logentry1Activity.this, DisplayLog.class);
        startActivity(i);
    }


}
