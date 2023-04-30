package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Patient_StatusPage_Activity extends AppCompatActivity {
    TextView week;
    TextView advice;
    ImageButton home;
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
        home=findViewById(R.id.backhome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(Patient_StatusPage_Activity.this,Patient_HomePage_Activity.class);
                startActivity(intent);
            }
        });
    }
}