package com.example.myapplication55;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication55.model.appointmentInfos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ReservationInformationEditPage extends AppCompatActivity {
    ConstraintLayout viewPageButtons;
    TextView date;
    TextView time;
    Button back;
    Button send;
    DatePickerDialog.OnDateSetListener datesetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_information_edit_page);
        String therapistKey=getIntent().getStringExtra("therapistKey");
        String therapistname=getIntent().getStringExtra("therapistname");

        viewPageButtons = findViewById(R.id.buttonlists);


        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        back=findViewById(R.id.back);
        send=findViewById(R.id.send);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hours=calendar.get(Calendar.HOUR_OF_DAY);
        final int mins=calendar.get(Calendar.MINUTE);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ReservationInformationEditPage.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, datesetListener, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        datesetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String rdate = day + "/" + month + "/" + year;
                date.setText(rdate);
            }
        };
time.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        TimePickerDialog timePickerDialog= new TimePickerDialog(ReservationInformationEditPage.this, com.google.android.material.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);
                c.setTimeZone(TimeZone.getDefault());
                SimpleDateFormat format=new SimpleDateFormat("k:mm a");
                String rtime=format.format(c.getTime());
                time.setText(rtime);
            }
        },hours,mins,false);
        timePickerDialog.show();
    }
});

send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        appointmentInfos appointmentInfos=new appointmentInfos();
        String datetxt=date.getText().toString();
        String timetxt=time.getText().toString();
        appointmentInfos.date=datetxt;
        appointmentInfos.time=timetxt;
        appointmentInfos.patientuid=uid;
        appointmentInfos.therapistuid=therapistKey;
        appointmentInfos.therapistname=therapistname;
        appointmentInfos.state="pending";
        FirebaseDatabase.getInstance().getReference().child("patientappointment").child(uid).setValue(appointmentInfos);
        FirebaseDatabase.getInstance().getReference().child("docappointment").child(therapistKey).setValue(appointmentInfos);
        startActivity(new Intent(ReservationInformationEditPage.this,Patient_TherapistPage_Activity.class));
    }
});
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(ReservationInformationEditPage.this,Patient_AppointmentPage_Activity.class));
    }
});
    }
}





