package com.example.myapplication55;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;


public class Patient_HomePage_Activity extends FirebaseAuthMethods{
    CardView myappointment, makeanappointment, myprofile, chat;
    ImageView avatar;
    boolean reload = true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_newhomepage);

        myappointment=findViewById(R.id.patient_myappointment);
        makeanappointment=findViewById(R.id.patient_makeanappointment);
        myprofile=findViewById(R.id.patient_myprofile);
        chat=findViewById(R.id.patient_chat);
        avatar=findViewById(R.id.patient_avatar);
        myappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient_HomePage_Activity.this, Patient_TherapistPage_Activity.class );
                startActivity(intent);
            }
        });
        makeanappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient_HomePage_Activity.this, Patient_AppointmentPage_Activity.class );
                startActivity(intent);
            }
        });
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient_HomePage_Activity.this, Patient_ProfilePage_Activity.class );
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient_HomePage_Activity.this, Patient_ChatPage_Activity.class );
                startActivity(intent);
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient_HomePage_Activity.this, Patient_ProfilePage_Activity.class );
                startActivity(intent);
            }
        });
    }
}
