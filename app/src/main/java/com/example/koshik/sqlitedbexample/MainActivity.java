package com.example.koshik.sqlitedbexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sRollNo, sName, sMarks;
    Button dataStore, dataUpdate, dataDelete, dataRetrieve, dataAll;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create DatabaseHelper class object to access methods and variable
        dbHelper = new DatabaseHelper(this);

        sRollNo = (EditText) findViewById(R.id.etRollNo);
        sName = (EditText) findViewById(R.id.etStudentName);
        sMarks = (EditText) findViewById(R.id.etStudentMarks);

        dataStore = (Button) findViewById(R.id.btnStore);
        dataDelete = (Button) findViewById(R.id.btnDelete);
        dataRetrieve = (Button) findViewById(R.id.btnRetrieve);
        dataUpdate = (Button) findViewById(R.id.btnUpdate);
        dataAll = (Button) findViewById(R.id.btnDisplayAll);
    }

    //this will perform the operation of insertion using dbHelper class object as it is middleware between database and application
    public void dataStore(View view) {
        try {
            //we need to open database each time to perform any operation into database
            dbHelper.openDatabase();
            //collect data from EditText
            int dataRoll = Integer.parseInt(sRollNo.getText().toString());
            String dataName = sName.getText().toString();
            int dataMarks = Integer.parseInt(sMarks.getText().toString());

            if (dbHelper.insertData(dataRoll, dataName, dataMarks) >= 0) {
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbHelper.closeDatabase();
        }
        //clear the EditTexts after data inserted successfully
        sRollNo.setText("");
        sName.setText("");
        sMarks.setText("");
    }


    public void dataUpdate(View view) {
        try {
            dbHelper.openDatabase();
            int dataRoll = Integer.parseInt(sRollNo.getText().toString());
            String dataName = sName.getText().toString();
            int dataMarks = Integer.parseInt(sMarks.getText().toString());

            if (dbHelper.updateData(dataRoll, dataName, dataMarks) >= 0) {
                Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Data Updating Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbHelper.closeDatabase();
        }

        sRollNo.setText("");
        sName.setText("");
        sMarks.setText("");
    }

    public void dataRetrieve(View view) {
        String stName;
        int stMark;

        try {
            dbHelper.openDatabase();
            //to dataRetrieve we need to compare rollNo with the database using cursor object so it will first get the text as string through below int rollNo edittext
            int rollNo = Integer.parseInt(sRollNo.getText().toString());
            Cursor cursor = dbHelper.showData(rollNo);

            //check weather cursor is empty or not if does not than move cursor first and the arguments
            // 1 and 2 inside the stName and stMarks ias it is 2nd and 3rd column but indext start from 0 and rollNo is 1st column
            if (cursor != null) {
                cursor.moveToFirst();
                stName = cursor.getString(1);
                stMark = cursor.getInt(2);
                sName.setText(stName);
                sMarks.setText(stMark + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbHelper.closeDatabase();
        }
    }

    public void dataDelete(View view) {
        try {
            dbHelper.openDatabase();
            if (dbHelper.deleteData(Integer.parseInt(sRollNo.getText().toString())) > 0) {
                Toast.makeText(this, "Deletion Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Deletion Failed ", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbHelper.closeDatabase();
        }
    }

    public void retrieveAllData(View view) {
        Intent intent = new Intent(MainActivity.this, ButtonDisplayData.class);
        startActivity(intent);
    }
}
