package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditPatientProfilePage extends AppCompatActivity {
    private TextView disname;
    private EditText name;
    private EditText city;
    private EditText state;
    private EditText street;
    private EditText phone;
    private EditText email;
    private EditText birth;
    private EditText condition;
    private DatabaseReference rootDatabaseref;
    private Button save;
    private String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_edit_patient_profile_page);

        disname=findViewById(R.id.displaynamep);
        name=findViewById(R.id.editnamep);
        save=findViewById(R.id.savep);
        city=findViewById(R.id.editcityp);
        state=findViewById(R.id.editstatep);
        street=findViewById(R.id.editstreetp);
        phone=findViewById(R.id.editphonep);
        birth=findViewById(R.id.editbirthp);
        condition=findViewById(R.id.editintroducep);
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
                    String rcondition=snapshot.child("condition").getValue().toString();
                    city.setText(rcity);
                    state.setText(rstate);
                    street.setText(rstreet);
                    disname.setText(rname);
                    name.setText(rname);
                    birth.setText(rage);
                    phone.setText(rphone);
                    condition.setText(rcondition);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rname=name.getText().toString();
                String rstate=state.getText().toString();
                String rstreet=street.getText().toString();
                String rbirth=birth.getText().toString();
                String rphone=phone.getText().toString();
                String rcondition=condition.getText().toString();
                String rcity=city.getText().toString();
                HashMap hashMap=new HashMap();
                hashMap.put("pname",rname);
                hashMap.put("state",rstate);
                hashMap.put("street",rstreet);
                hashMap.put("birth",rbirth);
                hashMap.put("phone",rphone);
                hashMap.put("condition",rcondition);
                hashMap.put("city",rcity);
                rootDatabaseref.updateChildren(hashMap);
                Intent intent = new Intent();
                intent.setClass(EditPatientProfilePage.this,Patient_ProfilePage_Activity.class);
                startActivity(intent);
            }
        });
    }
}
