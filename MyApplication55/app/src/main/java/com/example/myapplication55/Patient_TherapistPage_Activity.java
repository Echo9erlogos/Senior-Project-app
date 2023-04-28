package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Adapters.DisplayAdapterPatient;
import com.example.myapplication55.model.appointmentinfodisplayPatient;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Patient_TherapistPage_Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<appointmentinfodisplayPatient> options;
    FirebaseRecyclerAdapter<appointmentinfodisplayPatient, DisplayAdapterPatient> adapter;
    DatabaseReference DataRef,DataRef2,DataRef3;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_therapistpage);

        mRecyclerView=findViewById(R.id.infolist);
        DataRef= FirebaseDatabase.getInstance().getReference().child("patientappointment").child(uid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        LoadData();


    }

    private void LoadData() {
        String state="waiting for payment";
        options=new FirebaseRecyclerOptions.Builder<appointmentinfodisplayPatient>().setQuery(DataRef,appointmentinfodisplayPatient.class).build();
        adapter=new FirebaseRecyclerAdapter<appointmentinfodisplayPatient, DisplayAdapterPatient>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DisplayAdapterPatient holder, int position, @NonNull appointmentinfodisplayPatient model) {
                holder.textname.setText(model.getTherapistname());
                holder.textstate.setText(model.getState());
                holder.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataRef=FirebaseDatabase.getInstance().getReference().child("patientappointment").child(uid).child(model.getTherapistuid());
                        DataRef.removeValue();
                        DataRef2=FirebaseDatabase.getInstance().getReference().child("pendingappointment").child(model.getTherapistuid()).child(uid);
                        DataRef2.removeValue();
                        DataRef3=FirebaseDatabase.getInstance().getReference().child("appointmentinprogress").child(model.getTherapistuid()).child(uid);
                        DataRef3.removeValue();
                    }
                });
                holder.textstate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(state.equals(model.getState())){
                            Intent intent = new Intent(Patient_TherapistPage_Activity.this, PatientPaymentPageActivity.class);
                            intent.putExtra("amount",model.getpaymentamount());
                            intent.putExtra("therapistuid", model.getTherapistuid());
                            intent.putExtra("therapistname", model.getTherapistname());
                            startActivity(intent);
                        }
                    }
                });
                holder.status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Patient_TherapistPage_Activity.this, Patient_StatusPage_Activity.class);
                        intent.putExtra("weeks",model.getWeeks());
                        intent.putExtra("advices",model.getAdvice());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public DisplayAdapterPatient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_appointment_state, parent, false);
                return new DisplayAdapterPatient(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}
