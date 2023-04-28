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

public class Therapist_ProfilePage_Activity extends FirebaseAuthMethods {
    Button button=null;
    Button btn;
    private TextView name;
    private TextView city;
    private TextView state;
    private TextView street;
    private TextView score;
    private TextView year;
    private TextView phone;
    private TextView email;
    private TextView number;
    public TextView feedback;
    public TextView description;
    public TextView workinghour1;
    public TextView workinghour2;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    private DatabaseReference rootDatabaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_profile_page);
        btn=findViewById(R.id.signout);
        btn.setOnClickListener(v -> {
            signOut();
        });
        button=(Button)findViewById(R.id.edit);
        button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(Therapist_ProfilePage_Activity.this, EditTherapistProfilePage.class);
            startActivity(intent);
        });

        name=findViewById(R.id.textView4);
        city=findViewById(R.id.textcity);
        state=findViewById(R.id.textstate);
        street=findViewById(R.id.textstreet);
        score=findViewById(R.id.textscore);
        year=findViewById(R.id.textbeingtime);
        phone=findViewById(R.id.textphone);
        email=findViewById(R.id.textemail);
        number=findViewById(R.id.textnumoftreat);
        feedback=findViewById(R.id.textevaluation);
        description=findViewById(R.id.textintroduce);
        workinghour1=findViewById(R.id.textworkinghours1);
        workinghour2=findViewById(R.id.textworkinghours2);
        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("therapist").child(uid);
        rootDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rname=snapshot.child("name").getValue().toString();
                String rcity=snapshot.child("city").getValue().toString();
                String rstate=snapshot.child("state").getValue().toString();
                String rstreet=snapshot.child("street").getValue().toString();
                String rscore=snapshot.child("score").getValue().toString();
                String ryear=snapshot.child("beingtime").getValue().toString();
                String rphone=snapshot.child("phone").getValue().toString();
                String remail=snapshot.child("e_mail").getValue().toString();
                String rnumber=snapshot.child("number").getValue().toString();
                String rfeedback=snapshot.child("feedback").getValue().toString();
                String rdescription=snapshot.child("description").getValue().toString();
                String rworkinghours1=snapshot.child("workinghours1").getValue().toString();
                String rworkinghours2=snapshot.child("workinghours2").getValue().toString();
                workinghour1.setText(rworkinghours1);
                workinghour2.setText(rworkinghours2);
                name.setText(rname);
                city.setText(rcity);
                state.setText(rstate);
                street.setText(rstreet);
                score.setText(rscore);
                year.setText(ryear);
                phone.setText(rphone);
                email.setText(remail);
                number.setText(rnumber);
                feedback.setText(rfeedback);
                description.setText(rdescription);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}