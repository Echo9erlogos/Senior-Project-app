package com.example.appointmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView dateView;
    private TimePicker timeView;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateView = (TextView) findViewById(R.id.editTextDate);
        timeView = (TimePicker) findViewById(R.id.editTextTime2);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

    public void setDate(View view) {
        showDialog(1);
        Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                .show();
    }
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 1) {
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this,
                    myDateListener, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); //disable previous dates
            return datePickerDialog;
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int i, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    public StringBuilder getTime(int hour, int min) {
        String format = "";
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        return new StringBuilder().append(hour).append(" ").append(format);
    }


    public void setAppointment(View view) {

        EditText name = (EditText)findViewById(R.id.editTextTextPersonName2);
        String patientName = name.getText().toString();
        String date = dateView.getText().toString();
        String time = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            time = getTime(timeView.getHour(), timeView.getMinute()).toString();
        }
        String toDisplay = patientName+" you have made appointment on  "+date+" at "+time+" .";
        // start the new activity for showing the appointment details
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(Intent.EXTRA_TEXT, toDisplay);
        startActivity(intent);
    }
}