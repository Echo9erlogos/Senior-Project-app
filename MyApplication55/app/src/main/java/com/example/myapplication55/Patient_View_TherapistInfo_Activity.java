package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patient_View_TherapistInfo_Activity extends AppCompatActivity {
    ConstraintLayout viewPageButtons;
    TextView tname, experience, description, address1, address2, contact1, contact2, workinghours1, workinghours2;
    DatabaseReference ref;
    Button back, schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_therapistinfo);

        back = findViewById(R.id.back);
        schedule = findViewById(R.id.schedule);
        tname = findViewById(R.id.textView4);
        experience = findViewById(R.id.textexp);
        description = findViewById(R.id.textdescription);
        address1 = findViewById(R.id.textView28);
        address2 = findViewById(R.id.textView30);
        contact1 = findViewById(R.id.textView35);
        contact2 = findViewById(R.id.textView36);
        workinghours1=findViewById(R.id.workinghours1);
        workinghours2=findViewById(R.id.workinghours2);
        ref = FirebaseDatabase.getInstance().getReference().child("therapist");

        String therapistKey = getIntent().getStringExtra("therapistKey");

        ref.child(therapistKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String exp = snapshot.child("feedback").getValue().toString();
                    String des = snapshot.child("description").getValue().toString();
                    String therapistname = snapshot.child("name").getValue().toString();
                    String address3 = snapshot.child("street").getValue().toString();
                    String address4 = snapshot.child("city").getValue().toString();
                    String address5 = snapshot.child("state").getValue().toString();
                    String bigaddress=address4+", "+address5;
                    String contact3 = snapshot.child("phone").getValue().toString();
                    String contact4 = snapshot.child("e_mail").getValue().toString();
                    String hours1=snapshot.child("workinghours1").getValue().toString();
                    String hours2=snapshot.child("workinghours2").getValue().toString();
                    tname.setText(therapistname);
                    experience.setText(exp);
                    description.setText(des);
                    address1.setText(address3);
                    address2.setText(bigaddress);
                    contact1.setText(contact3);
                    contact2.setText(contact4);
                    workinghours1.setText(hours1);
                    workinghours2.setText(hours2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Patient_View_TherapistInfo_Activity.this, Patient_AppointmentPage_Activity.class));
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                String therapistname = tname.getText().toString();
                intent = new Intent(Patient_View_TherapistInfo_Activity.this, ReservationInformationEditPage.class);
                intent.putExtra("therapistKey", therapistKey);
                intent.putExtra("therapistname", therapistname);
                startActivity(intent);
            }
        });
    }
}


