package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    DatabaseHelper myDB;
    TextView editDate, editTime;
    Button dateButton, timeButton, saveButton, viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        editDate = findViewById(R.id.editDate);
        dateButton = findViewById(R.id.dateButton);
        editTime = findViewById(R.id.editTime);
        timeButton = findViewById(R.id.timeButton);
        saveButton = findViewById(R.id.saveButton);
        viewButton = findViewById(R.id.viewButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editDate.getText().toString().isEmpty() && !editTime.getText().toString().isEmpty()) {
                    String dateTXT = editDate.getText().toString();
                    String timeTXT = editTime.getText().toString();
                    Boolean insertDateTime = myDB.insertDateTime(dateTXT, timeTXT);
                    if (insertDateTime == true) {
                        Toast.makeText(MainActivity.this, "New Date time", Toast.LENGTH_SHORT).show();
                        editDate.setText("");
                        editTime.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "New Date time", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(MainActivity.this, "Date and time fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getData();
                if(res.getCount() ==0){
                    Toast.makeText(MainActivity.this, "No Date time exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Date: "+res.getString(0)+"\n");
                    buffer.append("Time: "+res.getString(1)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Saved date and time");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Please note that use your package name here
                com.example.myapplication.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.myapplication.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v){
                com.example.myapplication.TimePicker mTimePickerDialogFragment;
                mTimePickerDialogFragment = new com.example.myapplication.TimePicker();
                mTimePickerDialogFragment.show(getSupportFragmentManager(), "TIME PICK");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.SHORT).format(mCalendar.getTime());
        editDate.setText(selectedDate);
    }
    @Override
    public void onTimeSet(android.widget.TimePicker view, int hour, int minute){
        Calendar tCalendar = Calendar.getInstance();
        tCalendar.set(Calendar.HOUR_OF_DAY, hour);
        tCalendar.set(Calendar.MINUTE, minute);
        String selectedDate =DateFormat.getTimeInstance(DateFormat.SHORT).format(tCalendar.getTime());
        editTime.setText(selectedDate);
    }


}