package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patient_ProfilePage_Activity extends FirebaseAuthMethods {
    Button button=null;
    Button btn;
    private TextView city;
    private TextView state;
    private TextView street;
    private TextView age;
    private TextView condition;
    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView gender;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    private DatabaseReference rootDatabaseref;
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
        city=findViewById(R.id.textcity);
        state=findViewById(R.id.textstate);
        street=findViewById(R.id.textstreet);
        name=findViewById(R.id.displayname);
        age=findViewById(R.id.textage);
        phone=findViewById(R.id.textphone);
        email=findViewById(R.id.textemail);
        gender=findViewById(R.id.textgender);
        condition=findViewById(R.id.textintroduce);
        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("patient").child(uid);
        rootDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String rcity=snapshot.child("city").getValue().toString();
                    String rstate=snapshot.child("state").getValue().toString();
                    String rstreet=snapshot.child("street").getValue().toString();
                    String rname=snapshot.child("pname").getValue().toString();
                    String rage=snapshot.child("age").getValue().toString();
                    String rphone=snapshot.child("phone").getValue().toString();
                    String remail=snapshot.child("email").getValue().toString();
                    String rgender=snapshot.child("gender").getValue().toString();
                    String rcondition=snapshot.child("condition").getValue().toString();
                    city.setText(rcity);
                    state.setText(rstate);
                    street.setText(rstreet);
                    name.setText(rname);
                    age.setText(rage);
                    phone.setText(rphone);
                    email.setText(remail);
                    gender.setText(rgender);
                    condition.setText(rcondition);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}