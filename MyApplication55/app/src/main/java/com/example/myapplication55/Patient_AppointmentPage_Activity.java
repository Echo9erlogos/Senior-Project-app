package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Patient_AppointmentPage_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointmentpage);

        BottomNavigationView bottomNavigationView=findViewById(R.id.patient_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.patient_navigation_appointment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.patient_navigation_home:
                        startActivity(new Intent(getApplicationContext(), Patient_HomePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_chat:
                        startActivity(new Intent(getApplicationContext(), Patient_ChatPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_therapist:
                        startActivity(new Intent(getApplicationContext(), Patient_TherapistPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_appointment:
                        return true;
                    case R.id.patient_navigation_profile:
                        startActivity(new Intent(getApplicationContext(), Patient_ProfilePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}
