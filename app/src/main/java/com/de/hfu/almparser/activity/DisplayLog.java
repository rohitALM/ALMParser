package com.de.hfu.almparser.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.de.hfu.almparser.app.DeviceStatusService;
import com.de.hfu.almparser.app.R;
import com.de.hfu.almparser.data.ALMDB;
import com.de.hfu.almparser.data.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to fetch event log information from DB and display it to the user
 *
 * @author Rohit
 */
public class DisplayLog extends Activity {

    ALMDB dba;
    DiaryAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dba = new ALMDB(this);
        dba.open();

        super.onCreate(savedInstanceState);

        startService(new Intent(DisplayLog.this,
                DeviceStatusService.class));

        setContentView(R.layout.activity_display_log);
        super.onCreate(savedInstanceState);
        myAdapter = new DiaryAdapter(this);
        myAdapter.notifyDataSetChanged();
        //this.setListAdapter(myAdapter);
        ListView list1 = (ListView) findViewById(R.id.list_item);
        list1.setAdapter(myAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        myAdapter = new DiaryAdapter(this);
        myAdapter.notifyDataSetChanged();
        //this.setListAdapter(myAdapter);
        ListView list1 = (ListView) findViewById(R.id.list_item);
        list1.setAdapter(myAdapter);
    }

    private class MyDiary {
        public String title;
        public String content;
        public String recorddate;

        public MyDiary(String t, String c, String r) {
            title = t;
            content = c;
            recorddate = r;
        }
    }

    private class DiaryAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<MyDiary> diaries;

        public DiaryAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            diaries = new ArrayList<MyDiary>();
            getdata();
        }

        public void getdata() {
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
                    MyDiary temp = new MyDiary(title, content, datedata);
                    Log.d("title", title);
                    Log.d("content", content);
                    Log.d("datedata", datedata);
                    diaries.add(temp);
                } while (c.moveToNext());
            }
        }

        @Override
        public int getCount() {
            return diaries.size();
        }

        public MyDiary getItem(int i) {
            return diaries.get(i);
        }

        public long getItemId(int i) {
            return i;
        }

        public View getView(int arg0, View arg1, ViewGroup arg2) {
            final ViewHolder holder;
            View v = arg1;
            if ((v == null) || (v.getTag() == null)) {
                v = mInflater.inflate(R.layout.logrow, null);
                holder = new ViewHolder();
                holder.mTitle = (TextView) v.findViewById(R.id.name);
                holder.mContent = (TextView) v.findViewById(R.id.eventcontent);
                holder.mDate = (TextView) v.findViewById(R.id.datetext);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            holder.mdiary = getItem(arg0);
            holder.mTitle.setText(holder.mdiary.title);
            holder.mContent.setText(holder.mdiary.content);
            holder.mDate.setText(holder.mdiary.recorddate);
            v.setTag(holder);
            return v;
        }

        public class ViewHolder {
            MyDiary mdiary;
            TextView mTitle;
            TextView mContent;
            TextView mDate;
        }
    }

}
