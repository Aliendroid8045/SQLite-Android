package com.example.koshik.sqlitedbexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by koshik on 01-11-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASENAME = "College.db";
    public static final String TABLENAME = "Student";
    public static final String ROLLNO = "rollNo";
    public static final String STUDENTNAME = "studentName";
    public static final String STUDENTMARK = "studentMarks";
    public static final int VERSION = 3;

    public static final String create_table_query = "create table " + TABLENAME +
            "(" + ROLLNO + " INTEGER PRIMARY KEY NOT NULL" + ","
            +  STUDENTNAME + " TEXT" + STUDENTMARK + " int)";
    public SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
        Log.v("Database", create_table_query);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//this will call the select query statement and create table into database using execSQL method
        sqLiteDatabase.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //open database connection using database object
    public void openDatabase() {
        //we are writing into database so getWritable use
        database = this.getWritableDatabase();
    }

    // insert data into database using inset method pass 3 parameter
    public long insertData(int rollNo, String studentName, int studentMarks) {
        //ContentValues is store the data and pass it from SQLiteHelper to database
        //store the data in key-value pair
        ContentValues values = new ContentValues();
        values.put(ROLLNO, rollNo);
        values.put(STUDENTNAME, studentName);
        values.put(STUDENTMARK, studentMarks);

        return database.insert(TABLENAME, null, values);
    }

    //updating data with this method as ContentValues is responsible to carry data from one to another place
    public int updateData(int rollNo, String studentName, int studentMarks) {
        ContentValues values = new ContentValues();
        values.put(ROLLNO, rollNo);
        values.put(STUDENTNAME, studentName);
        values.put(STUDENTMARK, studentMarks);
//we are updating into existing database so used database object database.update
        return database.update(TABLENAME, values, ROLLNO + "=" + rollNo, null);


    }
    //Retrieval operation use cursor as cursor responsible to move from
    // one index to another index and fetch data from tables

    public Cursor showData(int rollNo) {
        //return statement give you data row
        Cursor cursor = database.query(TABLENAME, new String[]{ROLLNO, STUDENTNAME, STUDENTMARK}, ROLLNO + "=" + rollNo, null, null, null, null);
        return cursor;
    }

    //delete operation does not required any reference as it can delete row directly from table
    //return statement return the deleted no of row
    public int deleteData(int rollNo) {
        return database.delete(TABLENAME, ROLLNO + "=" + rollNo, null);

    }
    // display all data into list view
    public Cursor getAllData() {
        database = this.getReadableDatabase();
        String[] columns = {DatabaseHelper.ROLLNO, DatabaseHelper.STUDENTNAME, DatabaseHelper.STUDENTMARK};
        Cursor data = database.query(DatabaseHelper.TABLENAME,columns, null, null, null, null, null);
        if (data != null) {
            data.moveToFirst();
        }
        return data;
    }
    //we need to close each time we perform operation so memory not going to leak any data

    public void closeDatabase() {
        database.close();
        this.close();
    }

}
