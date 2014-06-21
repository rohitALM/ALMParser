package com.de.hfu.almparser.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
public class DisplayLog extends ActionBarActivity {

    ALMDB dba;
    DiaryAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_log);
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
                holder.mDate = (TextView) v.findViewById(R.id.datetext);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            holder.mdiary = getItem(arg0);
            holder.mTitle.setText(holder.mdiary.title);
            holder.mDate.setText(holder.mdiary.recorddate);
            v.setTag(holder);
            return v;
        }

        public class ViewHolder {
            MyDiary mdiary;
            TextView mTitle;
            TextView mDate;
        }
    }

}
