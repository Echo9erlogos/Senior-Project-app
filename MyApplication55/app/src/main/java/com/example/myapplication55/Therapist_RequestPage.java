package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Therapist_RequestPage extends AppCompatActivity {
    Button submit;
    TextView name;
    EditText amount;
    CheckBox box;
    DatabaseReference DataRef,DataRef2;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_request_page);
        String patientname=getIntent().getStringExtra("patientname");
        String patientuid=getIntent().getStringExtra("patientuid");
        submit=findViewById(R.id.submit_button);
        name=findViewById(R.id.patient_name);
        amount=findViewById(R.id.amount);
        box=findViewById(R.id.checkBox);
        name.setText(patientname);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box.isChecked()) {
                    DataRef = FirebaseDatabase.getInstance().getReference().child("appointmentinprogress").child(uid).child(patientuid);
                    DataRef2 = FirebaseDatabase.getInstance().getReference().child("patientappointment").child(patientuid).child(uid);
                    HashMap hashMap1 = new HashMap();
                    String amount1 = amount.getText().toString();
                    hashMap1.put("paymentstatus", "1");
                    hashMap1.put("paymentamount", amount1);
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("paymentamount", amount1);
                    hashMap2.put("state", "waiting for payment");
                    DataRef.updateChildren(hashMap1);
                    DataRef2.updateChildren(hashMap2);
                    Intent intent = new Intent();
                    intent.setClass(Therapist_RequestPage.this,Therapist_PatientPage_Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Therapist_RequestPage.this,"Please tick",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}