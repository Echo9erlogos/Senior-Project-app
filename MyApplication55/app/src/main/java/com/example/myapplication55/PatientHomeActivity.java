package com.example.myapplication55;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PatientHomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        bottomNavigationView=findViewById(R.id.patient_bottom_navigation);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new patient_home_fragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;


                switch (item.getItemId()){
                    case R.id.patient_navigation_home:
                        fragment=new patient_home_fragment();
                        break;
                    case R.id.patient_navigation_appointment:
                        fragment=new patient_appointment_fragment();
                        break;
                    case R.id.patient_navigation_chat:
                        fragment=new patient_chat_fragment();
                        break;
                    case R.id.patient_navigation_therapist:
                        fragment=new patient_therapist_fragment();
                        break;
                    case R.id.patient_navigation_profile:
                        fragment=new patient_profile_fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, fragment).commit();
                return true;
            }
        });
    }
}