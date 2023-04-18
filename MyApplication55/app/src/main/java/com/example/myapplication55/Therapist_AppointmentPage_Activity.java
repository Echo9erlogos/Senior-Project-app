package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication55.Adapters.DisplayAdapterPending;
import com.example.myapplication55.model.appointmentInfos;
import com.example.myapplication55.model.appointmentinfodisplayTherapist;
import com.example.myapplication55.model.patientfriendinfos;
import com.example.myapplication55.model.therapistfriendinfos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Therapist_AppointmentPage_Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FirebaseRecyclerOptions<appointmentinfodisplayTherapist> options;
    FirebaseRecyclerAdapter<appointmentinfodisplayTherapist, DisplayAdapterPending> adapter;
    DatabaseReference DataRef,DataRef2;
    final String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_appointmentpage);

        mRecyclerView=findViewById(R.id.demandlist);
        DataRef= FirebaseDatabase.getInstance().getReference().child("pendingappointment").child(uid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        LoadData();


    }

    private void LoadData() {
        options=new FirebaseRecyclerOptions.Builder<appointmentinfodisplayTherapist>().setQuery(DataRef,appointmentinfodisplayTherapist.class).build();
        adapter=new FirebaseRecyclerAdapter<appointmentinfodisplayTherapist, DisplayAdapterPending>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DisplayAdapterPending holder, int position, @NonNull appointmentinfodisplayTherapist model) {
                holder.textpatientname.setText(model.getPatientname());
                holder.textaddress.setText(model.getAddress());
                holder.textdate.setText(model.getDate());
                holder.texttime.setText(model.getTime());
                holder.textphone.setText(model.getPhone());
                holder.textemail.setText(model.getEmail());
                holder.textcondition.setText(model.getCondition());
                holder.reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataRef.child(model.getPatientuid()).removeValue();
                        DataRef2=FirebaseDatabase.getInstance().getReference().child("patientappointment").child(model.getPatientuid()).child(uid);
                        HashMap hashMap=new HashMap();
                        hashMap.put("state","be rejected");
                        DataRef2.updateChildren(hashMap);
                        startActivity(new Intent(Therapist_AppointmentPage_Activity.this,Therapist_HomePage_Activity.class));
                    }
                });
                holder.confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appointmentInfos appointmentInfo=new appointmentInfos();
                        patientfriendinfos patientfriendinfo=new patientfriendinfos();
                        therapistfriendinfos therapistfriendinfo=new therapistfriendinfos();
                        appointmentInfo.date=model.getDate();
                        appointmentInfo.time=model.getTime();
                        appointmentInfo.patientuid=model.getPatientuid();
                        appointmentInfo.therapistuid=uid;
                        appointmentInfo.patientname=model.getPatientname();
                        appointmentInfo.address=model.getAddress();
                        appointmentInfo.phone=model.getPhone();
                        appointmentInfo.email=model.getEmail();
                        appointmentInfo.condition=model.getCondition();
                        //new stuff
                        appointmentInfo.paymentstatus="0";
                        appointmentInfo.paymentamount="0.00";
                        appointmentInfo.weeks="-";
                        appointmentInfo.advice="-";

                        FirebaseDatabase.getInstance().getReference().child("appointmentinprogress").child(uid).child(model.getPatientuid()).setValue(appointmentInfo);
                        patientfriendinfo.therapistname=model.getTherapistname();
                        patientfriendinfo.therapistuid=uid;
                        therapistfriendinfo.patientname=model.getPatientname();
                        therapistfriendinfo.patientuid=model.getPatientuid();
                        FirebaseDatabase.getInstance().getReference().child("chats").child(model.getPatientuid()).child(uid).setValue(patientfriendinfo);
                        FirebaseDatabase.getInstance().getReference().child("chats").child(uid).child(model.getPatientuid()).setValue(therapistfriendinfo);
                        DataRef.child(model.getPatientuid()).removeValue();
                        DataRef2=FirebaseDatabase.getInstance().getReference().child("patientappointment").child(model.getPatientuid()).child(uid);
                        HashMap hashMap=new HashMap();
                        hashMap.put("state","be accepted");
                        DataRef2.updateChildren(hashMap);
                        startActivity(new Intent(Therapist_AppointmentPage_Activity.this,Therapist_PatientPage_Activity.class));
                    }
                });
            }

            @NonNull
            @Override
            public DisplayAdapterPending onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.therapist_pending_appointment, parent, false);
                return new DisplayAdapterPending(v);
            }
        };
        adapter.startListening();
        mRecyclerView.setAdapter(adapter);
    }
}