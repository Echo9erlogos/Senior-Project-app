package com.example.myapplication55;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Therapist_AppointmentPage_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_appointmentpage);

        BottomNavigationView bottomNavigationView=findViewById(R.id.therapist_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.therapist_navigation_appointment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.therapist_navigation_home:
                        startActivity(new Intent(getApplicationContext(), Therapist_HomePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.therapist_navigation_chat:
                        startActivity(new Intent(getApplicationContext(), Therapist_ChatPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.therapist_navigation_patient:
                        startActivity(new Intent(getApplicationContext(), Therapist_PatientPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.therapist_navigation_appointment:
                        return true;
                    case R.id.therapist_navigation_profile:
                        startActivity(new Intent(getApplicationContext(), Therapist_ProfilePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}
