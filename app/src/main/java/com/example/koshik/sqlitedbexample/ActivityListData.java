package com.example.koshik.sqlitedbexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ActivityListData extends AppCompatActivity {
    ListView dataListView;
    DatabaseHelper dbHelper;
    int[] rollNo;
    String[] stName;
    int[] stMarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);

        dataListView = (ListView) findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        ArrayList<Student> aList = new ArrayList<>();
        Cursor data = dbHelper.getAllData();

        if (data.getCount() == 0) {
            Toast.makeText(ActivityListData.this, "No Such data Found in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                Student st = new Student();

                st.setRollNo(data.getInt(0));
                st.setName(data.getString(1));
                st.setMarks(data.getInt(2));
                aList.add(st);

            }
            DataAdapter adapter = new DataAdapter(this,aList);

            dataListView.setAdapter(adapter);

        }

    }
}
