package com.example.myapplication55;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Therapist_HomePage_Activity extends FirebaseAuthMethods{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_homepage);

        BottomNavigationView bottomNavigationView=findViewById(R.id.therapist_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.therapist_navigation_home);
        setName();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch(menuitem.getItemId()){
                    case R.id.therapist_navigation_home:
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
                        startActivity(new Intent(getApplicationContext(), Therapist_ProfilePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
    TextView nameText;
    public void setName(){
        nameText = findViewById(R.id.textView23);
        nameText.setText("Hi "+getNameFBAuth());
    }
}
