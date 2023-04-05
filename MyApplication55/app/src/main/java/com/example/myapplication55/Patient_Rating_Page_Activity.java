package com.example.myapplication55;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;


public class Patient_Rating_Page_Activity extends AppCompatActivity {

    private DatabaseReference rootDatabaseref;
    int number;
    double score;
    String nfeedback;
    String ufeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_rating_page);
        String therapistname=getIntent().getStringExtra("therapistname");
        String therapistuid=getIntent().getStringExtra("therapistuid");
        RatingBar ratingBar;
        Button submit;
        TextView name;
        EditText feedback;
        name=findViewById(R.id.therapist_name);
        name.setText(therapistname);
        feedback=findViewById(R.id.feedbackii);
        ratingBar=findViewById(R.id.ratingBar);
        submit=findViewById(R.id.submit);


        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("therapist").child(therapistuid);
        rootDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String rfeedback=snapshot.child("feedback").getValue().toString();
                String rnumber=snapshot.child("number").getValue().toString();
                String rscore=snapshot.child("score").getValue().toString();
               number=Integer.parseInt(rnumber);
               score=Double.parseDouble(rscore);
                DecimalFormat df=new DecimalFormat("0.0");
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                score=score*number;
                                score=score+rating;
                                number++;
                                score=score/number;
                                ufeedback=feedback.getText().toString();
                                nfeedback=rfeedback+"\n\n"+ufeedback;
                                String inputnumber=String.valueOf(number);
                                String inputscore=String.valueOf(score);
                                HashMap hashMap=new HashMap();
                                hashMap.put("number",inputnumber);
                                hashMap.put("score",inputscore);
                                hashMap.put("feedback",nfeedback);
                                rootDatabaseref.updateChildren(hashMap);
                                Intent intent = new Intent();
                                intent.setClass(Patient_Rating_Page_Activity.this, Patient_HomePage_Activity.class);
                                Toast.makeText(Patient_Rating_Page_Activity.this,"Rating: "+String.valueOf(rating),Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}