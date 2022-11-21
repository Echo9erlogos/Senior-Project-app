package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Patient_ProfilePage_Activity extends FirebaseAuthMethods {
    Button button=null;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_page);
        button=(Button)findViewById(R.id.edit);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(Patient_ProfilePage_Activity.this,EditPatientProfilePage.class);
            startActivity(intent);
        });
        btn=(Button)findViewById(R.id.patient_signout_button);
        btn.setOnClickListener(v -> {
            signOut();
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.patient_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.patient_navigation_profile);

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
                        startActivity(new Intent(getApplicationContext(), Patient_AppointmentPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_profile:
                        return true;
                }
                return false;
            }
        });
    }

}