package com.example.myapplication55;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PatientPaymentPageActivity extends AppCompatActivity {
    Button pay;
    TextView name;
    TextView amount;
    CheckBox box;
    DatabaseReference DataRef;
    ImageButton home;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_payment_page);
        String therapistname=getIntent().getStringExtra("therapistname");
        String therapistuid=getIntent().getStringExtra("therapistuid");
        String amount1=getIntent().getStringExtra("amount");
        home=findViewById(R.id.backhome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(PatientPaymentPageActivity.this,Patient_HomePage_Activity.class);
                startActivity(intent);
            }
        });
        pay=findViewById(R.id.pay_button);
        name=findViewById(R.id.therapist_name);
        amount=findViewById(R.id.amount);
        box=findViewById(R.id.checkBox);
        name.setText(therapistname);
        amount.setText(amount1);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box.isChecked()) {
                    DataRef = FirebaseDatabase.getInstance().getReference().child("patientappointment").child(uid).child(therapistuid);
                    HashMap hashMap = new HashMap();
                    hashMap.put("state", "Paid");
                    DataRef.updateChildren(hashMap);
                    Intent intent = new Intent();
                    intent.setClass(PatientPaymentPageActivity.this,Patient_Rating_Page_Activity.class);
                    intent.putExtra("therapistuid", therapistuid);
                    intent.putExtra("therapistname", therapistname);
                    startActivity(intent);
                }else{
                    Toast.makeText(PatientPaymentPageActivity.this,"Please tick",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}