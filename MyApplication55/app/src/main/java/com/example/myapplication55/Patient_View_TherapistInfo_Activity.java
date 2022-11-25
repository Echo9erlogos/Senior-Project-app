package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patient_View_TherapistInfo_Activity extends AppCompatActivity {
    ConstraintLayout viewPageButtons;
    TextView tname, experience, description, address1, address2, contact1, contact2;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_therapistinfo);

        tname=findViewById(R.id.textView4);
        experience=findViewById(R.id.textexp);
        description=findViewById(R.id.textdescription);
        address1=findViewById(R.id.textView28);
        address2=findViewById(R.id.textView30);
        contact1=findViewById(R.id.textView35);
        contact2=findViewById(R.id.textView36);
        ref= FirebaseDatabase.getInstance().getReference().child("therapist");

        String therapistKey=getIntent().getStringExtra("therapistKey");

        ref.child(therapistKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String exp=snapshot.child("experience").getValue().toString();
                    String des=snapshot.child("description").getValue().toString();
                    String therapistname=snapshot.child("name").getValue().toString();
                    String address3=snapshot.child("smalladdress").getValue().toString();
                    String address4=snapshot.child("bigaddress").getValue().toString();
                    String contact3=snapshot.child("phone").getValue().toString();
                    String contact4=snapshot.child("e_mail").getValue().toString();
                    tname.setText(therapistname);
                    experience.setText(exp);
                    description.setText(des);
                    address1.setText(address3);
                    address2.setText(address4);
                    contact1.setText(contact3);
                    contact2.setText(contact4);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        viewPageButtons = findViewById(R.id.buttonlists);

        OnClick onClick = new OnClick();

        for (int i = 0; i < viewPageButtons.getChildCount(); i++) {
            viewPageButtons.getChildAt(i).setOnClickListener(onClick);
        }
    }
    private class OnClick implements View.OnClickListener {
        String therapistKey=getIntent().getStringExtra("therapistKey");
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.back:
                    intent = new Intent(Patient_View_TherapistInfo_Activity.this, Patient_AppointmentPage_Activity.class);
                case R.id.schedule:
                    String therapistname=tname.getText().toString();
                    intent = new Intent(Patient_View_TherapistInfo_Activity.this, ReservationInformationEditPage.class);
                    intent.putExtra("therapistKey", therapistKey);
                    intent.putExtra("therapistname",therapistname);
                    break;
                default:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }else{
                Toast.makeText(Patient_View_TherapistInfo_Activity.this,"invalid button",Toast.LENGTH_SHORT).show();
            }
        }
    }
}