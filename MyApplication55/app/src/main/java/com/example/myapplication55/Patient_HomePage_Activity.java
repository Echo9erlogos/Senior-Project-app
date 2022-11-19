package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class Patient_HomePage_Activity extends FirebaseAuthMethods{

    TextView nameText;
    boolean reload = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_homepage);

        setName();

        BottomNavigationView bottomNavigationView=findViewById(R.id.patient_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.patient_navigation_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.patient_navigation_home:
                        return true;
                    case R.id.patient_navigation_chat:
                        signOut();
                        //startActivity(new Intent(getApplicationContext(), Patient_ChatPage_Activity.class));
                        //overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_therapist:
                        deleteUser();
                        //startActivity(new Intent(getApplicationContext(), Patient_TherapistPage_Activity.class));
                        //overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_appointment:
                        startActivity(new Intent(getApplicationContext(), Patient_AppointmentPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_profile:
                        startActivity(new Intent(getApplicationContext(), Patient_ProfilePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }/*
    @Override
    public void onStart(){
        super.onStart();

            setName();


    }
    */
    public void setName(){
        nameText = findViewById(R.id.UserWelcome);
        nameText.setText("Hi, "+getNameFBAuth());
    }
}
