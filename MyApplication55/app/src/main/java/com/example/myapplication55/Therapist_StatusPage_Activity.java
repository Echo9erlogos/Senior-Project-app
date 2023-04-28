package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Therapist_StatusPage_Activity extends AppCompatActivity {
    TextView name;
    EditText weeks;
    EditText advice;
    Button submit;
    DatabaseReference DataRef,DataRef2;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_status_page);
        String patientname=getIntent().getStringExtra("patientname");
        String patientuid=getIntent().getStringExtra("patientuid");
        name=findViewById(R.id.patientname);
        weeks=findViewById(R.id.weeks);
        advice=findViewById(R.id.advice);
        submit=findViewById(R.id.submit);
        name.setText(patientname);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRef = FirebaseDatabase.getInstance().getReference().child("appointmentinprogress").child(uid).child(patientuid);
                DataRef2 = FirebaseDatabase.getInstance().getReference().child("patientappointment").child(patientuid).child(uid);
                HashMap hashMap=new HashMap();
                String week=weeks.getText().toString();
                String advices=advice.getText().toString();
                hashMap.put("weeks",week);
                hashMap.put("advice",advices);
                DataRef.updateChildren(hashMap);
                DataRef2.updateChildren(hashMap);
                Intent intent = new Intent();
                intent.setClass(Therapist_StatusPage_Activity.this,Therapist_PatientPage_Activity.class);
                startActivity(intent);
            }
        });
    }
}