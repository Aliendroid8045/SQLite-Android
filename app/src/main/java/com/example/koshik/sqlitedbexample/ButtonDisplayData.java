package com.example.koshik.sqlitedbexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ButtonDisplayData extends AppCompatActivity {
    Button btnDisplayList;
    TextView tvDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_display_data);
        btnDisplayList = (Button) findViewById(R.id.btnDisplayData);
        tvDisplay = (TextView) findViewById(R.id.tvInfo);

    }

    public void displayInListView(View view) {
                Intent intent = new Intent(ButtonDisplayData.this, ActivityListData.class);
                startActivity(intent);
            }
        }

