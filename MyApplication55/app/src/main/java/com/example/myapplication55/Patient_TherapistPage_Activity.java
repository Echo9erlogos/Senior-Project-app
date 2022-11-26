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
    DatabaseReference DataRef,DataRef2;
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

        BottomNavigationView bottomNavigationView=findViewById(R.id.patient_bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.patient_navigation_therapist);

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
                        return true;
                    case R.id.patient_navigation_appointment:
                        startActivity(new Intent(getApplicationContext(), Patient_AppointmentPage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.patient_navigation_profile:
                        startActivity(new Intent(getApplicationContext(), Patient_ProfilePage_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<appointmentinfodisplayPatient>().setQuery(DataRef,appointmentinfodisplayPatient.class).build();
        adapter=new FirebaseRecyclerAdapter<appointmentinfodisplayPatient, DisplayAdapterPatient>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DisplayAdapterPatient holder, int position, @NonNull appointmentinfodisplayPatient model) {
                holder.textname.setText(model.getTherapistname());
                holder.textstate.setText(model.getState());
                holder.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataRef.removeValue();
                        DataRef2=FirebaseDatabase.getInstance().getReference().child("pendingappointment").child(model.getTherapistuid()).child(uid);
                        DataRef2.removeValue();
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
