package com.example.myapplication55;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Adapters.DisplayAdapterTherapist;
import com.example.myapplication55.model.appointmentInfos;
import com.example.myapplication55.model.appointmentinfodisplayTherapist;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Therapist_PatientPage_Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<appointmentinfodisplayTherapist> options;
    FirebaseRecyclerAdapter<appointmentinfodisplayTherapist, DisplayAdapterTherapist> adapter;
    DatabaseReference DataRef,DataRef2;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_patientpage);
        mRecyclerView=findViewById(R.id.demandlist);
        DataRef= FirebaseDatabase.getInstance().getReference().child("appointmentinprogress").child(uid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        LoadData();



    }
    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<appointmentinfodisplayTherapist>().setQuery(DataRef,appointmentinfodisplayTherapist.class).build();
        adapter=new FirebaseRecyclerAdapter<appointmentinfodisplayTherapist, DisplayAdapterTherapist>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DisplayAdapterTherapist holder, int position, @NonNull appointmentinfodisplayTherapist model) {
                holder.textpatientname.setText(model.getPatientname());
                holder.textaddress.setText(model.getAddress());
                holder.textdate.setText(model.getDate());
                holder.texttime.setText(model.getTime());
                holder.textphone.setText(model.getPhone());
                holder.textemail.setText(model.getEmail());
                holder.textcondition.setText(model.getCondition());
                holder.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataRef.child(model.getPatientuid()).removeValue();
                        DataRef2=FirebaseDatabase.getInstance().getReference().child("patientappointment").child(model.getPatientuid()).child(uid);
                        HashMap hashMap=new HashMap();
                        hashMap.put("state","be Canceled");
                        DataRef2.updateChildren(hashMap);
                    }
                });
                holder.request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String status="0";
                        if(status.equals(model.getpaymentstatus())) {
                            Intent intent = new Intent(Therapist_PatientPage_Activity.this, Therapist_RequestPage.class);
                            intent.putExtra("patientuid", model.getPatientuid());
                            intent.putExtra("patientname", model.getPatientname());
                            startActivity(intent);
                        }else{
                            Toast.makeText(Therapist_PatientPage_Activity.this,"You have sent a request to "+model.getPatientname()+" for the amount: "+model.getpaymentamount(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.advice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Therapist_PatientPage_Activity.this, Therapist_StatusPage_Activity.class);
                        intent.putExtra("patientuid", model.getPatientuid());
                        intent.putExtra("patientname", model.getPatientname());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public DisplayAdapterTherapist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_appointment_in_progress, parent, false);
                return new DisplayAdapterTherapist(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}
