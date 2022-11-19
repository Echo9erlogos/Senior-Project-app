package com.example.myapplication55;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Therapist_ProfilePage_Activity extends AppCompatActivity {
    Button button=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_profilepage);
        button=(Button)findViewById(R.id.edit);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(Therapist_ProfilePage_Activity.this, EditTherapistProfilePage.class);
            startActivity(intent);
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.therapist_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.therapist_navigation_profile);

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
                        startActivity(new Intent(getApplicationContext(), Therapist_AppointmentPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.therapist_navigation_profile:
                        return true;
                }
                return false;
            }
        });
    }
}