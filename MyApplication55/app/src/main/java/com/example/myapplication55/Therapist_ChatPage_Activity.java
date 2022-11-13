package com.example.myapplication55;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Therapist_ChatPage_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_chatpage);

        BottomNavigationView bottomNavigationView=findViewById(R.id.therapist_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.therapist_navigation_chat);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.therapist_navigation_home:
                        startActivity(new Intent(getApplicationContext(), Therapist_HomePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.therapist_navigation_chat:
                        return true;
                    case R.id.therapist_navigation_patient:
                        startActivity(new Intent(getApplicationContext(), Therapist_PatientPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.therapist_navigation_appointment:
                        startActivity(new Intent(getApplicationContext(), Therapist_AppointmentPage_Activity.class));
                        overridePendingTransition(0, 0);
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
