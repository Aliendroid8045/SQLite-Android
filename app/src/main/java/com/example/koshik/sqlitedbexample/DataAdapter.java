package com.example.koshik.sqlitedbexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by koshik on 03-11-2016.
 */

class DataAdapter extends BaseAdapter {

    Context context;
    //   int[] rollNo;
    // String[] stName;
    //int[] stMarks;
    ArrayList<Student> aList;
    LayoutInflater inflater;

   /* public DataAdapter(Context context, String[] studentMame, int[] studentRollNo, int[] studentMarks) {
        this.context = context;
        this.rollNo = studentRollNo;
        this.stName = studentMame;
        this.stMarks = studentMarks;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);}*/


    public DataAdapter(Context context, ArrayList<Student> aList) {
        this.aList = aList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return aList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        ViewHolder holder = null;

        if (convertView == null) {
            Log.v("getView() called", i + "times" + " ConvertView = " + convertView);
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.single_row, null);
            holder.rollNo = (TextView) convertView.findViewById(R.id.etRollNo);
            holder.studentName = (TextView) convertView.findViewById(R.id.etName);
            holder.studentMarks = (TextView) convertView.findViewById(R.id.etMarks);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rollNo.setText(aList.get(i).getRollNo() + "");
        holder.studentName.setText(aList.get(i).getName());
        holder.studentMarks.setText(aList.get(i).getMarks() + "");


        return convertView;
    }

    static class ViewHolder {
        TextView rollNo;
        TextView studentName;
        TextView studentMarks;
    }
}
