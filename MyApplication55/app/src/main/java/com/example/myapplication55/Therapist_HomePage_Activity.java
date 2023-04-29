package com.example.myapplication55;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import  android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Therapist_HomePage_Activity extends FirebaseAuthMethods {
    CardView myappointment, mypatient, myprofile, chat;
    ImageView avatar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_newhomepage);

        myappointment=findViewById(R.id.therapist_myappointment);
        mypatient=findViewById(R.id.therapist_mypatient);
        myprofile=findViewById(R.id.therapist_myprofile);
        chat=findViewById(R.id.therapist_chat);
        avatar=findViewById(R.id.therapist_avatar);

        myappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Therapist_HomePage_Activity.this, Therapist_AppointmentPage_Activity.class );
                startActivity(intent);
            }
        });
        mypatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Therapist_HomePage_Activity.this, Therapist_PatientPage_Activity.class );
                startActivity(intent);
            }
        });
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Therapist_HomePage_Activity.this, Therapist_ProfilePage_Activity.class );
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Therapist_HomePage_Activity.this, Therapist_ChatPage_Activity.class );
                startActivity(intent);
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Therapist_HomePage_Activity.this, Therapist_ProfilePage_Activity.class );
                startActivity(intent);
            }
        });
    }
    TextView nameText;
    public void setName(){
        nameText = findViewById(R.id.textView23);
        nameText.setText("Hi "+getNameFBAuth());
    }
}
