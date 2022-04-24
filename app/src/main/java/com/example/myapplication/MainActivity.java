package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    TextView editDate, editTime;
    Button dateButton, timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editDate = findViewById(R.id.editDate);
        dateButton = findViewById(R.id.dateButton);
        editTime = findViewById(R.id.editTime);
        timeButton = findViewById(R.id.timeButton);
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