package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Patient_StatusPage_Activity extends AppCompatActivity {
    TextView week;
    TextView advice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_status_page);
        week=findViewById(R.id.weeks);
        advice=findViewById(R.id.advice);
        String weeks=getIntent().getStringExtra("weeks");
        String advices=getIntent().getStringExtra("advices");
        week.setText(weeks);
        advice.setText(advices);
    }
}