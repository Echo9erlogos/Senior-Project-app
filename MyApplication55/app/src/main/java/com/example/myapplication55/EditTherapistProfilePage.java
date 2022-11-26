package com.example.myapplication55;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditTherapistProfilePage extends AppCompatActivity {
    private TextView disname;
    private EditText name;
    private EditText city;
    private EditText state;
    private EditText street;
    private EditText phone;
    private EditText year;
    private EditText introduction;
    private EditText workinghours1;
    private EditText workinghours2;
    private DatabaseReference rootDatabaseref;
    private Button save;
    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_therapist_prifile_page);
        disname=findViewById(R.id.displayname);
        name=findViewById(R.id.editname);
        city=findViewById(R.id.editcity);
        state=findViewById(R.id.editstate);
        street=findViewById(R.id.editstreet);
        phone=findViewById(R.id.editphone);
        year=findViewById(R.id.editbeingtime);
        introduction=findViewById(R.id.editintroduce);
        workinghours1=findViewById(R.id.editworkinghours1);
        workinghours2=findViewById(R.id.editworkinghours2);
        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("therapist").child(uid);
        rootDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rcity=snapshot.child("city").getValue().toString();
                String rstate=snapshot.child("state").getValue().toString();
                String rstreet=snapshot.child("street").getValue().toString();
                String rname=snapshot.child("name").getValue().toString();
                String ryear=snapshot.child("beingtime").getValue().toString();
                String rphone=snapshot.child("phone").getValue().toString();
                String rcondition=snapshot.child("description").getValue().toString();
                String rworkinghours1=snapshot.child("workinghours1").getValue().toString();
                String rworkinghours2=snapshot.child("workinghours2").getValue().toString();
                city.setText(rcity);
                state.setText(rstate);
                street.setText(rstreet);
                disname.setText(rname);
                name.setText(rname);
                year.setText(ryear);
                phone.setText(rphone);
                introduction.setText(rcondition);
                workinghours1.setText(rworkinghours1);
                workinghours2.setText(rworkinghours2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rname=name.getText().toString();
                String rstate=state.getText().toString();
                String rstreet=street.getText().toString();
                String ryear=year.getText().toString();
                String rphone=phone.getText().toString();
                String rintroduction=introduction.getText().toString();
                String rcity=city.getText().toString();
                String rworkinghours1=workinghours1.getText().toString();
                String rworkinghours2=workinghours2.getText().toString();
                HashMap hashMap=new HashMap();
                hashMap.put("name",rname);
                hashMap.put("state",rstate);
                hashMap.put("street",rstreet);
                hashMap.put("beingtime",ryear);
                hashMap.put("phone",rphone);
                hashMap.put("description",rintroduction);
                hashMap.put("city",rcity);
                hashMap.put("workinghours1",rworkinghours1);
                hashMap.put("workinghours2",rworkinghours2);
                rootDatabaseref.updateChildren(hashMap);
                Intent intent = new Intent();
                intent.setClass(EditTherapistProfilePage.this,Therapist_ProfilePage_Activity.class);
                startActivity(intent);
            }
        });
    }
}